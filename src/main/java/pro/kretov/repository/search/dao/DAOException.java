package pro.kretov.repository.search.dao;

import java.sql.SQLException;

public class DAOException extends Exception {
    public DAOException(Exception e) {
        super(e);
    }

    public DAOException() {
        super();
    }
}
