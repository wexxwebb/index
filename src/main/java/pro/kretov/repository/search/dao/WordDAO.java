package pro.kretov.repository.search.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
                    "insert into words (sequence) select distinct sequence from entrances"
            );
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
