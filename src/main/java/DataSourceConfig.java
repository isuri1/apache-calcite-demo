package com.zuhlke.apachecalcitedemo.src.main.java;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceConfig {

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    static {
        config.setJdbcUrl("jdbc:h2:file:~/data/h2_2;MODE=MYSQL;DB_CLOSE_ON_EXIT=FALSE");
        config.setUsername("sa");
        config.setPassword("");
        config.setMaximumPoolSize(6);
        config.setMinimumIdle(6);
        config.setIdleTimeout(30000);
        config.setMaxLifetime(2000000);
        config.setConnectionTimeout(3000);
        config.setPoolName("Hikari-IMS");
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}