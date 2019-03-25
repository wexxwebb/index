package pro.kretov.repository.search.dao;

import org.springframework.stereotype.Service;
import pro.kretov.repository.search.index.dto.Item;
import pro.kretov.repository.search.index.entity.File;
import pro.kretov.repository.search.index.entity.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class SearchDAO {

    private DataSource dataSource;

    public SearchDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Collection<Item> searchSequence(String sequence) throws DAOException {
        Set<Item> result = new TreeSet<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select word from repository_index.public.files_words where " +
                            "word like ?"
            );
            preparedStatement.setString(1, sequence + "%");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Item item = new Item(
                        resultSet.getString("word"));
                result.add(item);
            }
            return result;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public List<File> getWord(String sequence) throws DAOException {
        List<File> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select r.name repo_name, r.address repo_path, f.id file_id, f.name file_name from files_words fw" +
                            " inner join files f on fw.file_id = f.id" +
                            " inner join repositories r on r.id = f.repository_id where word = ?; "
            );
            preparedStatement.setString(1, sequence);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                File file = buildFile(resultSet);
                result.add(file);
            }
            return result;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private File buildFile(ResultSet resultSet) throws SQLException {
        Repository repository = new Repository();
        repository.setName(resultSet.getString("repo_name"));
        repository.setAddress(resultSet.getString("repo_path"));
        File file = new File();
        file.setRepository(repository);
        file.setId(resultSet.getString("file_id"));
        file.setName(resultSet.getString("file_name"));
        return file;
    }

    public File getFile(String id) throws DAOException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select f.id file_id, r.name repo_name, r.address repo_path, f.name file_name from files f " +
                            "inner join repositories r on f.repository_id = r.id where f.id = ?"
            );
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                File file = buildFile(resultSet);
                return file;
            }
            throw new DAOException();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
