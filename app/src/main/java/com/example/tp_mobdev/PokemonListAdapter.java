package com.example.tp_mobdev;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_mobdev.Pokemon;
import com.example.tp_mobdev.R;
import com.example.tp_mobdev.databinding.PokemonItemBinding;

import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {
    List<Pokemon> pokemonList;
    public PokemonListAdapter(List<Pokemon> pokemonList) {
        assert pokemonList != null;
        this.pokemonList = pokemonList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.pokemon_item, parent, false);
        return new ViewHolder(binding);
    }

    // Remplie la vue associée au ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);
        holder.binding.front.setImageResource(pokemon.getFrontResource());
        holder.viewModel.setPokemon(pokemon);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public Pokemon getPokemonByName(String name) {
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getName().equals(name)) {
                return pokemon;
            }
        }
        return null;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private PokemonItemBinding binding;
        private PokemonViewModel viewModel = new PokemonViewModel();
        ViewHolder(PokemonItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.setPokemonViewModel(viewModel);
            this.binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        Pokemon selectedPokemon = viewModel.getPokemon();
                        listener.onClickOnNote(selectedPokemon); // Infos à passer en paramètres
                    }
                }
            });
        }
    }


    private PokedexFragment.OnClickOnNoteListener listener;

    public void setOnClickOnNoteListener(PokedexFragment.OnClickOnNoteListener listener) {
        this.listener = listener;
    }

}
