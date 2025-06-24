package com.example.swiki;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMActivity extends AppCompatActivity {

    private TextView tvTitulo, tvNomeCompleto, tvEpisode, tvData, tvDiretor, tvProdutor, tvAbertura;
    private Button btnSalvarFilme;
    private LinearLayout btnVoltar;
    private ImageView logo;

    private String filmTitle = "";
    private String releaseDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_m);

        tvTitulo = findViewById(R.id.tvTitulo);
        tvNomeCompleto = findViewById(R.id.tvNomeCompleto);
        tvEpisode = findViewById(R.id.tvEpisode);
        tvData = findViewById(R.id.tvData);
        tvDiretor = findViewById(R.id.tvDiretor);
        tvProdutor = findViewById(R.id.tvProdutor);
        tvAbertura = findViewById(R.id.tvAbertura);

        btnSalvarFilme = findViewById(R.id.btnSalvarFilme);
        btnVoltar = findViewById(R.id.btnVoltar);
        logo = findViewById(R.id.logoApp);

        String filmUid = getIntent().getStringExtra("FILM_UID");
        if (filmUid != null) {
            carregarFilme(filmUid);
        } else {
            Toast.makeText(this, "Filme inválido", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnVoltar.setOnClickListener(v -> finish());

        btnSalvarFilme.setOnClickListener(v -> {
            if (!filmTitle.isEmpty()) {
                FavoriteEntity filmeFavorito = new FavoriteEntity(
                        filmTitle,
                        releaseDate,
                        "movie"
                );

                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "swiki_db").allowMainThreadQueries().build();

                db.favoriteDao().insert(filmeFavorito);
                Toast.makeText(this, "Filme salvo com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Filme ainda não carregado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void carregarFilme(String uid) {
        SwapiService service = RetrofitClient.getInstance().create(SwapiService.class);
        service.getFilmById(uid).enqueue(new Callback<FilmDetailResponse>() {
            @Override
            public void onResponse(Call<FilmDetailResponse> call, Response<FilmDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    FilmProperties movie = response.body().getResult().getProperties();

                    filmTitle = movie.getTitle();
                    releaseDate = movie.getRelease_date();

                    tvTitulo.setText(movie.getTitle());
                    tvNomeCompleto.setText("Star Wars: " + movie.getTitle());
                    tvEpisode.setText("Episódio: " + movie.getEpisode_id());
                    tvData.setText("Lançamento: " + movie.getRelease_date());
                    tvDiretor.setText("Diretor: " + movie.getDirector());
                    tvProdutor.setText("Produtor: " + movie.getProducer());

                    String aberturaFormatada = movie.getOpening_crawl().replace("\r\n", " ");
                    tvAbertura.setText(aberturaFormatada);
                } else {
                    Toast.makeText(DetailMActivity.this, "Erro ao carregar detalhes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FilmDetailResponse> call, Throwable t) {
                Toast.makeText(DetailMActivity.this, "Falha na conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
