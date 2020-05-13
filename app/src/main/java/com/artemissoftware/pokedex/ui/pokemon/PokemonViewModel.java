package com.artemissoftware.pokedex.ui.pokemon;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.artemissoftware.pokedex.repository.NoteRepository;
import com.artemissoftware.pokedex.repository.PokemonRepository;
import com.artemissoftware.pokedex.requests.models.PokemonResponse;
import com.artemissoftware.pokedex.ui.Resource;
import com.artemissoftware.pokedex.ui.pokemon.models.Note;

import java.util.List;

import javax.inject.Inject;

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
    public MutableLiveData<PokemonResponse> pokemon;
    public MutableLiveData<Resource> notes;


    @Inject
    public PokemonViewModel(NoteRepository noteRepository, PokemonRepository pokemonRepository){

        this.noteRepository = noteRepository;
        this.pokemonRepository = pokemonRepository;

        this.disposables = new CompositeDisposable();

        resourceLiveData = new MutableLiveData<>();
        pokemon = new MutableLiveData<>();
        notes = new MutableLiveData<>();



        Timber.d("Pokemon noteRepository: " + this.noteRepository);
        Timber.d("PokemonViewModel is working");

    }

    public MutableLiveData<Resource> observePokemon(){
        return resourceLiveData;
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
                        resourceLiveData.setValue(Resource.success(pokemonResponse.get(0)));
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        resourceLiveData.setValue(Resource.error(throwable.getMessage(), "Execution error"));
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
                    public void onError(Throwable e) {

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
                    public void onError(Throwable e) {

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
                    public void onError(Throwable e) {

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

                                    //scoreLiveData.setValue(Resource.error(throwable.getMessage(), "Execution error"));
                                }
                            }
                    )
        );
    }


}
