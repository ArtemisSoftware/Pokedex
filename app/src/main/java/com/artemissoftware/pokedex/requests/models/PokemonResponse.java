package com.artemissoftware.pokedex.requests.models;

import com.google.gson.annotations.SerializedName;

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
    public List<Family> family;


    public class Family{

        @SerializedName("evolutionLine")
        public List<String> evolutionLine;
    }
}
