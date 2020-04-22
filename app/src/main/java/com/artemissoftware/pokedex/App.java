package com.artemissoftware.pokedex;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class App extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //Stetho.initializeWithDefaults(this);
        //Timber.plant(new Timber.DebugTree());
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {

        //Timber.d("AndroidInjector... ");

        return null;//DaggerAppComponent.builder().application(this).build();
    }
}