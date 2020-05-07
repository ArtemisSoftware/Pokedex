package com.artemissoftware.pokedex.ui.pokemon;

import androidx.lifecycle.ViewModel;

import com.artemissoftware.pokedex.repository.PokemonRepository;
import com.artemissoftware.pokedex.requests.api.PokemonGlitchApi;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class PokemonViewModel extends ViewModel {


    private final CompositeDisposable disposables;

    private final PokemonRepository repository;


    @Inject
    public PokemonViewModel(PokemonRepository repository){

        this.repository = repository;

        this.disposables = new CompositeDisposable();

        Timber.d("Pokemon repository: " + this.repository);
        Timber.d("PokemonViewModel is working");

    }

}
