package com.example.swiki;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPeopleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PeopleAdapter peopleAdapter;
    private LinearLayout btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_people);

        recyclerView = findViewById(R.id.recyclerViewPeople);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> finish());

        fetchPeople();
    }

    private void fetchPeople() {
        SwapiService service = RetrofitClient.getInstance().create(SwapiService.class);
        Call<PeopleResponse> call = service.getPeople();

        call.enqueue(new Callback<PeopleResponse>() {
            @Override
            public void onResponse(Call<PeopleResponse> call, Response<PeopleResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<People> peopleList = response.body().getResults();
                    peopleAdapter = new PeopleAdapter(ListPeopleActivity.this, peopleList);
                    recyclerView.setAdapter(peopleAdapter);
                } else {
                    Toast.makeText(ListPeopleActivity.this, "Erro ao carregar personagens", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PeopleResponse> call, Throwable t) {
                Toast.makeText(ListPeopleActivity.this, "Falha na conexão: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
