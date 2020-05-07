package com.artemissoftware.pokedex.requests.api;

import com.artemissoftware.pokedex.requests.models.PokemonResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonGlitchApi {

    @GET("pokemon/{id}")
    Single<List<PokemonResponse>> searchPokemon(@Path("id") String id);
}
