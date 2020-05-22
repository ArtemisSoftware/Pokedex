package com.artemissoftware.pokedex.database;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RawQuery;

import com.artemissoftware.pokedex.ui.favourites.model.Favourite;
import com.artemissoftware.pokedex.ui.pokemon.models.Pokemon;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
abstract public class PokemonDao implements BaseDao<Pokemon>{

    @Query("SELECT COUNT(id) FROM pokemons WHERE id = :id")
    abstract public Maybe<Integer> exists(String id);


    @Query("SELECT * FROM pokemons ORDER BY id ASC")
    abstract public Flowable<List<Pokemon>> getAll();


    @Query("DELETE FROM pokemons WHERE id = :id")
    abstract public Completable delete(String id);


    @Query("SELECT id, name, description, numberNotes FROM pokemons as pkm LEFT JOIN (SELECT idPokemon, COUNT(id) as numberNotes FROM notes GROUP BY idPokemon) as nts ON pkm.id = nts.idPokemon ORDER BY id ASC")
    abstract public Flowable<List<Favourite>> getFavourites();


}