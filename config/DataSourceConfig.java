package com.zuhlke.apachecalcitedemo.config;

import org.apache.calcite.avatica.jdbc.JdbcMeta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    private static final String H2_2_USER_NAME = "sa";
    private static final String H2_2_PWD = "";

    @Bean
    public CustomJdbcMeta customJdbcMeta(@Value("${spring.datasource.hikari.jdbc-url}") String url, Properties properties, DataSource dataSource) throws SQLException {
        return new CustomJdbcMeta(url, properties, dataSource);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public Properties properties(){

        Properties properties = new Properties();
        properties.put("user", H2_2_USER_NAME);
        properties.put("password", H2_2_PWD);
        properties.put(JdbcMeta.ConnectionCacheSettings.MAX_CAPACITY.key(), "10");

        return properties;
    }

//    private static final HikariConfig config = new HikariConfig();
//    private static final HikariDataSource ds;

//    static {
//        config.setJdbcUrl("jdbc:h2:file:~/data/h2_2;MODE=MYSQL;DB_CLOSE_ON_EXIT=FALSE");
//        config.setUsername("sa");
//        config.setPassword("");
//        config.setMaximumPoolSize(6);
//        config.setMinimumIdle(6);
//        config.setIdleTimeout(30000);
//        config.setMaxLifetime(2000000);
//        config.setConnectionTimeout(3000);
//        config.setPoolName("Hikari-IMS");
//        ds = new HikariDataSource(config);
//        System.out.println("********* DataSourceConfig.static");
//    }

//    public static Connection getConnection() throws SQLException {
//        return ds.getConnection();
//    }
}