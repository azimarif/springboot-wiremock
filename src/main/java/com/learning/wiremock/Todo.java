package com.learning.wiremock;


import lombok.Data;

@Data
public class Todo {
    private int id;
    private int userId;
    private String title;
    private boolean isCompleted;
}

