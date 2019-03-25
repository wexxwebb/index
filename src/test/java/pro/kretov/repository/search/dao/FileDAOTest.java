package pro.kretov.repository.search.dao;

import org.junit.jupiter.api.Test;
import pro.kretov.repository.search.config.PersistenceConfiguration;
import pro.kretov.repository.search.index.entity.File;
import pro.kretov.repository.search.index.entity.Repository;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

class FileDAOTest {

    @Test
    void save() throws DAOException {
//        PersistenceConfiguration persistenceConfiguration = new PersistenceConfiguration();
//        DataSource dataSource = persistenceConfiguration.dataSource();
//
//        Repository repository = new Repository();
//        repository.setName("Main");
//        repository.setAddress("zip/zip");
//
//        RepositoryDAO repositoryDAO = new RepositoryDAO(dataSource);
//
//        repository = repositoryDAO.save(repository);
//
//        FileDAO fileDAO = new FileDAO(persistenceConfiguration.dataSource());
//        File file = new File();
//        file.setName("ola/ola.java");
//        file.setRepository(repository);
//        file = fileDAO.save(file);
    }
}