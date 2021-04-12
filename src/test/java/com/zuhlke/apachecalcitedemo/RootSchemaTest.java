package com.zuhlke.apachecalcitedemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RootSchemaTest {

    private RootSchema rootSchema;

    @BeforeEach
    void setUp() {

        rootSchema = new RootSchema();
    }

    @Test
    public void test_H2_1_FromClient() throws SQLException, ClassNotFoundException {
        rootSchema.connectToDataSource("H2_1", "select * from H2_1.ORDERS limit 10");
    }

    @Test
    public void test_H2_2_FromClient() throws SQLException, ClassNotFoundException {
        rootSchema.connectToDataSource("H2_2", "select * from H2_2.USERS limit 10");
    }
}