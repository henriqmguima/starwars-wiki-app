package com.example.swiki;

import java.util.List;

public class PeopleResponse {
    private String message;
    private List<People> results;

    public String getMessage() {
        return message;
    }

    public List<People> getResults() {
        return results;
    }
}
