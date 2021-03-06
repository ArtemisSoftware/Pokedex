package com.artemissoftware.pokedex.ui.pokemon.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormat;
import java.util.Date;

@Entity(tableName = "notes")
public class Note implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "idPokemon")
    private int idPokemon;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "registerDate")
    private Date registerDate;


    public Note(int idPokemon, String description, Date registerDate) {
        this.idPokemon = idPokemon;
        this.description = description;
        this.registerDate = registerDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPokemon() {
        return idPokemon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getDate() {
        return DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM).format(registerDate);
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
