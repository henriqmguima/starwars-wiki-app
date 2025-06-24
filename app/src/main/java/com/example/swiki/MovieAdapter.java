package com.example.swiki;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<FilmResult> movies;

    public MovieAdapter(List<FilmResult> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        FilmResult filmResult = movies.get(position);
        FilmProperties movie = filmResult.getProperties();

        holder.title.setText(movie.getTitle());
        holder.date.setText(movie.getRelease_date());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailMActivity.class);
            intent.putExtra("FILM_UID", filmResult.getUid());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title, date;

        public MovieViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvMovieTitle);
            date = itemView.findViewById(R.id.tvMovieDate);
        }
    }
}
