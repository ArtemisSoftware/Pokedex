package com.artemissoftware.pokedex.repository;

import androidx.annotation.NonNull;

import com.artemissoftware.pokedex.database.NoteDao;
import com.artemissoftware.pokedex.requests.api.PokemonGlitchApi;
import com.artemissoftware.pokedex.requests.models.PokemonResponse;
import com.artemissoftware.pokedex.ui.pokemon.models.Note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class PokemonRepository {

    private PokemonGlitchApi api;
    private final NoteDao noteDao;


    public PokemonRepository(@NonNull PokemonGlitchApi api, @NonNull NoteDao noteDao) {
        this.api = api;
        this.noteDao = noteDao;
    }


    public Single<List<PokemonResponse>> searchPokemon(String id) {
        return api.searchPokemon(id);
    }

    public Single<Long> insertNote(Note note) {
        return noteDao.insert(note);
    }

    public Flowable<List<Note>> getNotes(int idPokemon) {
        return noteDao.getNotes(idPokemon);
    }


}
