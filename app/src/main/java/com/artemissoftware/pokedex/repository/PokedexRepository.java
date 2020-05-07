package com.artemissoftware.pokedex.repository;

import androidx.annotation.NonNull;

import com.artemissoftware.pokedex.requests.api.PokemonApi;
import com.artemissoftware.pokedex.requests.models.PokedexResults;
import com.artemissoftware.pokedex.util.ApiConstants;

import io.reactivex.Single;

public class PokedexRepository {


    private PokemonApi api;


    public PokedexRepository(@NonNull PokemonApi api) {
        this.api = api;
    }


    public Single<PokedexResults> searchPokedex() {
        return api.searchPokedex(ApiConstants.API_RESULT_OFFSET, ApiConstants.API_RESULT_LIMIT);
    }


}
