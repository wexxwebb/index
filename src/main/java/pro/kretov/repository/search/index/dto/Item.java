package pro.kretov.repository.search.index.dto;

import java.util.Objects;

public class Item implements Comparable {


    private String label;

    public Item(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(label, item.label);
    }

    @Override
    public int hashCode() {
        return getLabel().hashCode();
    }

    @Override
    public int compareTo(Object o) {
        return label.compareTo(((Item)o).label);
    }
}
