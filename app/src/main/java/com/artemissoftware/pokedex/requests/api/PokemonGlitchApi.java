package com.artemissoftware.pokedex.requests.api;

import com.artemissoftware.pokedex.requests.models.PokedexResults;
import com.artemissoftware.pokedex.requests.models.PokemonResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokemonGlitchApi {

    @GET("pokemon/{id}")
    Single<PokemonResponse> searchPokemon(@Path("id") String id);
}
