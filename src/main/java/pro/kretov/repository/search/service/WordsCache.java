package pro.kretov.repository.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.kretov.repository.search.dao.DAOException;
import pro.kretov.repository.search.dao.FilesWordsDAO;
import pro.kretov.repository.search.dao.WordDAO;
import pro.kretov.repository.search.index.entity.File;
import pro.kretov.repository.search.index.entity.Word;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("Duplicates")
@Service
public class WordsCache {

    private Set<Word> cache = Collections.newSetFromMap(new ConcurrentHashMap<>());

    private WordDAO wordDAO;
    private FilesWordsDAO filesWordsDAO;

    @Autowired
    public WordsCache(WordDAO wordDAO) {
        this.wordDAO = wordDAO;
        this.filesWordsDAO = filesWordsDAO;
    }

    public void save(String sequence) throws DAOException {
//        Word word = new Word();
//        word.setSequence(sequence);
//        if (cache.contains(word)) {
//            return cache.
//        } else {
//            word = new Word();
//            word.setSequence(sequence);
//            cache.put(sequence, word);
//            wordDAO.save(word);
////            filesWordsDAO.wire(file, word);
//            return word;
//        }
    }

    private class WordSaver implements Runnable {

        @Override
        public void run() {

        }
    }
}
