package com.artemissoftware.pokedex.ui.favourites.model;

public class Favourite {

    private String id;
    private String name;
    private String description;
    private int numberNotes;

    public Favourite(String id, String name, String description, int numberNotes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numberNotes = numberNotes;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberNotes() {
        return numberNotes;
    }
}
