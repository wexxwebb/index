package pro.kretov.repository.search.dao;

import org.junit.jupiter.api.Test;
import pro.kretov.repository.search.config.PersistenceConfiguration;
import pro.kretov.repository.search.index.entity.File;
import pro.kretov.repository.search.index.entity.Word;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

class FilesWordsDAOTest {

    @Test
    void wire() throws DAOException {
//        PersistenceConfiguration persistenceConfiguration = new PersistenceConfiguration();
//        DataSource dataSource = persistenceConfiguration.dataSource();
//        FilesWordsDAO filesWordsDAO = new FilesWordsDAO(dataSource);
//
//        Word word = new Word();
//        word.setSequence("Important");
//        WordDAO wordDAO = new WordDAO(dataSource);
//        word = wordDAO.save(word);
//
//        File file = new File();
//        file.setName("/imp/file.xml");
//        FileDAO fileDAO = new FileDAO(dataSource);
//        file = fileDAO.save(file);
//
//        filesWordsDAO.wire(file, word);
    }
}