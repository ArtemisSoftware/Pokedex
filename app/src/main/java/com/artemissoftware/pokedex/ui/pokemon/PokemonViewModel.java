package com.artemissoftware.pokedex.ui.pokemon;

import androidx.lifecycle.ViewModel;

import com.artemissoftware.pokedex.requests.api.PokemonGlitchApi;

import javax.inject.Inject;

import timber.log.Timber;

public class PokemonViewModel extends ViewModel {


    private final PokemonGlitchApi api;


    @Inject
    public PokemonViewModel(PokemonGlitchApi api){

        this.api = api;

        Timber.d("auth api: " + this.api);
        Timber.d("PokemonViewModel is working");

    }

}
