package com.learning.wiremock;

import java.io.IOException;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


public class WireMockApi {

    private WireMockServer wireMockServer;

    public void start() {
        wireMockServer = new WireMockServer(wireMockConfig().port(80));
        wireMockServer.start();
        WireMock.configureFor("localhost", 80);
    }

    public void stop() {
        wireMockServer.stop();
    }

    public void stubTodoResponse() throws IOException {
        String response = Utils.readFile("src/test/resources/todo.json");

        wireMockServer.stubFor(get(urlPathEqualTo("/todos/1"))
                .willReturn(aResponse()
                        .withStatus(OK.value())
                        .withBody(response)
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)));
    }
}