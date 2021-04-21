package com.zuhlke.apachecalcitedemo;

import org.apache.calcite.avatica.jdbc.JdbcMeta;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentMap;

import com.google.common.cache.Cache;

public class JDBCMetaConfig extends JdbcMeta {

    public JDBCMetaConfig(String url, String user, String password) throws SQLException {
        super(url, user, password);
    }

    @Override
    public void openConnection(ConnectionHandle ch, Map<String, String> info) {
        Properties fullInfo = new Properties();
        try {
            final Field field = JdbcMeta.class.getDeclaredField("info");
            field.setAccessible(true);
            fullInfo.putAll((Map<?, ?>) field.get(this));

            if (info != null) {
                fullInfo.putAll(info);
            }

            Field field2 = JdbcMeta.class.getDeclaredField("connectionCache");
            field2.setAccessible(true);
            Cache<String, Connection> connectionCache = (Cache<String, Connection>) field2.get(this);
            ConcurrentMap<String, Connection> cacheAsMap = connectionCache.asMap();

            if (cacheAsMap.containsKey(ch.id)) {
                throw new RuntimeException("Connection already exists: " + ch.id);
            } else {
                try {
                    Field field3 = JdbcMeta.class.getDeclaredField("url");
                    field3.setAccessible(true);
                    Connection conn = this.createConnection((String) field3.get(this), fullInfo);
                    Connection loadedConn = cacheAsMap.putIfAbsent(ch.id, conn);
                    if (loadedConn != null) {
                        conn.close();
                        throw new RuntimeException("Connection already exists: " + ch.id);
                    }
                } catch (SQLException var7) {
                    throw new RuntimeException(var7);
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Connection createConnection(String url, Properties info) throws SQLException {
        final Connection connection = DataSourceConfig.getConnection();
        return connection;
    }
}
