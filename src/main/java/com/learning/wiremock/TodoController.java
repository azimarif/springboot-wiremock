package com.learning.wiremock;

import java.io.IOException;
import java.io.InputStream;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoController {

    @Autowired
    ApiServiceCaller apiServiceCaller;

    @GetMapping
    public Todo getTodo() throws IOException {
        InputStream inputStream = apiServiceCaller.getData();
        StringBuilder content = Utils.readBufferData(inputStream);

        return Utils.parse(content, Todo.class);
    }
}
