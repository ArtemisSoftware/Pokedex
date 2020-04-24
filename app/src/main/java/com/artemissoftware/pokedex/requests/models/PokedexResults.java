package com.artemissoftware.pokedex.requests.models;

import com.artemissoftware.pokedex.R;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.util.List;

public class PokedexResults {

    @SerializedName("count")
    public String count;

    @SerializedName("results")
    public List<PokemonInfo> pokemons;

    public final int defaultImage = R.drawable.ic_launcher_foreground;


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

        public String getImageUrl(){
            return "https://pokeres.bastionbot.org/images/pokemon/"+ getId() + ".png";
        }

    }

}
