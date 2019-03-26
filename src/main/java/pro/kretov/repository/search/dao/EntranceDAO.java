package pro.kretov.repository.search.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.kretov.repository.search.index.entity.Entrance;
import pro.kretov.repository.search.index.entity.Word;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class EntranceDAO {

    private DataSource dataSource;

    @Autowired
    public EntranceDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private void insert(Set<Entrance> entrances) throws DAOException {
        if (!entrances.isEmpty()) {
            String statement = buildStatement(entrances);
            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(statement);
                int i = 0;
                for (Entrance entrance : entrances) {
                    preparedStatement.setString(++i, UUID.randomUUID().toString());
                    preparedStatement.setString(++i, entrance.getRepository().getName());
                    preparedStatement.setString(++i, entrance.getRepository().getAddress());
                    preparedStatement.setString(++i, entrance.getFile().getName());
                    preparedStatement.setString(++i, entrance.getWord().getSequence());
                }
                preparedStatement.execute();
            } catch (SQLException e) {
                System.out.println(statement);
                throw new DAOException(e);
            }
        }
    }

    private String buildStatement(Set<Entrance> words) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into repository_index.public.entrances " +
                "(id, repository_name, repository_path, file_name, sequence) values");
        for (int i = 0; i < words.size(); i++) {
            stringBuilder.append(" (?, ?, ?, ?, ?)");
            if (i + 1 < words.size()) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

    public void save(Set<Entrance> entrances) throws DAOException {
        Set<Entrance> buffer = new HashSet<>();
        for (Entrance entrance: entrances) {
            buffer.add(entrance);
            if (buffer.size() > 10000) {
                insert(buffer);
                buffer.clear();
            }
        }
        insert(buffer);
    }
}
