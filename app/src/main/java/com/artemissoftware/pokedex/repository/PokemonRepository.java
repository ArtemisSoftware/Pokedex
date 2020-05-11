package com.artemissoftware.pokedex.repository;

import androidx.annotation.NonNull;

import com.artemissoftware.pokedex.requests.api.PokemonGlitchApi;
import com.artemissoftware.pokedex.requests.models.PokemonResponse;
import com.artemissoftware.pokedex.ui.pokemon.models.Note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Single;

public class PokemonRepository {

    private PokemonGlitchApi api;


    public PokemonRepository(@NonNull PokemonGlitchApi api) {
        this.api = api;
    }


    public Single<List<PokemonResponse>> searchPokemon(String id) {
        return api.searchPokemon(id);
    }


    /*
    public Flowable<List<Category>> getCategories() {
        return categoryDao.getCategories();
    }
    */



    public List<Note> getNotes() {

        List<Note> notes = new ArrayList<>();

        notes.add(new Note(1, "Note one", new Date()));
        notes.add(new Note(1, "Note two", new Date()));
        notes.add(new Note(1, "Note three", new Date()));

        notes.add(new Note(1, "Note one one", new Date()));
        notes.add(new Note(1, "Note two one", new Date()));
        notes.add(new Note(1, "Note thre eone", new Date()));

        notes.add(new Note(1, "Note one two", new Date()));
        notes.add(new Note(1, "Note two two", new Date()));
        notes.add(new Note(1, "Note three two", new Date()));

        notes.add(new Note(1, "Note one three", new Date()));
        notes.add(new Note(1, "Note two three", new Date()));
        notes.add(new Note(1, "Note three three", new Date()));

        return notes;
        //return categoryDao.getCategories();
    }
}
