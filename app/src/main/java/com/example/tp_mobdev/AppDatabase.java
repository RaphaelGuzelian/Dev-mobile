package com.example.tp_mobdev;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PokemonEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PokemonDAO pokemonDAO();
}
