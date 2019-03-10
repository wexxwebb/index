package pro.kretov.repository.search.index.dto;

import pro.kretov.repository.search.index.entity.File;
import pro.kretov.repository.search.index.entity.Word;

import java.util.ArrayList;
import java.util.List;

public class WordDTO {

    private String sequence;
    private List<FileDTO> files = new ArrayList<>();
    private Long id;

    public WordDTO(Word word) {
        sequence = word.getSequence();
        for (File file : word.getFiles()) {
            FileDTO fileDTO = new FileDTO();
            fileDTO.setId(file.getId());
            fileDTO.setName(file.getName());
            RepositoryDTO repositoryDTO = new RepositoryDTO();
            repositoryDTO.setName(file.getRepository().getName());
            repositoryDTO.setId(file.getRepository().getId());
            fileDTO.setRepository(repositoryDTO);
            files.add(fileDTO);
        }
        id = word.getId();
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public List<FileDTO> getFiles() {
        return files;
    }

    public void setFiles(List<FileDTO> files) {
        this.files = files;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
