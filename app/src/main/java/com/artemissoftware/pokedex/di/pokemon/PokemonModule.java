package com.artemissoftware.pokedex.di.pokemon;

import com.artemissoftware.pokedex.requests.api.PokemonGlitchApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class PokemonModule {


    @PokemonScope
    @Provides
    static PokemonGlitchApi provideMainApi(Retrofit retrofit){

        PokemonGlitchApi api = retrofit.create(PokemonGlitchApi.class);

        //Timber.d("Providing PokemonGlitchApi: " + api);
        return api;

    }

}
