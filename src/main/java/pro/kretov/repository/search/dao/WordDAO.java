package pro.kretov.repository.search.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.kretov.repository.search.index.entity.Word;

import javax.sql.DataSource;
import java.sql.*;
import java.util.UUID;

@Service
public class WordDAO {

    private DataSource dataSource;

    @Autowired
    public WordDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void index() throws DAOException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into words (sequence) select distinct word from files_words"
            );
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public Word save(Word word) throws DAOException {
        try (Connection connection = dataSource.getConnection()) {
            word.setId(UUID.randomUUID().toString());
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into repository_index.public.words (id, sequence) values (?, ?) " +
                            "on conflict do nothing"
            );
            preparedStatement.setString(1, word.getId().toString());
            preparedStatement.setString(2, word.getSequence());
            preparedStatement.execute();
            return word;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public Word get(String sequence) throws DAOException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from repository_index.public.words where sequence = ?"
            );
            preparedStatement.setString(1, sequence);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.first()) {
                Word word = new Word();
                word.setId(resultSet.getString("id"));
                word.setSequence(resultSet.getString("sequence"));
                return word;
            } else {
                throw new DAOException();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }
}
