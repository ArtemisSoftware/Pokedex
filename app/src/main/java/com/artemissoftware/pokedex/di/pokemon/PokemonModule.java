package com.artemissoftware.pokedex.di.pokemon;

import com.artemissoftware.pokedex.database.NoteDao;
import com.artemissoftware.pokedex.database.PokemonDataBase;
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
    static NoteDao provideNoteDao(PokemonDataBase pokemonDataBase){

        NoteDao database = pokemonDataBase.getNoteDao();

        Timber.d("Providing NoteDao: " + database);
        return database;
    }


    @PokemonScope
    @Provides
    static PokemonGlitchApi provideMainApi(@Named("detailRetrofit") Retrofit retrofit){

        PokemonGlitchApi api = retrofit.create(PokemonGlitchApi.class);

        Timber.d("Providing PokemonGlitchApi: " + api);
        return api;

    }

    @PokemonScope
    @Provides
    PokemonRepository providePokemonRepository(PokemonGlitchApi apiInterface, NoteDao noteDao) {

        PokemonRepository repository = new PokemonRepository(apiInterface, noteDao);

        Timber.d("Providing PokemonRepository: " + repository);
        return repository;
    }

}
