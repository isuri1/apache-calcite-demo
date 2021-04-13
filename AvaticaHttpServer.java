package com.zuhlke.apachecalcitedemo;

import org.apache.calcite.avatica.jdbc.JdbcMeta;
import org.apache.calcite.avatica.remote.Driver;
import org.apache.calcite.avatica.remote.LocalService;
import org.apache.calcite.avatica.server.HttpServer;

import java.sql.SQLException;

public class AvaticaHttpServer {

    private static HttpServer server;

    @SuppressWarnings("unchecked")
    protected void createServer()
            throws SQLException, InterruptedException {
        final JdbcMeta jdbcMeta = new JdbcMeta("jdbc:calcite:model=src/main/resources/schema.json",
                "admin", "admin");
        LocalService service = new LocalService(jdbcMeta);

        server = new HttpServer.Builder()
                .withPort(8080)
                .withHandler(service, Driver.Serialization.PROTOBUF)
                .build();
        server.start();

        Thread.sleep(10000000);
    }
}
