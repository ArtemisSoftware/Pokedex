package com.artemissoftware.pokedex.ui.pokemon;

import com.artemissoftware.pokedex.requests.models.PokemonResponse;

public interface OnPokemonListener {

    void setFavourite(String number);

    void showNoteDialog(PokemonResponse response);

}
