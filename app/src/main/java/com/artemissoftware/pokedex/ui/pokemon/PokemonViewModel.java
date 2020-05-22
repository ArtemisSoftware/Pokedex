package com.artemissoftware.pokedex.ui.pokemon;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.artemissoftware.pokedex.database.PokemonDataBase;
import com.artemissoftware.pokedex.repository.NoteRepository;
import com.artemissoftware.pokedex.repository.PokemonRepository;
import com.artemissoftware.pokedex.requests.models.PokemonResponse;
import com.artemissoftware.pokedex.requests.models.Post;
import com.artemissoftware.pokedex.util.Resource;
import com.artemissoftware.pokedex.ui.pokemon.models.Note;
import com.artemissoftware.pokedex.ui.pokemon.models.Pokemon;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class PokemonViewModel extends ViewModel {


    private final CompositeDisposable disposables;

    private final NoteRepository noteRepository;
    private final PokemonRepository pokemonRepository;

    private MutableLiveData<Resource> resourceLiveData;
    private MutableLiveData<Resource> messageLiveData;
    public MutableLiveData<PokemonResponse> pokemon;
    public MutableLiveData<Resource> notes;
    public MutableLiveData<List<Pokemon>> pokemons;


    @Inject
    PokemonDataBase pokemonDataBase;

    @Inject
    public PokemonViewModel(NoteRepository noteRepository, PokemonRepository pokemonRepository){

        this.noteRepository = noteRepository;
        this.pokemonRepository = pokemonRepository;

        this.disposables = new CompositeDisposable();

        messageLiveData = new MutableLiveData<>();
        resourceLiveData = new MutableLiveData<>();
        pokemon = new MutableLiveData<>();
        notes = new MutableLiveData<>();
        pokemons = new MutableLiveData<>();


        Timber.d("Pokemon noteRepository: " + this.noteRepository);
        Timber.d("PokemonViewModel is working");

    }

    public MutableLiveData<Resource> observePokemon(){
        return resourceLiveData;
    }

    public MutableLiveData<Resource> observeMessages(){
        return messageLiveData;
    }

    public MutableLiveData<Resource> observeNotes(){
        return notes;
    }

    public MutableLiveData<List<Pokemon>> observeFavourites(){
        return pokemons;
    }


    @Override
    protected void onCleared() {
        disposables.clear();
    }



    public void searchPokemon(String id) {

        Timber.d("Searching pokemon with id: " + id);
        //isPerformingQuery = true;

        pokemonRepository.searchPokemon(id)
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

                        getFavourite(pokemonResponse.get(0).number);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        messageLiveData.setValue(Resource.error(throwable.getMessage(), "Execution error"));
                    }
                });

    }

    public void sendPost(Pokemon pokemon){

        Post post = new Post(Integer.parseInt(pokemon.getId()), pokemon.getName(), pokemon.getDescription());

        pokemonRepository.postPokemon(post)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(Post post) {

                        if(post.getId() == 101){
                            messageLiveData.setValue(Resource.success(post, "Pokemon posted"));
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        messageLiveData.setValue(Resource.error(throwable.getMessage(), "Execution error"));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    public void addNote(Note note){

        if(note.getId() == 0){
            insertNote(note);
        }
        else{
            updateNote(note);
        }

    }


    private void insertNote(Note note){

        noteRepository.insert(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(Long aLong) {
                        getNotes(note.getIdPokemon());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        messageLiveData.setValue(Resource.error(throwable.getMessage(), "Execution error"));
                    }
                });
    }


    private void updateNote(Note note){

        noteRepository.update(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(Integer aInt) {
                        getNotes(note.getIdPokemon());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        messageLiveData.setValue(Resource.error(throwable.getMessage(), "Execution error"));
                    }
                });
    }


    public void deleteNote(Note note){

        noteRepository.delete(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(Integer aInt) {
                        getNotes(note.getIdPokemon());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        messageLiveData.setValue(Resource.error(throwable.getMessage(), "Execution error"));
                    }
                });
    }


    public void getNotes(int idPokemon){

        disposables.add(
            noteRepository.getNotes(idPokemon)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new Consumer<List<Note>>() {
                                @Override
                                public void accept(List<Note> items) throws Exception {

                                    notes.setValue(Resource.success(items));
                                }
                            },
                            new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {

                                    messageLiveData.setValue(Resource.error(throwable.getMessage(), "Execution error"));
                                }
                            }
                    )
        );
    }




    public void setFavourite(boolean favourite, Pokemon pokemon){

        if(favourite == true){
            insertFavourite(pokemon);
        }
        else{
            deleteFavourite(pokemon);
        }
    }


    private void insertFavourite(Pokemon pokemon){

        pokemonRepository.insert(pokemon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(Long aLong) {
                        sendPost(pokemon);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        messageLiveData.setValue(Resource.error(throwable.getMessage(), "Execution error"));
                    }
                });
    }

    private void deleteFavourite(Pokemon pokemon){

        pokemonRepository.delete(pokemon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(Integer aInt) {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        messageLiveData.setValue(Resource.error(throwable.getMessage(), "Execution error"));
                    }
                });
    }


    private void getFavourite(String id){

        pokemonRepository.exists(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer exists) {

                        if(exists > 0) {
                            pokemon.getValue().favourite = true;
                        }

                        resourceLiveData.setValue(Resource.success(pokemon.getValue()));
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        messageLiveData.setValue(Resource.error(throwable.getMessage(), "Execution error"));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void getFavourites(){

        disposables.add(

            pokemonRepository.getFavourites()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new Consumer<List<Pokemon>>() {
                                @Override
                                public void accept(List<Pokemon> list) throws Exception {
                                    pokemons.setValue(list);
                                }
                            },
                            new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    messageLiveData.setValue(Resource.error(throwable.getMessage(), "Execution error"));
                                }
                            }
                    )
        );
    }




    public void removeFavourite(int position){

        
        /*
        Single.concatArray(noteRepository.delete(pokemons.getValue().get(position).getId()), pokemonRepository.delete(pokemons.getValue().get(position)))
                .observeOn(Schedulers.single()) // OFF UI THREAD
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        pokemonDataBase.beginTransaction();
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Timber.d("doOnComplete: ");
                        pokemonDataBase.setTransactionSuccessful();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Timber.d("doFinally: ");

                        if(pokemonDataBase.inTransaction() == true) {
                            pokemonDataBase.endTransaction();
                        }
                    }
                })
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread()) // ON UI THREAD

        .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }

        },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        messageLiveData.setValue(Resource.error(throwable.getMessage(), "Execution error"));
                    }
                }) ;
                        */


        Completable.concatArray(noteRepository.delete(pokemons.getValue().get(position).getId()), pokemonRepository.delete(pokemons.getValue().get(position).getId()))
                .observeOn(Schedulers.single()) // OFF UI THREAD

                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                        if(disposable != null) {
                            disposables.add(disposable);
                        }

                        pokemonDataBase.beginTransaction();
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Timber.d("doOnComplete: ");
                        pokemonDataBase.setTransactionSuccessful();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Timber.d("doFinally: ");

                        if(pokemonDataBase.inTransaction() == true) {
                            pokemonDataBase.endTransaction();
                        }
                    }
                })
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread()) // ON UI THREAD
                .subscribeWith(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onComplete() {
                        messageLiveData.setValue(Resource.success(position));
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        messageLiveData.setValue(Resource.error(throwable.getMessage(), "Execution error"));
                    }
                });


    }

}
