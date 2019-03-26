package pro.kretov.repository.search.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.kretov.repository.search.dao.DAOException;
import pro.kretov.repository.search.dao.SearchDAO;
import pro.kretov.repository.search.index.dto.Item;
import pro.kretov.repository.search.index.entity.File;
import pro.kretov.repository.search.index.entity.Word;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class SearchService {

    private SearchDAO searchDAO;

    @Autowired
    public SearchService(SearchDAO searchDAO) {
        this.searchDAO = searchDAO;
    }

    public List<File> getWord(String sequence) throws DAOException {
        return searchDAO.getWord(sequence);
    }

    public File getFile(String id) throws IOException, DAOException {
        File file = searchDAO.getFile(id);
//        ZipFile zipFile = new ZipFile(file.getRepository().getAddress());
//        ZipEntry zipEntry = zipFile.getEntry(file.getName());
//        String content = IOUtils.toString(zipFile.getInputStream(zipEntry), UTF_8);
//        file.setContent(content);
        return file;
    }

    public Collection<Item> searchSequence(String sequence) throws DAOException {
        return searchDAO.searchSequence(sequence);
//        return null;
    }

    public List<Word> searchWord(String sequense) {
//        return searchDAO.searchWord(sequense);
        return null;
    }
}
