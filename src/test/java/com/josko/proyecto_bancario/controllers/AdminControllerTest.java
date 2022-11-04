package com.josko.proyecto_bancario.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josko.proyecto_bancario.models.AccountHolder;
import com.josko.proyecto_bancario.repositories.AccountHolderRepository;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
class AdminControllerTest extends IntegrationTestBase {

    private final AccountHolderRepository accountHolderRepository;
    @Autowired
    public AdminControllerTest(MockMvc mockMvc,
                               WebApplicationContext webApplicationContext,
                               ObjectMapper objectMapper, AccountHolderRepository accountHolderRepository) {
        super(mockMvc, webApplicationContext, objectMapper);
        this.accountHolderRepository = accountHolderRepository;
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }

    private HashMap<String, String> parseResponse(String result) {

        HashMap<String, String> resultSet = new HashMap<>();
        result = result.replaceAll("[{}\"\\[\\]]", "");

        String[] pairs = result.split(",");

        for ( String a : pairs ) {
            String[] pair = a.split(":");
            if (pair.length == 1)
                pair = new String[]{pair[0], ""};
            resultSet.put(pair[0],pair[1]);
        }

        return resultSet;
    }

    @Test
    void adminWelcome() throws Exception {
        log.info("INTEGRATION_TEST:ADMIN_CONTROLLER_TEST:adminWelcome: GET: Bienvenida Admin.");
        MvcResult mvcResult = mockMvc.perform( get("/api/v1/admins/welcome")
                )
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType("text/plain;charset=UTF-8")
                )
                .andReturn();

        System.out.println("\n\nmvcResult:\n" + mvcResult.getResponse().getContentAsString());
        System.out.println("\n\n");

        assertAll(
                () -> assertEquals(200, mvcResult.getResponse().getStatus()),
                () -> assertEquals("text/plain;charset=UTF-8", mvcResult.getResponse().getContentType()),
                () -> assertTrue(mvcResult.getResponse().getContentAsString().contains("Hola, Bienvenido a la interfaz bancaria."))
        );
    }

    @Test
    void createThirdPartyUser() throws Exception {
        log.info("INTEGRATION_TEST:ADMIN_CONTROLLER_TEST:createThirdPartyUser: POST: Creacion nuevo usuario 'ThirdParty'.");

        HashMap<String, String> newUserData = new HashMap<>();
        newUserData.put("name", "Thorben Janssen");
        newUserData.put("username", "thorben");
        newUserData.put("email", "thorben@email.com");
        newUserData.put("password", "tttt");
        newUserData.put("hashedKey", "tttt");

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/admins/newthirdparty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUserData))
                )
                .andDo(print())
                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.name").value("Thorben Janssen"),
                        jsonPath("$.username").value("thorben")
                )
                .andReturn();

