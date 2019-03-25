package pro.kretov.repository.search.index.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordTest {

    @Test
    void equals() {
        Word wordA = new Word();
        wordA.setSequence("alpha");

        Word wordB = new Word();
        wordB.setSequence("alpha");

        assertEquals(wordA, wordB);

        wordB.setSequence("omega");

        assertNotEquals(wordA, wordB);
    }
}