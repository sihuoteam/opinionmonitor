package com.hhhy.db;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Connection;
import java.util.Properties;

/**
 * 数据库连接池
 * 
 * @author chenlingpeng
 * 
 */
public class ConnectionSource {
    private static final Logger logger = Logger.getLogger(ConnectionSource.class);

    private static BasicDataSource dataSource = null;

    private ConnectionSource() {
    }

    public static synchronized void init() {
        logger.info("init ConnectionSource...");
        if (dataSource != null) {
            try {
                dataSource.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            dataSource = null;
        }

        try {
            Properties p = new Properties();
            p.load(ConnectionSource.class.getClassLoader().getResourceAsStream(
                    "db.properties"));

            dataSource = (BasicDataSource) BasicDataSourceFactory
                    .createDataSource(p);

        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("init ConnectionSource success...");
    }

    public static synchronized Connection getConnection() throws SQLException {
        if (dataSource == null) {
            init();
        }
        Connection conn = null;
        if (dataSource != null) {
            conn = dataSource.getConnection();
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        ConnectionSource.getConnection();
    }
}
