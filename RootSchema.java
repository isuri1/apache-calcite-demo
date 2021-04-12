package com.zuhlke.apachecalcitedemo;

import org.apache.calcite.adapter.jdbc.JdbcSchema;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.Properties;

public class RootSchema {

    public void connectToDataSource (String schemaName, String sql) throws ClassNotFoundException, SQLException, SQLException {
        Class.forName("org.apache.calcite.jdbc.Driver");
        Properties info = new Properties();
        info.setProperty("lex", "JAVA");
        Connection connection =
                DriverManager.getConnection("jdbc:calcite:", info);
        CalciteConnection calciteConnection =
                connection.unwrap(CalciteConnection.class);
        SchemaPlus rootSchema = calciteConnection.getRootSchema();
        Class.forName("org.h2.Driver");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:calcite:model=src/main/resources/schema.json");
        dataSource.setUsername("admin");
        dataSource.setPassword("admin");
        Schema schema = JdbcSchema.create(rootSchema, schemaName, dataSource,
                null, schemaName);
        rootSchema.add(schemaName, schema);
        Statement statement = calciteConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                sql);

        while(resultSet.next())
        {
            System.out.println(resultSet.getString("ID"));
            System.out.println(resultSet.getString("NAME"));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
