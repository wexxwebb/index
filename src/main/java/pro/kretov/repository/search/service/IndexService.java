package pro.kretov.repository.search.service;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.kretov.repository.search.dao.SaveDAO;
import pro.kretov.repository.search.index.entity.File;
import pro.kretov.repository.search.index.entity.Repository;
import pro.kretov.repository.search.index.entity.Word;
import pro.kretov.repository.search.jenkins.JenkinsClient;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static java.nio.charset.StandardCharsets.UTF_8;

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
    private SaveDAO saveDAO;

    private Map<String, Word> words;

    @Autowired
    public IndexService(JenkinsClient jenkinsClient, SaveDAO saveDAO) {
        this.jenkinsClient = jenkinsClient;
        this.saveDAO = saveDAO;
    }

    public void createIndex() {

        try {

            words = new ConcurrentHashMap<>();

//            ZipFile zipFile = jenkinsClient.getZip(
//                    "https://github.com/wexxwebb/sport_schedule/archive/spring_security_rmi_hibernate.zip",
//                    "sport_schedule");

            ZipFile zipFile;

            zipFile = new ZipFile("zip/sport_schedule.zip");
            Repository repository = new Repository();
            repository.setName("sport_schedule");
            repository.setAddress("zip/sport_schedule.zip");
            saveDAO.persist(repository);

            Indexer indexer;

            indexer = new Indexer(zipFile, repository);
            Thread thread;
            thread = new Thread(indexer);
            thread.start();

            zipFile = new ZipFile("zip/RoutingCore.zip");
            repository = new Repository();
            repository.setName("RoutingCore");
            repository.setAddress("zip/RoutingCore.zip");
            saveDAO.persist(repository);

            indexer = new Indexer(zipFile, repository);
            thread = new Thread(indexer);
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
//        executorService = Executors.newFixedThreadPool(10);
//        ThrottlingController throttlingController = new ThrottlingController(futures);
//        Thread thread = new Thread(throttlingController);
//        thread.start();

//        int index = 0;
//        try {
//            Jobs jobs = jenkinsClient.getJobs();
//            for (Job job : jobs.getJobs()) {
//                ZipFile zipFile;
//                try {
//                    zipFile = jenkinsClient.getZip(job);
//                } catch (JenkinsClientException e) {
//                    continue;
//                }
//                Repository repository = new Repository();
//                repository.setName(job.getName());
//
////                if (futures.size() > 10) {
////                    synchronized (IndexService.class) {
////                        try {
////                            IndexService.class.wait();
////                        } catch (InterruptedException e) {
////                            e.printStackTrace();
////                        }
////                    }
////                }
////                Indexer indexer = new Indexer(zipFile, repository);
////                futures.add(new Fut(executorService.submit(indexer), repository.getName()));
//                Indexer indexer = new Indexer(zipFile, repository);
//                indexer.index();
//                if (++index > 16) {
//                    break;
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        while (!futures.isEmpty()) {
//
//        }
//
//        executorService.shutdown();
//        throttlingController.stop();
    }

//    private int count() {
//        int count = 0;
//        for (Word word : words.values()) {
//            count = count + word.getFiles().size();
//        }
//        return count;
//    }

//    private class ThrottlingController implements Runnable {
//
//        private List<Fut> futures;
//        private boolean stop = false;
//
//        private ThrottlingController(List<Fut> futures) {
//            this.futures = futures;
//        }
//
//        void stop() {
//            this.stop = true;
//        }
//
//        @Override
//        public void run() {
//            while (!stop) {
//                try {
//                    for (Fut fut : futures) {
//                        if (fut.getFuture().isDone()) {
//                            synchronized (IndexService.class) {
//                                IndexService.class.notifyAll();
//                            }
//                            futures.remove(fut);
//                            break;
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    private class Indexer implements Runnable {

        private ZipFile zipFile;
        private Repository repository;

        private Indexer(ZipFile zipFile, Repository repository) {
            this.zipFile = zipFile;
            this.repository = repository;
        }

        @Override
        public void run() {
            try {
                index();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void index() throws IOException {
            Enumeration<? extends ZipEntry> zipEntryEnumeration = zipFile.entries();
            while (zipEntryEnumeration.hasMoreElements()) {
                ZipEntry zipEntry = zipEntryEnumeration.nextElement();

                if (!(zipEntry.getName().endsWith(".java") ||
                    zipEntry.getName().endsWith(".xml") ||
                    zipEntry.getName().endsWith(".properties") ||
                    zipEntry.getName().endsWith(".MF") ||
                    zipEntry.getName().endsWith(".xsd") ||
                    zipEntry.getName().endsWith(".wsdl") ||
                    zipEntry.getName().endsWith(".xslt"))) {
                    continue;
                }

                File file = new File();
                file.setName(zipEntry.getName());
                file.setRepository(repository);

                Pattern pattern = Pattern.compile("[@0-9A-Za-z-_]+");
                String content = IOUtils.toString(zipFile.getInputStream(zipEntry), UTF_8);
                Matcher matcher = pattern.matcher(content);
                while (matcher.find()) {
                    Word word = words.get(matcher.group());
                    if (word != null) {
                        file.getWords().add(word);
                    } else {
                        word = new Word();
                        word.setSequence(matcher.group());
                        file.getWords().add(word);
                        saveDAO.persist(word);
                        words.put(matcher.group(), word);
                    }
                }
                saveDAO.persist(file);
            }
            zipFile.close();
        }
    }

    public void clearIndex() {
        saveDAO.clear();
    }
}
