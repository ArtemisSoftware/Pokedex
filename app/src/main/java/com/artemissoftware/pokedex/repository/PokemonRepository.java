package com.artemissoftware.pokedex.repository;

import androidx.annotation.NonNull;

import com.artemissoftware.pokedex.database.PokemonDao;
import com.artemissoftware.pokedex.requests.api.JsonPlaceHolderApi;
import com.artemissoftware.pokedex.requests.api.PokemonGlitchApi;
import com.artemissoftware.pokedex.requests.models.PokemonResponse;
import com.artemissoftware.pokedex.ui.pokemon.models.Pokemon;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class PokemonRepository implements Repository<Pokemon>{

    private final PokemonDao pokemonDao;
    private final PokemonGlitchApi api;
    private final JsonPlaceHolderApi jsonPlaceHolderApi;


    public PokemonRepository(@NonNull PokemonGlitchApi api, @NonNull JsonPlaceHolderApi jsonPlaceHolderApi, @NonNull PokemonDao pokemonDao) {
        this.api = api;
        this.pokemonDao = pokemonDao;
        this.jsonPlaceHolderApi = jsonPlaceHolderApi;
    }


    public Single<List<PokemonResponse>> searchPokemon(String id) {
        return api.searchPokemon(id);
    }



    @Override
    public Single<Long> insert(Pokemon item) {
        return pokemonDao.insert(item);
    }


    @Override
    public Single<Integer> delete(Pokemon item) {
        return pokemonDao.delete(item);
    }


    public Maybe<Integer> exists(String id) {
        return pokemonDao.exists(id);
    }




    @Override
    public Single<Integer> update(Pokemon item) {
        return null;
    }
}
