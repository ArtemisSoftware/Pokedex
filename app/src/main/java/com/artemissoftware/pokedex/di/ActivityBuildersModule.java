package com.artemissoftware.pokedex.di;


import com.artemissoftware.pokedex.MainActivity;
import com.artemissoftware.pokedex.ui.encyclopedia.PokedexActivity;

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

}
