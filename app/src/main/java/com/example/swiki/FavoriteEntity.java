package com.example.swiki;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites")
public class FavoriteEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String data;
    private String tipo; // "movie" ou "person"

    public FavoriteEntity(String title, String data, String tipo) {
        this.title = title;
        this.data = data;
        this.tipo = tipo;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getData() {
        return data;
    }

    public String getTipo() {
        return tipo;
    }
}