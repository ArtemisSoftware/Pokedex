package com.artemissoftware.pokedex.requests.models;

import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.util.List;

public class PokedexResults {

    @SerializedName("count")
    public String count;

    @SerializedName("results")
    public List<PokemonInfo> pokemons;


    public class PokemonInfo{

        @SerializedName("url")
        public String url;

        @SerializedName("name")
        public String name;


        public String getId(){

            String values [] = url.split("/");
            return values[values.length - 1];
        }


        public String referenceId(){

            DecimalFormat df = new DecimalFormat("000");
            String values [] = url.split("/");
            return "#" + df.format(Integer.parseInt(values[values.length - 1]));
        }



    }

}
