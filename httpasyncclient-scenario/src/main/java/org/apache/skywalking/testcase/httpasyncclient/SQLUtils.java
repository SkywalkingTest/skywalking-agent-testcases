package org.apache.skywalking.testcase.httpasyncclient;

import java.sql.SQLException;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SQLUtils {
    private static Logger logger = Logger.getLogger(SQLUtils.class);

    @Autowired
    private SQLExecutor sqlExecutor;

    private static SQLExecutor sqlExecute;

    @PostConstruct
    public void setUp() {
        sqlExecute = sqlExecutor;
        drop();
        init();
    }

    public static void init() {
        final String CREATE_TABLE_SQL = "CREATE TABLE test_01(\n" +
            "id VARCHAR(1) PRIMARY KEY, \n" +
            "value VARCHAR(1) NOT NULL)";
        String INSERT_DATA_SQL = "INSERT INTO test_01(id, value) VALUES(?,?)";
        try {
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
        try {
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
        String DROP_TABLE_SQL = "DROP table test_01";
        try {
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
