package com.example.swiki;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<FavoriteEntity> favoritos;

    public FavoriteAdapter(List<FavoriteEntity> favoritos) {
        this.favoritos = favoritos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavoriteEntity fav = favoritos.get(position);
        holder.tvTitulo.setText(fav.getTitle());
        holder.tvInfoExtra.setText(fav.getData());
    }

    @Override
    public int getItemCount() {
        return favoritos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvInfoExtra;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvFavTitle);
            tvInfoExtra = itemView.findViewById(R.id.tvFavData);
        }
    }
}
