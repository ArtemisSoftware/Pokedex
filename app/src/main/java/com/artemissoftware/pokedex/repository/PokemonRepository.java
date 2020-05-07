package com.artemissoftware.pokedex.repository;

import androidx.annotation.NonNull;

import com.artemissoftware.pokedex.requests.api.PokemonGlitchApi;
import com.artemissoftware.pokedex.requests.models.PokemonResponse;

import io.reactivex.Single;

public class PokemonRepository {

    private PokemonGlitchApi api;


    public PokemonRepository(@NonNull PokemonGlitchApi api) {
        this.api = api;
    }


    public Single<PokemonResponse> searchPokemon(String id) {
        return api.searchPokemon(id);
    }
}
