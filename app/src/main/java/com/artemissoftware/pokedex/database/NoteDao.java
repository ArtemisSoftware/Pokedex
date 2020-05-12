package com.artemissoftware.pokedex.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.artemissoftware.pokedex.ui.pokemon.models.Note;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface NoteDao {


    @Query("SELECT * FROM notes WHERE idPokemon = :idPokemon")
    Flowable<List<Note>> getItemList(int idPokemon);
}
