package lk.ijse.util;


import lk.ijse.db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionUtil {
    static Connection connection;

    static {
        try {
            connection = DBConnection.getDbConnection().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public TransactionUtil() throws SQLException, ClassNotFoundException {
    }


    public static void startTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public static void rollbackTransaction() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    public static void endTransaction() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }
}
