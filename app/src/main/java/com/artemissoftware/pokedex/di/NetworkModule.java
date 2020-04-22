package com.artemissoftware.pokedex.di;

import com.artemissoftware.pokedex.repository.PokemonRepository;
import com.artemissoftware.pokedex.requests.PokemonApi;
import com.artemissoftware.pokedex.util.ApiConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Timber.d("Providing retrofit: " + retrofit);
        return retrofit;
    }


    @Provides
    @Singleton
    PokemonApi providePokemonApiInterface(Retrofit retrofit) {

        PokemonApi api = retrofit.create(PokemonApi.class);

        //Timber.d("Providing FlickrApi: " + api);
        return api;
    }


    @Provides
    @Singleton
    PokemonRepository provideRepository(PokemonApi apiInterface) {

        PokemonRepository repository = new PokemonRepository(apiInterface);

        //Timber.d("Providing repository: " + repository);
        return repository;
    }

}
