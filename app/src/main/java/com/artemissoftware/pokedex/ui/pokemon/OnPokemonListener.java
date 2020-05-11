package com.artemissoftware.pokedex.ui.pokemon;

import com.artemissoftware.pokedex.requests.models.PokemonResponse;
import com.artemissoftware.pokedex.ui.pokemon.models.Note;

public interface OnPokemonListener {

    void showNoteDialog(PokemonResponse response);

    void showNoteDialog(Note note);

}
