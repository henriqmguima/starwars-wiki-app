package com.example.swiki;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout cardPeople, cardMovies;
    private Button buttonFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardPeople = findViewById(R.id.cardPeople);
        cardMovies = findViewById(R.id.cardMovies);
        buttonFavorites = findViewById(R.id.buttonFavorites);

        cardPeople.setOnClickListener(view -> {
            Intent intent = new Intent(this, ListPeopleActivity.class);
            startActivity(intent);
        });

        cardMovies.setOnClickListener(view -> {
            Intent intent = new Intent(this, MoviesActivity.class);
            startActivity(intent);
        });

        buttonFavorites.setOnClickListener(view -> {
            Intent intent = new Intent(this, FavoritesActivity.class);
            startActivity(intent);
        });
    }
}
