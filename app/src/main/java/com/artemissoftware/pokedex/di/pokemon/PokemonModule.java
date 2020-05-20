package com.artemissoftware.pokedex.di.pokemon;

import com.artemissoftware.pokedex.database.NoteDao;
import com.artemissoftware.pokedex.database.PokemonDao;
import com.artemissoftware.pokedex.database.PokemonDataBase;
import com.artemissoftware.pokedex.repository.NoteRepository;
import com.artemissoftware.pokedex.repository.PokemonRepository;
import com.artemissoftware.pokedex.requests.api.JsonPlaceHolderApi;
import com.artemissoftware.pokedex.requests.api.PokemonGlitchApi;
import com.artemissoftware.pokedex.util.ApiConstants;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
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
    @Named("jsonplaceholderRetrofit")
    Retrofit provideJsonplaceholderRetrofit(OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.JSON_PLACE_HOLDER_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Timber.d("Providing JsonplaceholderRetrofit: " + retrofit);
        return retrofit;
    }


    @PokemonScope
    @Provides
    static JsonPlaceHolderApi provideJsonPlaceHolderApi(@Named("jsonplaceholderRetrofit") Retrofit retrofit){

        JsonPlaceHolderApi api = retrofit.create(JsonPlaceHolderApi.class);

        Timber.d("Providing JsonPlaceHolderApi: " + api);
        return api;
    }



    @PokemonScope
    @Provides
    static PokemonGlitchApi provideGlitchApi(@Named("detailRetrofit") Retrofit retrofit){

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
    PokemonRepository providePokemonRepository(PokemonGlitchApi pokemonGlitchApi, JsonPlaceHolderApi jsonPlaceHolderApi, PokemonDao pokemonDao) {

        PokemonRepository repository = new PokemonRepository(pokemonGlitchApi, jsonPlaceHolderApi, pokemonDao);

        Timber.d("Providing PokemonRepository: " + repository);
        return repository;
    }

}
