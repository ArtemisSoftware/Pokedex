package com.artemissoftware.pokedex.di.pokemon;

import com.artemissoftware.pokedex.database.NoteDao;
import com.artemissoftware.pokedex.database.PokemonDao;
import com.artemissoftware.pokedex.database.PokemonDataBase;
import com.artemissoftware.pokedex.repository.NoteRepository;
import com.artemissoftware.pokedex.repository.PokemonRepository;
import com.artemissoftware.pokedex.requests.api.PokemonGlitchApi;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import timber.log.Timber;

@Module
public class PokemonModule {


    @PokemonScope
    @Provides
    static NoteDao provideNoteDao(PokemonDataBase pokemonDataBase){

        NoteDao dao = pokemonDataBase.getNoteDao();

        Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @PokemonScope
    @Provides
    static PokemonDao providePokemonDao(PokemonDataBase pokemonDataBase){

        PokemonDao dao = pokemonDataBase.getPokemonDao();

        Timber.d("Providing PokemonDao: " + dao);
        return dao;
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
    NoteRepository provideNoteRepository(NoteDao noteDao) {

        NoteRepository repository = new NoteRepository(noteDao);

        Timber.d("Providing NoteRepository: " + repository);
        return repository;
    }


    @PokemonScope
    @Provides
    PokemonRepository providePokemonRepository(PokemonGlitchApi apiInterface, PokemonDao pokemonDao) {

        PokemonRepository repository = new PokemonRepository(apiInterface, pokemonDao);

        Timber.d("Providing PokemonRepository: " + repository);
        return repository;
    }

}
