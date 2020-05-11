package com.artemissoftware.pokedex.ui.pokemon.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Note implements Parcelable {


    private int id;
    private int idPokemon;
    private String description;
    private Date registerDate;

    public Note(int idPokemon, String description, Date registerDate) {
        this.idPokemon = idPokemon;
        this.description = description;
        this.registerDate = registerDate;
    }




    public String getDescription() {
        return description;
    }

    public Date getRegisterDate() {
        return registerDate;
    }






    protected Note(Parcel in) {
        id = in.readInt();
        idPokemon = in.readInt();
        description = in.readString();
    }



    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(idPokemon);
        dest.writeString(description);
    }
}
