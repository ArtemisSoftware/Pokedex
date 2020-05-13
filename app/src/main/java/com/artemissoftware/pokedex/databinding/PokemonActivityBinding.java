package com.artemissoftware.pokedex.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.artemissoftware.pokedex.ui.pokemon.NotesFragment;
import com.artemissoftware.pokedex.ui.pokemon.OnPokemonListener;
import com.artemissoftware.pokedex.ui.pokemon.adapters.NotesRecyclerAdapter;
import com.artemissoftware.pokedex.ui.pokemon.models.Note;

import java.util.List;

import me.gujun.android.taggroup.TagGroup;

public class PokemonActivityBinding {

    @BindingAdapter("tags")
    public static void setTags(TagGroup view, List<String> items) {

        if(items == null){
            return;
        }

        view.setTags(items);
    }




    @BindingAdapter({"notes", "onLongClick"})
    public static void setNotes(RecyclerView view, List<Note> registers, NotesFragment.OnNoteLongPressListener listener) {

        if(registers == null){
            return;
        }


        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        NotesRecyclerAdapter adapter = (NotesRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new NotesRecyclerAdapter(view.getContext(), registers, listener);
            view.setAdapter(adapter);
        }
        else{
            adapter.update(registers);
        }

    }

}
