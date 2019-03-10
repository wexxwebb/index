package pro.kretov.repository.search.index.dto;

import pro.kretov.repository.search.index.entity.File;

import java.util.ArrayList;
import java.util.List;

public class RepositoryDTO {

    private Long id;

    private String name;

    private List<File> files = new ArrayList<>();

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

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
