package com.example.tp_mobdev;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;


import com.example.tp_mobdev.databinding.PokemonDetailsBinding;

public class PokemonDetails extends PokedexFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        PokemonDetailsBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.pokemon_details,container,false);
        binding.setPokemonViewModel(viewModel); // On set son viewModel
        return binding.getRoot(); // renvoie la view java
    }

    private Pokemon pokemon = new Pokemon();
    private PokemonViewModel viewModel = new PokemonViewModel();

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
        viewModel.setPokemon(pokemon); // On set le pokemon du viewModel
        System.out.println("Name : " + pokemon.getName());
        System.out.println("Height : " + pokemon.getHeight());
        System.out.println("Weight : " + pokemon.getWeight());

    }

    public Pokemon getPokemon() {
        return pokemon;
    }


}
