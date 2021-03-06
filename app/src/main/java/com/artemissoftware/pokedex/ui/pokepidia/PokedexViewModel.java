package com.artemissoftware.pokedex.ui.pokepidia;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.artemissoftware.pokedex.repository.PokedexRepository;
import com.artemissoftware.pokedex.requests.models.PokedexResults;
import com.artemissoftware.pokedex.util.Resource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PokedexViewModel extends ViewModel {

    private final CompositeDisposable disposables;

    private final PokedexRepository pokemonRepository;

    private MutableLiveData<Resource> pokedexLiveData;


    public MutableLiveData<String> numberPokemons;
    public MutableLiveData<List<PokedexResults.PokemonInfo>> pokemons;




    @Inject
    public PokedexViewModel(PokedexRepository pokemonRepository) {

        this.pokemonRepository = pokemonRepository;
        this.disposables = new CompositeDisposable();

        pokedexLiveData = new MutableLiveData<>();
        pokemons = new MutableLiveData<>();
        numberPokemons = new MutableLiveData<String>();

        //Timber.d("Pokemon repository: " + this.pokemonRepository);
        //Timber.d("PokedexViewModel is ready");
    }


    public MutableLiveData<Resource> observePokedex(){
        return pokedexLiveData;
    }



    public void searchPokedex() {

        //Timber.d("Searching user " + nsid + " page " + pageNumber + " list of pictures");
        //isPerformingQuery = true;


        pokemonRepository.searchPokedex()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<PokedexResults>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(PokedexResults pokedexResults) {

                        pokemons.setValue(pokedexResults.pokemons);
                        numberPokemons.setValue(pokedexResults.count);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        pokedexLiveData.setValue(Resource.error(throwable.getMessage(), "Execution error"));
                    }
                });

        /*
        disposables.add(repository.searchPhotoList(nsid, String.valueOf(pageNumber))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                        galleryLiveData.setValue(ApiResponse.loading());
                    }
                })
                .subscribe(
                        new Consumer<PhotoListResponse>() {
                            @Override
                            public void accept(PhotoListResponse photoListResponse) throws Exception {

                                Timber.e("accept: " + photoListResponse.toString());

                                ApiResponse response = CheckApiResponse.validate(photoListResponse);

                                if(response.status == Status.SUCCESS) {

                                    pages = photoListResponse.photos.pages;
                                    List<Picture> pictures = new ArrayList<>();

                                    for (PhotoListResponse.Photo photo : photoListResponse.photos.pictures) {
                                        pictures.add(new Picture(photo));
                                    }

                                    galleryLiveData.setValue(ApiResponse.success(pictures));
                                }
                                else{
                                    galleryLiveData.setValue(response);
                                }

                                isPerformingQuery = false;
                            }
                        },

                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                                Timber.e("Error on serch user: " + throwable.getMessage());
                                galleryLiveData.setValue(ApiResponse.error(throwable.getMessage()));
                                isPerformingQuery = false;
                            }
                        }

                ));
*/
    }




}
