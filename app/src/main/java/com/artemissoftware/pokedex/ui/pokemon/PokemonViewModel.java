package com.artemissoftware.pokedex.ui.pokemon;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.artemissoftware.pokedex.repository.PokemonRepository;
import com.artemissoftware.pokedex.requests.api.PokemonGlitchApi;
import com.artemissoftware.pokedex.requests.models.PokemonResponse;
import com.artemissoftware.pokedex.ui.Resource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class PokemonViewModel extends ViewModel {


    private final CompositeDisposable disposables;

    private final PokemonRepository repository;

    private MutableLiveData<Resource> resourceLiveData;
    public MutableLiveData<PokemonResponse> pokemon;


    @Inject
    public PokemonViewModel(PokemonRepository repository){

        this.repository = repository;

        this.disposables = new CompositeDisposable();

        resourceLiveData = new MutableLiveData<>();
        pokemon = new MutableLiveData<>();



        Timber.d("Pokemon repository: " + this.repository);
        Timber.d("PokemonViewModel is working");

    }

    public MutableLiveData<Resource> observePokemon(){
        return resourceLiveData;
    }



    public void searchPokemon(String id) {

        Timber.d("Searching pokemon with id: " + id);
        //isPerformingQuery = true;


        repository.searchPokemon(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<PokemonResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(List<PokemonResponse> pokemonResponse) {

                        pokemon.setValue(pokemonResponse.get(0));
                        resourceLiveData.setValue(Resource.success(pokemonResponse.get(0)));
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        resourceLiveData.setValue(Resource.error(throwable.getMessage(), "Execution error"));
                    }
                });

    }


}
