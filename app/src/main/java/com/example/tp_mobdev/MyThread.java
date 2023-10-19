package com.example.tp_mobdev;


import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class MyThread extends Thread {

    private MyThreadEventListener listener;

    public MyThread(MyThreadEventListener listener) {
        this.listener = listener;
    }

    public void run(Context context, List<Pokemon> pokemonList) {
        ajouterInfosDB(context, pokemonList);
        listener.onEventInMyThread("Finished");
    }

    public void ajouterInfosDB(Context context, List<Pokemon> pokemonList) {
        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "PokemonDatabase").build();
        PokemonDAO pokemonDAO = db.pokemonDAO();
        for (Pokemon pokemon : pokemonList) {
            PokemonEntity pokemonEntity = new PokemonEntity();
            pokemonEntity.name = pokemon.getName();
            pokemonEntity.height = pokemon.getHeight();
            pokemonEntity.weight = pokemon.getWeight();
            pokemonEntity.frontResource = pokemon.getFrontResource();
            pokemonEntity.type1 = pokemon.getType1();
            pokemonEntity.type2 = pokemon.getType2();
            pokemonDAO.insert(pokemonEntity);
        }
        System.out.println("ajouterInfosDB fini");
    }



}


