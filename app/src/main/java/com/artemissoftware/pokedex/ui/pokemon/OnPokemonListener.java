package com.artemissoftware.pokedex.ui.pokemon;

import com.artemissoftware.pokedex.requests.models.PokemonResponse;

public interface OnPokemonListener {

    void setFavourite();

    void showNoteDialog(PokemonResponse response);

}
