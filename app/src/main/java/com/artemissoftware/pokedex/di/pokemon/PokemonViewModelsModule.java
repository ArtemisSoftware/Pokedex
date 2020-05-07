package com.artemissoftware.pokedex.di.pokemon;


import androidx.lifecycle.ViewModel;

import com.artemissoftware.pokedex.di.ViewModelKey;
import com.artemissoftware.pokedex.ui.pokemon.PokemonViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class PokemonViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(PokemonViewModel.class)
    public abstract ViewModel bindPokemonViewModel(PokemonViewModel viewModel);

}