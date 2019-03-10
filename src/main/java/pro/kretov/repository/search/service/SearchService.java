package pro.kretov.repository.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.kretov.repository.search.dao.SearchDAO;
import pro.kretov.repository.search.index.dto.FileDTO;
import pro.kretov.repository.search.index.dto.Item;
import pro.kretov.repository.search.index.dto.WordDTO;

import java.io.IOException;
import java.util.List;

@Service
public class SearchService {

    private SearchDAO searchDAO;

    @Autowired
    public SearchService(SearchDAO searchDAO) {
        this.searchDAO = searchDAO;
    }

    public List<WordDTO> getWord(Long id) {
        return searchDAO.getWord(id);
    }

    public FileDTO getFile(Long id) throws IOException {
        return searchDAO.getFile(id);
    }

    public List<Item> searchSequence(String sequence) {
        return searchDAO.searchSequence(sequence);
    }

    public List<WordDTO> searchWord(String sequense) {
        return searchDAO.searchWord(sequense);
    }
}
