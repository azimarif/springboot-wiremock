package com.learning.wiremock;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TodoControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private WireMockApi wireMockApi = new WireMockApi();

    @BeforeEach
    void setUp() throws IOException {
        createExternalApiMocks();
    }

    @Test
    void shouldAssertResponseIsSameAsPerTodoModel() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/").contentType(APPLICATION_JSON);
        mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(100)))
                .andExpect(jsonPath("$.userId", is(100)))
                .andExpect(jsonPath("$.title", is("random title")))
                .andExpect(jsonPath("$.completed", is(true)))
                .andReturn();
    }

    @AfterEach
    void tearDown() {
        wireMockApi.stop();
    }


    private void createExternalApiMocks() throws IOException {
        wireMockApi.start();
        wireMockApi.stubTodoResponse();
    }

}