package com.artemissoftware.pokedex.di;

import com.artemissoftware.pokedex.repository.PokedexRepository;
import com.artemissoftware.pokedex.requests.api.PokemonApi;
import com.artemissoftware.pokedex.util.ApiConstants;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient client = new OkHttpClient.Builder()

                .addInterceptor(loggingInterceptor)

                //establish connection to server
                .connectTimeout(ApiConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)

                //time between each byte read from the server
                .readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.SECONDS)

                //time between each byte sent to server
                .writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)

                .retryOnConnectionFailure(false)
                //.addInterceptor(new UrlInterceptor())
                .build();


        Timber.d("Providing OkHttpClient: " + client);
        return client;
    }




    @Provides
    @Singleton
    @Named("baseRetrofit")
    Retrofit provideBaseRetrofit(OkHttpClient okHttpClient) {

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
    @Named("detailRetrofit")
    Retrofit provideDetailRetrofit(OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.DETAIL_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Timber.d("Providing Detailretrofit: " + retrofit);
        return retrofit;
    }



    @Provides
    @Singleton
    PokemonApi providePokemonApiInterface(@Named("baseRetrofit") Retrofit retrofit) {

        PokemonApi api = retrofit.create(PokemonApi.class);

        //Timber.d("Providing FlickrApi: " + api);
        return api;
    }


    @Provides
    @Singleton
    PokedexRepository provideRepository(PokemonApi apiInterface) {

        PokedexRepository repository = new PokedexRepository(apiInterface);

        //Timber.d("Providing repository: " + repository);
        return repository;
    }

}
