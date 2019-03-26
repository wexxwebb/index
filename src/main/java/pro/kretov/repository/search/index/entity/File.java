package pro.kretov.repository.search.index.entity;

/**
 * @author Aleksandr Kretov
 * @date 25.02.2019
 */

public class File {

    private String name;

    private String content = "";

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
}
