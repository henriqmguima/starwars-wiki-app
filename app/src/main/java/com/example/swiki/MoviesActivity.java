package com.example.swiki;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends AppCompatActivity {
    private RecyclerView recyclerMovies;
    private MovieAdapter movieAdapter;
    private List<FilmResult> movieList = new ArrayList<>();
    private LinearLayout btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movies);

        recyclerMovies = findViewById(R.id.rvMovies);
        btnVoltar = findViewById(R.id.btnVoltar);

        recyclerMovies.setLayoutManager(new LinearLayoutManager(this));
        movieAdapter = new MovieAdapter(movieList);
        recyclerMovies.setAdapter(movieAdapter);

        carregarFilmes();

        btnVoltar.setOnClickListener(v -> finish());
    }

    private void carregarFilmes() {
        SwapiService service = RetrofitClient.getInstance().create(SwapiService.class);
        service.getFilms().enqueue(new Callback<FilmsResponse>() {
            @Override
            public void onResponse(Call<FilmsResponse> call, Response<FilmsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    movieList.clear();  // limpar antes de adicionar
                    movieList.addAll(response.body().getResult());
                    movieAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<FilmsResponse> call, Throwable t) {
                Toast.makeText(MoviesActivity.this, "Erro ao carregar filmes", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
