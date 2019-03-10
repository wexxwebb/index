package pro.kretov.repository.search.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author Aleksandr Kretov
 * @date 25.02.2019
 */

@Component
public class SaveDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public SaveDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Long save(Object entity) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(entity);
        session.flush();
        return id;
    }

    public void update(Object entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
        session.flush();
    }

    @Transactional
    public void persist(Object entity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(entity);
        session.flush();
    }

    @Transactional
    public void clear() {
        Session session = sessionFactory.getCurrentSession();
        NativeQuery nativeQuery = session.createNativeQuery(
                "delete from file_word;" +
                "delete from word;" +
                "delete from file;" +
                "delete from repository");
        nativeQuery.executeUpdate();
        session.flush();
    }

    @Transactional
    public void saveOrUpdate(Object entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
        session.flush();
    }

    public void saveAll(Collection entities) {
//        for (Object entity : entities) {
//            try {
//                session.save(entity);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }
}
