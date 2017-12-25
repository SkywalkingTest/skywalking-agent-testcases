package org.apache.skywalking.testcase.httpclientasync;

import java.sql.SQLException;
import org.apache.log4j.Logger;

public class SQLUtils {
    private static Logger logger = Logger.getLogger(SQLUtils.class);

    public static void init() {
        final String CREATE_TABLE_SQL = "CREATE TABLE test_01(\n" +
            "id VARCHAR(1) PRIMARY KEY, \n" +
            "value VARCHAR(1) NOT NULL)";
        String INSERT_DATA_SQL = "INSERT INTO test_01(id, value) VALUES(?,?)";
        SQLExecutor sqlExecute = null;
        try {
            sqlExecute = new SQLExecutor();
            sqlExecute.createTable(CREATE_TABLE_SQL);
            sqlExecute.insertData(INSERT_DATA_SQL, "1", "1");

        } catch (SQLException e) {
            logger.error("Failed to execute sql.", e);
        } finally {
            if (sqlExecute != null) {
                try {
                    sqlExecute.closeConnection();
                } catch (SQLException e) {
                    logger.error("Failed to close connection.", e);
                }
            }
        }
    }

    public static void query() {
        String QUERY_DATA_SQL = "SELECT id, value FROM test_01 WHERE id=?";
        SQLExecutor sqlExecute = null;
        try {
            sqlExecute = new SQLExecutor();
            sqlExecute.queryData(QUERY_DATA_SQL, "1");
        } catch (SQLException e) {
            logger.error("Failed to execute sql.", e);
        } finally {
            if (sqlExecute != null) {
                try {
                    sqlExecute.closeConnection();
                } catch (SQLException e) {
                    logger.error("Failed to close connection.", e);
                }
            }
        }
    }

    public static void drop() {

        String DELETE_DATA_SQL = "DELETE FROM test_01 WHERE id=?";
        String DROP_TABLE_SQL = "DROP table test_01";
        SQLExecutor sqlExecute = null;
        try {
            sqlExecute = new SQLExecutor();
            sqlExecute.dropTable(DROP_TABLE_SQL);

        } catch (SQLException e) {
            logger.error("Failed to execute sql.", e);
        } finally {
            if (sqlExecute != null) {
                try {
                    sqlExecute.closeConnection();
                } catch (SQLException e) {
                    logger.error("Failed to close connection.", e);
                }
            }
        }
    }

}
