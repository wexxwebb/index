package pro.kretov.repository.search.index.dto;

import java.util.ArrayList;
import java.util.List;

public class FileDTO {

    private Long id;

    private String name;

    private String content;

    private List<WordDTO> words = new ArrayList<>();

    private RepositoryDTO repository;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<WordDTO> getWords() {
        return words;
    }

    public void setWords(List<WordDTO> words) {
        this.words = words;
    }

    public RepositoryDTO getRepository() {
        return repository;
    }

    public void setRepository(RepositoryDTO repository) {
        this.repository = repository;
    }
}
