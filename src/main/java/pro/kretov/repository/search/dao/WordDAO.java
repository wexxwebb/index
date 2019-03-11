package pro.kretov.repository.search.dao;

import org.springframework.beans.factory.annotation.Autowired;
import pro.kretov.repository.search.index.entity.Word;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class WordDAO {

    private DataSource dataSource;

    @Autowired
    public WordDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

//    public Word save(Word word) {
//        try {
//            Connection connection = dataSource.getConnection();
//            word.setId(UUID.randomUUID().toString());
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "insert into words (id, sequence) values (?, ?)"
//            );
//            preparedStatement.setString(1, uuid.toString());
//            preparedStatement.setString(2, word.getSequence());
//            preparedStatement.execute();
//            return
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
