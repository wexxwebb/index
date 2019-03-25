package pro.kretov.repository.search.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.kretov.repository.search.index.entity.File;
import pro.kretov.repository.search.index.entity.Word;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Service
public class FilesWordsDAO {

    private DataSource dataSource;

    @Autowired
    public FilesWordsDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void wire(File file) throws DAOException {
        Set<Word> buffer = new HashSet<>();
        for (Word word : file.getWords()) {
            buffer.add(word);
            if (buffer.size() > 10000) {
                save(file, buffer);
                buffer.clear();
            }
        }
        save(file, buffer);
    }

    private void save(File file, Set<Word> words) throws DAOException {
        if (!words.isEmpty()) {
            String statement = buildStatement(words);
            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(statement);
                int i = 0;
                for (Word word : words) {
                    preparedStatement.setString(++i, file.getId().toString());
                    preparedStatement.setString(++i, word.getSequence());
                }
                preparedStatement.execute();
            } catch (SQLException e) {
                System.out.println(statement);
                throw new DAOException(e);
            }
        }
    }

    private String buildStatement(Set<Word> words) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into repository_index.public.files_words (file_id, word) values");
        for (int i = 0; i < words.size(); i++) {
            stringBuilder.append(" (?, ?)");
            if (i + 1 < words.size()) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append(" on conflict do nothing");
        return stringBuilder.toString();
    }
}
