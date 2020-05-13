package com.artemissoftware.pokedex.di;

import android.app.Application;

import androidx.room.Room;

import com.artemissoftware.pokedex.database.MigrationDb;
import com.artemissoftware.pokedex.database.PokemonDataBase;
import com.artemissoftware.pokedex.util.DataBaseConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    static PokemonDataBase providePokemonDataBase(Application application){
        return Room.databaseBuilder(application, PokemonDataBase.class, DataBaseConstants.NAME)
                .addMigrations(MigrationDb.getMigrations())
                .build();
    }

}
