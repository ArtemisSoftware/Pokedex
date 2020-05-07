package com.artemissoftware.pokedex.di.pokemon;

import com.artemissoftware.pokedex.repository.PokemonRepository;
import com.artemissoftware.pokedex.requests.api.PokemonGlitchApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import timber.log.Timber;

@Module
public class PokemonModule {


    @PokemonScope
    @Provides
    static PokemonGlitchApi provideMainApi(@Named("detailRetrofit") Retrofit retrofit){

        PokemonGlitchApi api = retrofit.create(PokemonGlitchApi.class);

        Timber.d("Providing PokemonGlitchApi: " + api);
        return api;

    }

    @PokemonScope
    @Provides
    PokemonRepository providePokemonRepository(PokemonGlitchApi apiInterface) {

        PokemonRepository repository = new PokemonRepository(apiInterface);

        Timber.d("Providing PokemonRepository: " + repository);
        return repository;
    }

}
