package com.zuhlke.apachecalcitedemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AvaticaHttpServerTest {

    private AvaticaHttpServer avaticaHttpServer = new AvaticaHttpServer();

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testCustomImpersonationConfig() throws Exception {
        avaticaHttpServer.createServer();
    }
}