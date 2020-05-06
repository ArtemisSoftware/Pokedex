package com.artemissoftware.pokedex.requests.api;

import com.artemissoftware.pokedex.requests.models.PokedexResults;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokemonApi {


    @GET("pokemon/?")
    Single<PokedexResults> searchPokedex(@Query("offset") String offset, @Query("limit") String limit);

}
