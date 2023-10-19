package com.example.tp_mobdev;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PokemonDAO {
    @Query("SELECT * FROM pokemonentity")
    List<PokemonEntity> getAll();

    @Insert
    void insert(PokemonEntity... pokemonEntities);

    @Delete
    void delete(PokemonEntity pokemonEntity);
}