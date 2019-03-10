package pro.kretov.repository.search.index.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksandr Kretov
 * @date 25.02.2019
 */

@Entity
@Table(indexes = {@Index(columnList = "sequence", name = "word_sequence_index")})
public class Word {

    private Long id;

    private String sequence;

    private List<File> files = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

//    @JsonInclude(ALWAYS)
    @ManyToMany(mappedBy = "words")
    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    @Override
    public int hashCode() {
        return getSequence().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && ((Word) obj).getSequence() != null && getSequence().equals(obj);
    }
}
