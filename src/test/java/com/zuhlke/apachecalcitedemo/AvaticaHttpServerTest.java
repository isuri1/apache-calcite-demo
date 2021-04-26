package com.zuhlke.apachecalcitedemo;

import com.zuhlke.apachecalcitedemo.config.AvaticaHttpServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AvaticaHttpServerTest {

    private AvaticaHttpServer avaticaHttpServer = new AvaticaHttpServer();

    AvaticaHttpServerTest() {
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testCustomImpersonationConfig() throws Exception {
        avaticaHttpServer.createServer();
    }
}