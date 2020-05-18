package com.learning.wiremock;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Utils {


    public static StringBuilder readBufferData(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content;
    }

    public static <T> T parse(StringBuilder content, Class<T> type) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content.toString(), type);
    }

    public static String readFile(String path) throws IOException {
        File file = new File(path);
        return FileUtils.readFileToString(file, UTF_8);
    }

}
