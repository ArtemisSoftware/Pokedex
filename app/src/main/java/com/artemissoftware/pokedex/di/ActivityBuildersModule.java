package com.artemissoftware.pokedex.di;


import com.artemissoftware.pokedex.MainActivity;
import com.artemissoftware.pokedex.di.pokemon.PokemonModule;
import com.artemissoftware.pokedex.di.pokemon.PokemonScope;
import com.artemissoftware.pokedex.di.pokemon.PokemonViewModelsModule;
import com.artemissoftware.pokedex.ui.pokepidia.PokedexActivity;
import com.artemissoftware.pokedex.ui.pokemon.PokemonActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {


    //@MainScope
    @ContributesAndroidInjector(
            modules = {/*QuizViewModelsModule.class, QuizModule.class*/}
    )
    abstract MainActivity contributeMainActivity();


    //@MainScope
    @ContributesAndroidInjector(
            modules = {/*QuizViewModelsModule.class, QuizModule.class*/}
    )
    abstract PokedexActivity contributePokedexActivity();


    @PokemonScope
    @ContributesAndroidInjector(
            modules = { PokemonViewModelsModule.class, PokemonModule.class }
    )
    abstract PokemonActivity contributePokemonActivity();

}
