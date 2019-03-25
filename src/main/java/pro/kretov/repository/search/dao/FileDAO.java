package pro.kretov.repository.search.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.kretov.repository.search.index.entity.File;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@Service
public class FileDAO {

    private DataSource dataSource;

    @Autowired
    public FileDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public File save(File file) throws DAOException {
        try (Connection connection = dataSource.getConnection()) {
            file.setId(UUID.randomUUID().toString());
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into repository_index.public.files (id, name, repository_id) values (?, ?, ?)"
            );
            preparedStatement.setString(1, file.getId().toString());
            preparedStatement.setString(2, file.getName());
            preparedStatement.setString(3, file.getRepository().getId().toString());
            preparedStatement.execute();
            return file;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
