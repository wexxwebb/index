package pro.kretov.repository.search.service;

import org.apache.commons.io.IOUtils;
import pro.kretov.repository.search.dao.DAOException;
import pro.kretov.repository.search.dao.FileDAO;
import pro.kretov.repository.search.dao.FilesWordsDAO;
import pro.kretov.repository.search.dao.WordDAO;
import pro.kretov.repository.search.index.entity.File;
import pro.kretov.repository.search.index.entity.Repository;
import pro.kretov.repository.search.index.entity.Word;

import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Indexer implements Runnable {

    private ZipFile zipFile;
    private FilesWordsDAO filesWordsDAO;
    private Repository repository;
    private final FileDAO fileDAO;

    public Indexer(FileDAO fileDAO, WordDAO wordDAO,
                   FilesWordsDAO filesWordsDAO, Repository repository) throws IOException {
        this.fileDAO = fileDAO;
        this.filesWordsDAO = filesWordsDAO;
        this.repository = repository;
        zipFile = new ZipFile(repository.getAddress());
    }

    @Override
    public void run() {
        try {
            index();
        } catch (IOException | DAOException e) {
            e.printStackTrace();
        }
    }

    private void index() throws IOException, DAOException {

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
            fileDAO.save(file);

            Pattern pattern = Pattern.compile("[@0-9A-Za-z\\-_]+");
            String content = IOUtils.toString(zipFile.getInputStream(zipEntry), UTF_8);
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                if (matcher.group().length() > 1) {
                    Word word = new Word();
                    word.setSequence(matcher.group());
                    file.getWords().add(word);
                }
            }
            filesWordsDAO.wire(file);
        }
        zipFile.close();
    }
}
