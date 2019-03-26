package pro.kretov.repository.search.service;

import org.apache.commons.io.IOUtils;
import pro.kretov.repository.search.dao.DAOException;
import pro.kretov.repository.search.dao.EntranceDAO;
import pro.kretov.repository.search.index.entity.Entrance;
import pro.kretov.repository.search.index.entity.File;
import pro.kretov.repository.search.index.entity.Repository;
import pro.kretov.repository.search.index.entity.Word;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Indexer implements Runnable {

    private ZipFile zipFile;
    private EntranceDAO entranceDAO;
    private Repository repository;

    public Indexer(EntranceDAO entranceDAO,
                   Repository repository) throws IOException {
        this.entranceDAO = entranceDAO;
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

            Set<Entrance> entrances = new HashSet<>();

            File file = new File();
            file.setName(zipEntry.getName());

            Pattern pattern = Pattern.compile("[@0-9A-Za-z\\-_]+");
            String content = IOUtils.toString(zipFile.getInputStream(zipEntry), UTF_8);
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                if (matcher.group().length() > 1) {
                    Word word = new Word();
                    word.setSequence(matcher.group());
                    Entrance entrance = new Entrance();
                    entrance.setWord(word);
                    entrance.setFile(file);
                    entrance.setRepository(repository);
                    entrances.add(entrance);
                }
            }
            entranceDAO.save(entrances);
        }
        zipFile.close();
    }
}
