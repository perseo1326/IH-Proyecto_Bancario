package com.josko.proyecto_bancario.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTestBase {

    protected MockMvc mockMvc;
    protected final WebApplicationContext webApplicationContext;
    protected final ObjectMapper objectMapper;

    @Autowired
    public IntegrationTestBase(MockMvc mockMvc, WebApplicationContext webApplicationContext, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.webApplicationContext = webApplicationContext;
        this.objectMapper = objectMapper;
    }
}
