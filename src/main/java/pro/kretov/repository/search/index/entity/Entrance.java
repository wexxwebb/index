package pro.kretov.repository.search.index.entity;

import java.util.Objects;

public class Entrance {

    private String id;
    private Repository repository;
    private File file;
    private Word word;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrance entrance = (Entrance) o;
        return repository.equals(entrance.repository) &&
                file.equals(entrance.file) &&
                word.equals(entrance.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repository, file, word);
    }
}
