package pro.kretov.repository.search.dao;

import org.springframework.stereotype.Service;
import pro.kretov.repository.search.index.dto.Item;
import pro.kretov.repository.search.index.entity.Entrance;
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
        List<Item> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select sequence from repository_index.public.words where " +
                            "sequence like ? order by sequence asc"
            );
            preparedStatement.setString(1, sequence + "%");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Item item = new Item(
                        resultSet.getString("sequence"));
                result.add(item);
            }
            return result;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public List<Entrance> getWord(String sequence) throws DAOException {
        List<Entrance> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from entrances where sequence = ?; "
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
        repository.setName(resultSet.getString("repository_name"));
        repository.setAddress(resultSet.getString("repository_path"));
        File file = new File();
        file.setName(resultSet.getString("file_name"));
        return file;
    }

    public File getFile(String id) throws DAOException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select sequence from entrance f " +
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
