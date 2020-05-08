package com.artemissoftware.pokedex.ui.pokemon.models;

import java.util.Date;

public class Note {


    private int id;
    private int idPokemon;
    private String description;
    private Date registerDate;

    public Note(int idPokemon, String description, Date registerDate) {
        this.idPokemon = idPokemon;
        this.description = description;
        this.registerDate = registerDate;
    }

    public String getDescription() {
        return description;
    }

    public Date getRegisterDate() {
        return registerDate;
    }
}
