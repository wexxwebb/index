package pro.kretov.repository.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.kretov.repository.search.dao.DAOException;
import pro.kretov.repository.search.index.dto.Item;
import pro.kretov.repository.search.index.entity.File;
import pro.kretov.repository.search.index.entity.Word;
import pro.kretov.repository.search.service.IndexService;
import pro.kretov.repository.search.service.SearchService;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author Aleksandr Kretov
 * @date 25.02.2019
 */

@RestController
public class IndexController {

    private IndexService indexService;
    private SearchService searchService;

//    @Autowired
//    public IndexController(IndexService indexService) {
//
//        this.indexService = indexService;
//    }

    @Autowired
    public IndexController(IndexService indexService, SearchService searchService) {
        this.indexService = indexService;
        this.searchService = searchService;
    }

    @GetMapping(value = "/create")
    private void createIndex() throws DAOException {
        indexService.createIndex();
    }

    @GetMapping(value = "/clear")
    private void clearIndex() {
        indexService.clearIndex();
    }

    @GetMapping(value = "/back/getWord")
    private List<File> search(@RequestParam String sequence) throws DAOException {
        return searchService.getWord(sequence);
    }

    @GetMapping(value = "/back/getFile")
    private File getFile(@RequestParam String id) throws IOException, DAOException {
        return searchService.getFile(id);
    }

    @GetMapping(value = "/back/searchSequence")
    private Collection<Item> searchSequence(@RequestParam String term) throws DAOException {
        return searchService.searchSequence(term);
    }

    @GetMapping(value = "/back/searchWord")
    private List<Word> searchWord(@RequestParam String sequence) {
        return searchService.searchWord(sequence);
    }
}
