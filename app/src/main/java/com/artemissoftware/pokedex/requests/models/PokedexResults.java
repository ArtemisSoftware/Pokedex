package com.artemissoftware.pokedex.requests.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokedexResults {

    @SerializedName("count")
    public String count;

    @SerializedName("results")
    public List<PokemonInfo> pokemons;


    public class PokemonInfo{

        @SerializedName("id")
        public String id;

        @SerializedName("name")
        public String name;
    }

}
