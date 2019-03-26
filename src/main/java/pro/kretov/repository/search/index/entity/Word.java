package pro.kretov.repository.search.index.entity;

import java.io.Serializable;

/**
 * @author Aleksandr Kretov
 * @date 25.02.2019
 */

public class Word {

    private String sequence;

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public int hashCode() {
        return getSequence().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null &&
                ((Word) obj).getSequence() != null &&
                getSequence().equals(((Word)obj).getSequence());
    }
}
