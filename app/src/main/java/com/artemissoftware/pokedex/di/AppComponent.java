package com.artemissoftware.pokedex.di;

import android.app.Application;

import com.artemissoftware.pokedex.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;


@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                //ActivityBuildersModule.class,
                //AppModule.class,
                ViewModelFactoryModule.class,

                NetworkModule.class,

                //PlayerModule.class
        }
)
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}