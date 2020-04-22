package com.artemissoftware.pokedex.ui.encyclopedia;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.artemissoftware.pokedex.ui.Resource;

import io.reactivex.disposables.CompositeDisposable;

public class PokedexViewModel extends ViewModel {

    private final CompositeDisposable disposables;

    //private final PokemonRepository pokemonRepository;

    private MutableLiveData<Resource> pokedexLiveData;

    //@Inject
    public PokedexViewModel(/*PokemonRepository pokemonRepository*/) {

        //this.pokemonRepository = pokemonRepository;
        this.disposables = new CompositeDisposable();

        pokedexLiveData = new MutableLiveData<>();

        //Timber.d("Pokemon repository: " + this.pokemonRepository);
        //Timber.d("PokedexViewModel is ready");
    }


    public MutableLiveData<Resource> observePokedex(){
        return pokedexLiveData;
    }



    public void searchPokedex() {

        //Timber.d("Searching user " + nsid + " page " + pageNumber + " list of pictures");
        //isPerformingQuery = true;
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
