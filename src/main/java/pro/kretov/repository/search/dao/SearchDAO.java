package pro.kretov.repository.search.dao;

import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.kretov.repository.search.index.dto.FileDTO;
import pro.kretov.repository.search.index.dto.Item;
import pro.kretov.repository.search.index.dto.WordDTO;
import pro.kretov.repository.search.index.entity.File;
import pro.kretov.repository.search.index.entity.Word;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class SearchDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public SearchDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<WordDTO> getWord(Long id) {
        List<WordDTO> result = new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();
        NativeQuery<Word> nativeQuery =
                session.createNativeQuery("select * from word where id = :id", Word.class);
        nativeQuery.setParameter("id", id);
        for (Word word : nativeQuery.getResultList()) {
            result.add(new WordDTO(word));
        }
        return result;
    }

    @Transactional
    public FileDTO getFile(Long id) throws IOException {
        Session session = sessionFactory.getCurrentSession();
        File file = session.get(File.class, id);
        if (file != null) {
            FileDTO fileDTO = new FileDTO();
            fileDTO.setId(file.getId());
            fileDTO.setName(file.getName());

            ZipFile zipFile = new ZipFile(file.getRepository().getAddress());
            ZipEntry zipEntry = zipFile.getEntry(file.getName());

            String content = IOUtils.toString(zipFile.getInputStream(zipEntry), UTF_8);
            fileDTO.setContent(content);
            return fileDTO;
        }
        return null;
    }

    @Transactional
    public List<Item> searchSequence(String sequence) {
        List<Item> result = new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();

        NativeQuery<Word> nativeQuery =
                session.createNativeQuery(
                        "select * from repository_index.public.word where sequence like :keyword limit 25",
                        Word.class);
        nativeQuery.setParameter("keyword", sequence + "%");

        for (Word word : nativeQuery.getResultList()) {
            result.add(new Item(word.getId(), word.getSequence()));
        }

        return result;
    }

    @Transactional
    public List<WordDTO> searchWord(String sequense) {
        List<WordDTO> result = new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();
        NativeQuery<Word> nativeQuery =
                session.createNativeQuery("select * from word where sequence = :keyword", Word.class);
        nativeQuery.setParameter("keyword", sequense);
        for (Word word : nativeQuery.getResultList()) {
            result.add(new WordDTO(word));
        }
        return result;
    }
}
