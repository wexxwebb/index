package pro.kretov.repository.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.kretov.repository.search.dao.SaveDAO;
import pro.kretov.repository.search.index.entity.File;
import pro.kretov.repository.search.index.entity.Repository;
import pro.kretov.repository.search.index.entity.Word;


/**
 * @author Aleksandr Kretov
 * @date 27.02.2019
 */

@RestController
public class TestController {

    private SaveDAO saveDAO;

    @Autowired
    public TestController(SaveDAO saveDAO) {
        this.saveDAO = saveDAO;
    }

    @GetMapping(value = "/test")
    public void test() {

//        saveDAO.openSession();

        Repository repository = new Repository();
        repository.setName("Test");

//        repository.setId(saveDAO.save(repository));

        File file = new File();
        file.setName("pom.pom");
        file.setRepository(repository);
        repository.getFiles().add(file);


        File fil2 = new File();
        fil2.setName("pom2.pom");
        fil2.setRepository(repository);
        repository.getFiles().add(fil2);


//        file.setId(saveDAO.save(file));

        Word word = new Word();
        word.setSequence("olalal");
        word.getFiles().add(file);
        word.getFiles().add(fil2);
        file.getWords().add(word);
        fil2.getWords().add(word);

//        saveDAO.save(word);
        saveDAO.save(repository);

//        saveDAO.persist(repository);

//        saveDAO.closeSession();

        Repository repository1 = new Repository();
        repository1.setName("Test2");


        File file1 = new File();
        file1.setRepository(repository1);

        repository1.getFiles().add(file1);

        file1.setName("pom3.pom");

        file1.getWords().add(word);
        word.getFiles().add(file1);

//        saveDAO.openSession();
//
//        saveDAO.save(repository1);
//
//        saveDAO.closeSession();


//        saveDAO.openSession();
//        saveDAO.save(word);
//        saveDAO.closeSession();
    }
}
