package com.artemissoftware.pokedex.ui.pokemon;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.artemissoftware.pokedex.repository.NoteRepository;
import com.artemissoftware.pokedex.repository.PokemonRepository;
import com.artemissoftware.pokedex.requests.models.PokemonResponse;
import com.artemissoftware.pokedex.requests.models.Post;
import com.artemissoftware.pokedex.util.Resource;
import com.artemissoftware.pokedex.ui.pokemon.models.Note;
import com.artemissoftware.pokedex.ui.pokemon.models.Pokemon;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
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


    @Inject
    public PokemonViewModel(NoteRepository noteRepository, PokemonRepository pokemonRepository){

        this.noteRepository = noteRepository;
        this.pokemonRepository = pokemonRepository;

        this.disposables = new CompositeDisposable();

        messageLiveData = new MutableLiveData<>();
        resourceLiveData = new MutableLiveData<>();
        pokemon = new MutableLiveData<>();
        notes = new MutableLiveData<>();



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
}
