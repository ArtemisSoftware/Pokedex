package com.artemissoftware.pokedex.requests.models;

import com.artemissoftware.pokedex.util.ApiConstants;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.util.List;

public class PokemonResponse {

    @SerializedName("number")
    public String number;

    @SerializedName("name")
    public String name;

    @SerializedName("species")
    public String species;


    @SerializedName("height")
    public String height;

    @SerializedName("weight")
    public String weight;

    @SerializedName("gen")
    public String generation;

    @SerializedName("description")
    public String description;


    @SerializedName("types")
    public List<String> types;


    @SerializedName("family")
    public Family family;

    @SerializedName("sprite")
    public String sprite;


    public class Family {

        @SerializedName("evolutionLine")
        public List<String> evolutionLine;
    }


    public String referenceId(){

        DecimalFormat df = new DecimalFormat("000");
        return "#" + df.format(Integer.parseInt(number));
    }

    public String getGameSpriteBack(){
        return ApiConstants.GAME_SPRITE_BACK_URL.replace("number", number);
    }

    public String getGameSpriteFront(){
        return ApiConstants.GAME_SPRITE_FRONT_URL.replace("number", number);
    }


    public boolean favourite;
}
