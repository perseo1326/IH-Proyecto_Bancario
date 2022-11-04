package com.josko.proyecto_bancario.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josko.proyecto_bancario.services.IntegrationTestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
class AuthControllerTest extends IntegrationTestBase {


    @Autowired
    public AuthControllerTest(MockMvc mockMvc,
                                WebApplicationContext webApplicationContext,
                                ObjectMapper objectMapper ) {
        super(mockMvc, webApplicationContext, objectMapper);
    }



    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test de funcionamiento : Peticion GET NO adecuada, url correcta, sin autenticacion ")
    void authenticateUser() throws Exception {
        log.info("INTEGRATION_TEST:AUTH_CONTROLLER:authenticateUser: GET prueba inicial de entorno de testing ");
        MvcResult mvcResult = mockMvc.perform( get("/api/v1/signin")
                )
                .andDo(print())
                .andExpectAll(
                        status().is4xxClientError()
                )
                .andReturn();

        assertEquals(405, mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName("POST: Petición de logging correcta.")
    void getAuthenticationOK() throws Exception {
        log.info("INTEGRATION_TEST:AUTH_CONTROLLER:getAuthenticationOK: POST: Petición de logging.");
        MvcResult mvcResult = mockMvc.perform( post("/api/v1/signin")
                        .content("{\n" +
                            "    \"username\": \"Alfredo\",\n" +
                            "    \"password\": \"123456\"\n" +
                            "} ")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.type").value("Bearer")
                )
                .andReturn();

        System.out.println("\n\nmvcResult:\n" + mvcResult.getResponse().getContentAsString());
        System.out.println("\n\n");

        assertAll(
                () -> assertEquals(200, mvcResult.getResponse().getStatus()),
                () -> assertEquals("application/json", mvcResult.getResponse().getContentType()),
                () -> assertTrue(mvcResult.getResponse().getContentAsString().contains("Bearer"))
        );
    }

    @Test
    @DisplayName("GET: Admin Bienvenida.")
    void getAdminWelcome() throws Exception {
/*
        log.info("INTEGRATION_TEST:AUTH_CONTROLLER:getAuthenticationOK: POST: Petición de logging.");
        MvcResult mvcResult = mockMvc.perform( post("/api/v1/signin")
                )
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();

        System.out.println("\n\nmvcResult:\n" + mvcResult.getResponse().getContentAsString());
        System.out.println("\n\n");

        assertAll(
                () -> assertEquals(200, mvcResult.getResponse().getStatus()),
                () -> assertEquals("application/json", mvcResult.getResponse().getContentType()),
                () -> assertTrue(mvcResult.getResponse().getContentAsString().contains("Hola, Bienvenido a la interfaz bancaria."))
        );
*/
    }









}