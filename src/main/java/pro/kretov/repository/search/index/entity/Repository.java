package pro.kretov.repository.search.index.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksandr Kretov
 * @date 25.02.2019
 */

@Entity
public class Repository {

    private String id;

    private String name;

    private String address;

    private List<File> files = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @OneToMany(mappedBy = "repository", targetEntity = File.class)
    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
