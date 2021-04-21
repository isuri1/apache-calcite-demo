package com.zuhlke.apachecalcitedemo;

import org.apache.calcite.avatica.remote.Driver;
import org.apache.calcite.avatica.remote.LocalService;
import org.apache.calcite.avatica.server.HttpServer;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

@Configuration
public class AvaticaHttpServer {

    @PostConstruct
    protected static void createServer() throws SQLException {

        final JDBCMetaConfig jdbcMeta = new JDBCMetaConfig("jdbc:h2:file:~/data/h2_2;MODE=MYSQL;DB_CLOSE_ON_EXIT=FALSE",
                "sa", "");
        LocalService service = new LocalService(jdbcMeta);

        HttpServer server = new HttpServer.Builder()
                .withPort(8080)
                .withHandler(service, Driver.Serialization.JSON)
                .build();
        server.start();

        final JDBCMetaConfig jdbcMeta2 = new JDBCMetaConfig("jdbc:h2:file:~/data/h2_1;MODE=MYSQL;DB_CLOSE_ON_EXIT=FALSE",
                "sa", "");
        LocalService service2 = new LocalService(jdbcMeta);

        HttpServer server2 = new HttpServer.Builder()
                .withPort(8085)
                .withHandler(service2, Driver.Serialization.JSON)
                .build();
        server2.start();
    }
}
