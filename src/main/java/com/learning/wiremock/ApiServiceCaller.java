package com.learning.wiremock;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class ApiServiceCaller {

    @Value("${HOST}")
    private String host;

    @Value("${PROTOCOL}")
    private String protocol;

    public InputStream getData() throws IOException {
        final String URL = getUrl();
        java.net.URL url = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        return con.getInputStream();
    }

    private String getUrl() {
        return protocol +  "://" + host + "/todos/1";
    }
}
