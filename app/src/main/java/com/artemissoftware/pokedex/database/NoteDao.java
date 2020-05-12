package com.artemissoftware.pokedex.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.artemissoftware.pokedex.ui.pokemon.models.Note;

import java.util.List;

import io.reactivex.Flowable;

@Dao
abstract public class NoteDao implements BaseDao<Note>{


    @Query("SELECT * FROM notes WHERE idPokemon = :idPokemon ORDER BY registerDate DESC")
    abstract public Flowable<List<Note>> getNotes(int idPokemon);
}
