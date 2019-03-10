package pro.kretov.repository.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.kretov.repository.search.index.dto.FileDTO;
import pro.kretov.repository.search.index.dto.Item;
import pro.kretov.repository.search.index.dto.WordDTO;
import pro.kretov.repository.search.service.IndexService;
import pro.kretov.repository.search.service.SearchService;

import java.io.IOException;
import java.util.List;

/**
 * @author Aleksandr Kretov
 * @date 25.02.2019
 */

@RestController
public class IndexController {

    private IndexService indexService;
    private SearchService searchService;

    @Autowired
    public IndexController(IndexService indexService, SearchService searchService) {
        this.indexService = indexService;
        this.searchService = searchService;
    }

    @GetMapping(value = "/create")
    private void createIndex() {
        indexService.createIndex();
    }

    @GetMapping(value = "/clear")
    private void clearIndex() {
        indexService.clearIndex();
    }

    @GetMapping(value = "/back/getWord")
    private List<WordDTO> search(@RequestParam Long id) {
        return searchService.getWord(id);
    }

    @GetMapping(value = "/back/getFile")
    private FileDTO getFile(@RequestParam Long id) throws IOException {
        return searchService.getFile(id);
    }

    @GetMapping(value = "/back/searchSequence")
    private List<Item> searchSequence(@RequestParam String term) {
        return searchService.searchSequence(term);
    }

    @GetMapping(value = "/back/searchWord")
    private List<WordDTO> searchWord(@RequestParam String sequence) {
        return searchService.searchWord(sequence);
    }
}
