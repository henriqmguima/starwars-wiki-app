package com.example.swiki;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {

    // Insere um item (personagem ou filme) na lista de favoritos
    @Insert
    void insert(FavoriteEntity favorite);

    // Remove um item específico da lista de favoritos
    @Delete
    void delete(FavoriteEntity favorite);

    // Retorna todos os favoritos (pessoas e filmes)
    @Query("SELECT * FROM favorites")
    List<FavoriteEntity> getAllFavorites();

    // Retorna favoritos filtrados por tipo: "movie" ou "person"
    @Query("SELECT * FROM favorites WHERE tipo = :tipo")
    List<FavoriteEntity> getFavoritesByType(String tipo);

    // Apaga todos os dados da tabela de favoritos
    @Query("DELETE FROM favorites")
    void deleteAll();
}
