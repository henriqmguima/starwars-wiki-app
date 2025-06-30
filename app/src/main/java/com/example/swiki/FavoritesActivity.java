package com.example.swiki;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView rvPessoas, rvFilmes;
    private FavoriteAdapter adapterPessoas, adapterFilmes;
    private LinearLayout btnVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        rvPessoas = findViewById(R.id.rvPessoas);
        rvFilmes = findViewById(R.id.rvFilmes);
        btnVoltar = findViewById(R.id.btnVoltar);

        rvPessoas.setLayoutManager(new LinearLayoutManager(this));
        rvFilmes.setLayoutManager(new LinearLayoutManager(this));

        carregarFavoritos();

        btnVoltar.setOnClickListener(v -> finish());
    }

    private void carregarFavoritos() {
        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            List<FavoriteEntity> todos = db.favoriteDao().getAllFavorites();

            List<FavoriteEntity> filmes = new ArrayList<>();
            for (FavoriteEntity f : todos) {
                if (f.getTipo().equals("movie")) {
                    filmes.add(f);
                }
            }



            List<FavoriteEntity> pessoas = new ArrayList<>();
            for (FavoriteEntity p : todos) {
                if (p.getTipo().equals("person")) {
                    pessoas.add(p);
                }
            }



            runOnUiThread(() -> {
                adapterPessoas = new FavoriteAdapter(pessoas);
                adapterFilmes = new FavoriteAdapter(filmes);

                rvPessoas.setAdapter(adapterPessoas);
                rvFilmes.setAdapter(adapterFilmes);
            });
        }).start();
    }
}
