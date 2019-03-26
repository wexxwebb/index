package pro.kretov.repository.search.index.entity;

import java.util.Objects;

/**
 * @author Aleksandr Kretov
 * @date 25.02.2019
 */

public class Repository {

    private String name;

    private String address;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return name.equals(that.name) &&
                address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }
}
