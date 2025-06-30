package com.example.swiki;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailPActivity extends AppCompatActivity {

    private TextView tvNome, tvGender, tvSkinColor, tvHairColor, tvHeight, tvEyeColor, tvBirthYear;
    private Button btnSalvar;
    private CharacterResponse.Properties personagemSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_p);

        tvNome = findViewById(R.id.tvNome);
        tvGender = findViewById(R.id.tvGender);
        tvSkinColor = findViewById(R.id.tvSkinColor);
        tvHairColor = findViewById(R.id.tvHairColor);
        tvHeight = findViewById(R.id.tvHeight);
        tvEyeColor = findViewById(R.id.tvEyeColor);
        tvBirthYear = findViewById(R.id.tvBirthYear);
        btnSalvar = findViewById(R.id.btnSalvar);

        LinearLayout btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> finish());

        btnSalvar.setOnClickListener(v -> {
            if (personagemSelecionado != null) {
                salvarPersonagem(personagemSelecionado);
            }
        });

        String characterId = getIntent().getStringExtra("CHARACTER_ID");
        if (characterId != null) {
            buscarDetalhesPersonagem(characterId);
        }
    }

    private void buscarDetalhesPersonagem(String id) {

        SwapiService service = RetrofitClient.getInstance().create(SwapiService.class);

        Call<CharacterResponse> call = service.getCharacterById(id);
        call.enqueue(new Callback<CharacterResponse>() {
            @Override
            public void onResponse(Call<CharacterResponse> call, Response<CharacterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CharacterResponse.Properties p = response.body().getResult().getProperties();
                    personagemSelecionado = p;

                    tvNome.setText(p.getName());
                    tvGender.setText("Gender: " + p.getGender());
                    tvSkinColor.setText("Skin Color: " + p.getSkinColor());
                    tvHairColor.setText("Hair Color: " + p.getHairColor());
                    tvHeight.setText("Height: " + p.getHeight());
                    tvEyeColor.setText("Eye Color: " + p.getEyeColor());
                    tvBirthYear.setText("Birth Year: " + p.getBirthYear());
                }
            }

            @Override
            public void onFailure(Call<CharacterResponse> call, Throwable t) {
                Toast.makeText(DetailPActivity.this, "Erro ao carregar dados", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void salvarPersonagem(CharacterResponse.Properties p) {
        FavoriteEntity personagemFavorito = new FavoriteEntity(
                p.getName(),
                p.getGender(),
                "person"
        );

        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            db.favoriteDao().insert(personagemFavorito);

            runOnUiThread(() ->
                    Toast.makeText(this, "Personagem salvo com sucesso", Toast.LENGTH_SHORT).show()
            );
        }).start();
    }
}
