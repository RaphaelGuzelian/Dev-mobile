package com.example.tp_mobdev;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PokemonEntity {
    @PrimaryKey
    public int uid;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "height")
    public int height;
    @ColumnInfo(name = "weight")
    public int weight;
    @ColumnInfo(name = "frontResource")
    public int frontResource;
    @ColumnInfo(name = "type1")
    public POKEMON_TYPE type1;
    @ColumnInfo(name = "type2")
    public POKEMON_TYPE type2;
}
