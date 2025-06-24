package com.example.swiki;

public class People {
    private String uid;
    private String name;
    private String url;

    public People(String uid, String name, String url) {
        this.uid = uid;
        this.name = name;
        this.url = url;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}

