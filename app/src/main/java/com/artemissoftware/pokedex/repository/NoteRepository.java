package com.artemissoftware.pokedex.repository;

import androidx.annotation.NonNull;

import com.artemissoftware.pokedex.database.NoteDao;
import com.artemissoftware.pokedex.requests.api.PokemonGlitchApi;
import com.artemissoftware.pokedex.requests.models.PokemonResponse;
import com.artemissoftware.pokedex.ui.pokemon.models.Note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class NoteRepository implements Repository<Note>{

    private final NoteDao noteDao;


    public NoteRepository(@NonNull NoteDao noteDao) {
        this.noteDao = noteDao;
    }


    @Override
    public Single<Long> insert(Note item) {
        return noteDao.insert(item);
    }

    @Override
    public Single<Integer> update(Note item) {
        return noteDao.update(item);
    }

    @Override
    public Single<Integer> delete(Note item) {
        return noteDao.delete(item);
    }


    public Flowable<List<Note>> getNotes(int idPokemon) {
        return noteDao.getNotes(idPokemon);
    }

    public Completable delete(String idPokemon) {
        return noteDao.delete(idPokemon);
    }

}
