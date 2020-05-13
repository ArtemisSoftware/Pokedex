package com.artemissoftware.pokedex.database;

import android.database.SQLException;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import timber.log.Timber;

public class MigrationDb {

    public static final Migration  [] getMigrations(){

        Migration migrations [] =  new Migration []{
                MIGRATION_1_2
        };

        return migrations;
    }


    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {
                database.execSQL("CREATE TABLE IF NOT EXISTS 'pokemons' ("
                        + "'id' TEXT PRIMARY KEY NOT NULL, "
                        + "'name' TEXT NOT NULL, "
                        + "'description' TEXT NOT NULL) ");

                Timber.d("MIGRATION_1_2: success");
            }
            catch(SQLException e){
                Timber.e("erro MIGRATION_1_2: " + e.getMessage());
            }
        }
    };


}
