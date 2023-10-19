package com.example.tp_mobdev;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.example.tp_mobdev.databinding.PokedexFragmentBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PokedexFragment extends Fragment {
    List<Pokemon> pokemonList = new ArrayList<>();
    public boolean ajoutDansBaseDeDonnees = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        // Permet de lier les éléments de l'interface définis dans "pokedex_fragment.xml"
        // avec les objets java correspondants
        PokedexFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.pokedex_fragment,container,false);
        binding.pokemonList.setLayoutManager(new LinearLayoutManager(
                binding.getRoot().getContext()));


        creation(requireContext(), binding, pokemonList);
        PokemonListAdapter adapter = new PokemonListAdapter(pokemonList);
        adapter.setOnClickOnNoteListener(listener); // On donne le listener à l'adapter
        binding.pokemonList.setAdapter(adapter);

        return binding.getRoot(); // renvoie la view java
    }

    public void creation(Context context, PokedexFragmentBinding binding, List<Pokemon> pokemonList) {
        //Ouverture du fichier res/raw
        InputStreamReader isr = new InputStreamReader(getResources().openRawResource(R.raw.poke));
        BufferedReader reader = new BufferedReader(isr);
        StringBuilder builder = new StringBuilder();
        String data = "";
        //lecture du fichier. data == null => EOF
        while(data != null) {
            try {
                data = reader.readLine();
                builder.append(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Traitement du fichier
        try {
            JSONArray array = new JSONArray(builder.toString());
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String name = object.getString("name");
                String image = object.getString("image");
                int height = object.getInt("height");
                int weight = object.getInt("weight");
                String type1 = object.getString("type1");
                String type2 = null;
                if (object.has("type2"))
                    type2 = object.getString("type2");

                int id = getResources().getIdentifier(image,"drawable",
                        binding.getRoot().getContext().getPackageName());

                POKEMON_TYPE pokemon_type1 = POKEMON_TYPE.valueOf(type1);
                POKEMON_TYPE pokemon_type2 = null;

                if (type2 != null) {
                    pokemon_type2 = POKEMON_TYPE.valueOf(type2);
                }

                // Créer un objet Pokemon à partir des données JSON et l'ajouter à la liste
                Pokemon pokemon = new Pokemon(id, name, id, height, weight, pokemon_type1, pokemon_type2);

                pokemonList.add(pokemon);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (Boolean.FALSE.equals(ajoutDansBaseDeDonnees)) {
            System.out.print("Début lancement thread");
            MyThreadEventListener listener = new MyThreadEventListener() {
                @Override
                public void onEventInMyThread(String data) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            // Le code que vous souhaitez exécuter après la fin de MyThread
                        }
                    });
                }
            };
            MyThread thread = new MyThread(listener);
            thread.start();
            System.out.println("Fin lancement thread");
            ajoutDansBaseDeDonnees = true;
        }

    }

    public interface OnClickOnNoteListener {
        public void onClickOnNote(Pokemon pokemon);
    }
    private OnClickOnNoteListener listener;
    public void setOnClickOnNoteListener(OnClickOnNoteListener listener) {
        this.listener = listener;
    }

}