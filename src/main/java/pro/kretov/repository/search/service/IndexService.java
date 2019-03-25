package pro.kretov.repository.search.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.kretov.repository.search.dao.*;
import pro.kretov.repository.search.exception.JenkinsClientException;
import pro.kretov.repository.search.index.entity.Repository;
import pro.kretov.repository.search.index.entity.Word;
import pro.kretov.repository.search.jenkins.JenkinsClient;
import pro.kretov.repository.search.jobs.Job;
import pro.kretov.repository.search.jobs.Jobs;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Aleksandr Kretov
 * @date 25.02.2019
 */

@Component
public class IndexService {

    private static Logger logger = LoggerFactory.getLogger(IndexService.class);

    private ExecutorService executorService;
    private List<Fut> futures = new CopyOnWriteArrayList<>();

    private JenkinsClient jenkinsClient;
//    private SaveDAO saveDAO;

    private RepositoryDAO repositoryDAO;

    private FileDAO fileDAO;

    private WordDAO wordDAO;

    private FilesWordsDAO filesWordsDAO;

    public IndexService(RepositoryDAO repositoryDAO, FileDAO fileDAO, WordDAO wordDAO, FilesWordsDAO filesWordsDAO) {
        this.repositoryDAO = repositoryDAO;
        this.fileDAO = fileDAO;
        this.wordDAO = wordDAO;
        this.filesWordsDAO = filesWordsDAO;
    }

    //    @Autowired
//    public IndexService(JenkinsClient jenkinsClient, SaveDAO saveDAO) {
//        this.jenkinsClient = jenkinsClient;
//        this.saveDAO = saveDAO;
//    }

    public void createIndex() throws DAOException {

//        try {
//
//            ZipFile zipFile = jenkinsClient.getRepository(
//                    "https://github.com/wexxwebb/sport_schedule/archive/spring_security_rmi_hibernate.zip",
//                    "sport_schedule");
//
//            ZipFile zipFile;
//
//            zipFile = new ZipFile("zip/sport_schedule.zip");
//            Repository repository = new Repository();
//            repository.setName("sport_schedule");
//            repository.setAddress("zip/sport_schedule.zip");
//            saveDAO.persist(repository);
//
//            Indexer indexer;
//
//            indexer = new Indexer(zipFile, repository);
//            Thread thread;
//            thread = new Thread(indexer);
//            thread.start();
//
//            zipFile = new ZipFile("zip/RoutingCore.zip");
//            repository = new Repository();
//            repository.setName("RoutingCore");
//            repository.setAddress("zip/RoutingCore.zip");
//            saveDAO.persist(repository);
//
//            indexer = new Indexer(zipFile, repository);
//            thread = new Thread(indexer);
//            thread.start();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        executorService = Executors.newFixedThreadPool(10);

        ThrottlingController throttlingController = new ThrottlingController(futures);
        Thread thread = new Thread(throttlingController);
        thread.start();

        int index = 0;
        List<Repository> list = new ArrayList<>();
        Repository repository1 = new Repository();
        repository1.setName("RoutingCore");
        repository1.setAddress("zip/RoutingCore.zip");
        list.add(repository1);

        Repository repository2 = new Repository();
        repository2.setName("AmberAdapter");
        repository2.setAddress("zip/AmberAdapter.zip");
        list.add(repository2);

        Repository repository3 = new Repository();
        repository3.setName("ApiManager");
        repository3.setAddress("zip/ApiManager.zip");
        list.add(repository3);

        Repository repository4 = new Repository();
        repository4.setName("sport_schedule");
        repository4.setAddress("zip/sport_schedule.zip");
        list.add(repository4);

        try {
//            Jobs jobs = jenkinsClient.getJobs();
//            for (Job job : jobs.getJobs()) {
            for (Repository repository : list) {
//                Repository repository;
//                try {
//                    repository = jenkinsClient.getRepository(job);
//                } catch (JenkinsClientException e) {
//                    continue;
//                }

//                saveDAO.persist(repository);
                repositoryDAO.save(repository);


                if (futures.size() > 10) {
                    synchronized (IndexService.class) {
                        try {
                            IndexService.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Indexer indexer = new Indexer(fileDAO, wordDAO, filesWordsDAO, repository);
                futures.add(new Fut(executorService.submit(indexer), repository.getName()));
                if (++index > 2) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!futures.isEmpty()) {
            synchronized (IndexService.class) {
                try {
                    IndexService.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        executorService.shutdown();
        throttlingController.stop();
    }

//    private int count() {
//        int count = 0;
//        for (Word word : words.values()) {
//            count = count + word.getFiles().size();
//        }
//        return count;
//    }

    private class ThrottlingController implements Runnable {

        private List<Fut> futures;
        private boolean stop = false;

        private ThrottlingController(List<Fut> futures) {
            this.futures = futures;
        }

        void stop() {
            this.stop = true;
        }

        @Override
        public void run() {
            while (!stop) {
                try {
                      for (Fut fut : futures) {
                        if (fut.getFuture().isDone()) {
                            synchronized (IndexService.class) {
                                IndexService.class.notifyAll();
                            }
                            futures.remove(fut);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void clearIndex() {
//        saveDAO.clear();
    }
}
