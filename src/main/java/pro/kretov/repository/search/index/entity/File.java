package pro.kretov.repository.search.index.entity;

import java.util.*;

/**
 * @author Aleksandr Kretov
 * @date 25.02.2019
 */

public class File {

    private String id;

    private String name;

    private String content = "";

    private Set<Word> words = new HashSet<>();

    private Repository repository;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
