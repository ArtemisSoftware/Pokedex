package com.artemissoftware.pokedex.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.artemissoftware.pokedex.ui.pokemon.models.Note;
import com.artemissoftware.pokedex.util.Converters;
import com.artemissoftware.pokedex.util.DataBaseConstants;

@Database(entities = {Note.class}, version = DataBaseConstants.VERSION)
@TypeConverters({Converters.class})
public abstract class PokemonDataBase extends RoomDatabase {

    public abstract NoteDao getNoteDao();

    public abstract PokemonDao getPokemonDao();

}
