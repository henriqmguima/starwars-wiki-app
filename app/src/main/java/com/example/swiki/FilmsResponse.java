package com.example.swiki;

import java.util.List;

public class FilmsResponse {
    private String message;
    private List<FilmResult> result;

    public List<FilmResult> getResult() {
        return result;
    }
}
