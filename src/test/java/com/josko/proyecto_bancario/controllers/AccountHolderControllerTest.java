package com.josko.proyecto_bancario.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josko.proyecto_bancario.repositories.AccountHolderRepository;
import com.josko.proyecto_bancario.services.IntegrationTestBase;
import com.josko.proyecto_bancario.services.ValidatorService;
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

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
class AccountHolderControllerTest extends IntegrationTestBase {

    private final ValidatorService validatorService;

    @Autowired
    public AccountHolderControllerTest(MockMvc mockMvc,
                                       WebApplicationContext webApplicationContext,
                                       ObjectMapper objectMapper, AccountHolderRepository accountHolderRepository, ValidatorService validatorService) {
        super(mockMvc, webApplicationContext, objectMapper);
        this.validatorService = validatorService;
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void setTransferToAccount() throws Exception {
        log.info("INTEGRATION_TEST:ACCOUNTHOLDER_CONTROLLER_TEST:setTransferToAccount: POST: Ejecución de una transferencia entre cuentas.");

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/accountholder/SA043/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"accountName\": \"Ernesto\",\n" +
                                "    \"ibanDestination\": \"CH022\",\n" +
                                "    \"balance\": {\n" +
                                "        \"currency\": \"EUR\",\n" +
                                "        \"amount\": 453.23\n" +
                                "    }\n" +
                                "}")
                )
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();

        final Map<String, String> resultSet = validatorService.parseResponse(mvcResult.getResponse().getContentAsString());

/*        assertAll(
                () -> assertEquals(200, mvcResult.getResponse().getStatus()),
                () -> assertEquals("application/json", mvcResult.getResponse().getContentType()),
                () -> assertEquals("Transaction successfully!", parseResponse(mvcResult.getResponse().getContentAsString())
        );
        */

    }


}