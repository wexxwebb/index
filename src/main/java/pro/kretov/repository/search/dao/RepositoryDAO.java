package pro.kretov.repository.search.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.kretov.repository.search.index.entity.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author Aleksandr Kretov
 * @date 25.02.2019
 */

@Component
public class RepositoryDAO {

    private DataSource dataSource;

    @Autowired
    public RepositoryDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Repository save(Repository repository) throws DAOException {
        try (Connection connection = dataSource.getConnection()) {
            repository.setId(UUID.randomUUID().toString());
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into repository_index.public.repositories (id, name, address) values (?, ?, ?)"
            );
            preparedStatement.setString(1, repository.getId().toString());
            preparedStatement.setString(2, repository.getName());
            preparedStatement.setString(3, repository.getAddress());
            preparedStatement.execute();
            return repository;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