//        var y = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<HashMap<String, String>>() { } );
        final Map<String, String> resultSet = parseResponse(mvcResult.getResponse().getContentAsString());

        assertAll(
                () -> assertEquals(201, mvcResult.getResponse().getStatus()),
                () -> assertEquals("application/json", mvcResult.getResponse().getContentType()),
                () -> assertEquals("thorben", resultSet.get("username"))
        );
    }

    @Test
    void createAccountHolderUser() throws Exception {
        log.info("INTEGRATION_TEST:ADMIN_CONTROLLER_TEST:createAccountHolderUser: POST: Creación nuevo usuario 'AccountHolder'.");

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/admins/newaccountholder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"Agatha Christie\",\n" +
                                "    \"username\": \"agatha\",\n" +
                                "    \"email\": \"agatha@email.com\",\n" +
                                "    \"password\": \"123456\",\n" +
                                "    \"birthDate\": \"1890-09-15\",\n" +
                                "    \"mainAddress\": {\n" +
                                "        \"country\": \"Reino Unido\",\n" +
                                "        \"city\": \" Winterbrook \", \n" +
                                "        \"street\": \"Wallingford\",\n" +
                                "        \"houseNumber\": \"9\", \n" +
                                "        \"homeUnit\": \"A\",\n" +
                                "        \"comment\": \"La casa de alguien en Reino Unido.\"\n" +
                                "    },\n" +
                                "    \"secondaryAddress\": {\n" +
                                "        \"country\": \"Mexico\",\n" +
                                "        \"city\": \"Tijuana\", \n" +
                                "        \"street\": \"Cactus\",\n" +
                                "        \"houseNumber\": \"97\", \n" +
                                "        \"homeUnit\": \"3A\",\n" +
                                "        \"comment\": \"Casa Verde con puertas y ventanas rojas.\"\n" +
                                "    }\n" +
                                "}")
                )
                .andDo(print())
                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.name").value("Agatha Christie"),
                        jsonPath("$.username").value("agatha")
                )
                .andReturn();

        final Map<String, String> resultSet = parseResponse(mvcResult.getResponse().getContentAsString());
        Long newUserId = Long.parseLong( resultSet.get("id"));

        Optional<AccountHolder> newUser = accountHolderRepository.findById(newUserId);

        assertAll(
                () -> assertEquals(201, mvcResult.getResponse().getStatus()),
                () -> assertEquals("application/json", mvcResult.getResponse().getContentType()),
                () -> assertEquals("Agatha Christie", newUser.get().getName())
        );
    }

    @Test
    @DisplayName("Creación de una nueva cuenta 'Savings'")
    void createNewSavingsAccount() throws Exception {
        log.info("INTEGRATION_TEST:ADMIN_CONTROLLER_TEST:createNewSavingsAccount: POST: Creación de una nueva cuenta 'Savings'.");

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/admins/accountholders/7/newsavings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"secondaryOwner\": null,\n" +
                                "    \"iban\": \"SA099\",\n" +
                                "    \"balance\": {\n" +
                                "        \"currency\": \"EUR\",\n" +
                                "        \"amount\": 15.23\n" +
                                "    },\n" +
                                "    \"interestRate\": 0.0049,\n" +
                                "    \"minimumBalanceSavings\": 250.56\n" +
                                "}")
                )
                .andDo(print())
                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();

        final Map<String, String> resultSet = parseResponse(mvcResult.getResponse().getContentAsString());

        assertAll(
                () -> assertEquals(201, mvcResult.getResponse().getStatus()),
                () -> assertEquals("application/json", mvcResult.getResponse().getContentType()),
                () -> assertEquals("SA099", resultSet.get("iban"))
        );
    }

    @Test
    @DisplayName("Error Creación de una nueva cuenta 'Savings'")
    void createNewSavingsAccount_Wrong() throws Exception {

        log.info("INTEGRATION_TEST:ADMIN_CONTROLLER_TEST:createNewSavingsAccount_Wrong: POST: ERROR Creación de una nueva cuenta 'Savings'.");

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/admins/accountholders/3/newsavings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"secondaryOwner\": null,\n" +
                                "    \"iban\": \"SA099\",\n" +
                                "    \"balance\": {\n" +
                                "        \"currency\": \"EUR\",\n" +
                                "        \"amount\": 15.23\n" +
                                "    },\n" +
                                "    \"interestRate\": 0.0049,\n" +
                                "    \"minimumBalanceSavings\": 250.56\n" +
                                "}")
                )
                .andDo(print())
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();

        assertAll(
                () -> assertEquals(400, mvcResult.getResponse().getStatus()),
                () -> assertEquals("application/json", mvcResult.getResponse().getContentType())
        );
    }


    @Test
    @DisplayName("Modificando el balance de una cuenta.")
    void changeBalanceBasicInAccount() throws Exception {
        log.info("INTEGRATION_TEST:ADMIN_CONTROLLER_TEST:changeBalanceBasicInAccount: POST: Modificación del balance de una cuenta.");

        MvcResult mvcResult = mockMvc.perform(patch("/api/v1/admins/accountholders/9/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"iban\": \"CH021\",\n" +
                                "    \"balance\": {\n" +
                                "        \"currency\": \"USD\",\n" +
                                "        \"amount\": -999.99\n" +
                                "    }\n" +
                                "}")
                )
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();

        final Map<String, String> resultSet = parseResponse(mvcResult.getResponse().getContentAsString());

        assertAll(
                () -> assertEquals(200, mvcResult.getResponse().getStatus()),
                () -> assertEquals("application/json", mvcResult.getResponse().getContentType()),
                () -> assertEquals("-999.99", resultSet.get("amount"))
        );

    }

    @Test
    @DisplayName("ERROR Modificar balance de cuenta ")
    void changeBalanceBasicInAccount_Wrong() throws Exception {
        log.info("INTEGRATION_TEST:ADMIN_CONTROLLER_TEST:changeBalanceBasicInAccount_Wrong: POST: Modificación del balance de una cuenta.");

        MvcResult mvcResult = mockMvc.perform(patch("/api/v1/admins/accountholders/7/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"iban\": \"CH022\",\n" +
                                "    \"balance\": {\n" +
                                "        \"currency\": \"USD\",\n" +
                                "        \"amount\": -999.99\n" +
                                "    }\n" +
                                "}")
                )
                .andDo(print())
                .andExpectAll(
                        status().isIAmATeapot(),
                        content().contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();

        final Map<String, String> resultSet = parseResponse(mvcResult.getResponse().getContentAsString());

        assertAll(
                () -> assertEquals(418, mvcResult.getResponse().getStatus()),
                () -> assertEquals("application/json", mvcResult.getResponse().getContentType())
        );
    }

}