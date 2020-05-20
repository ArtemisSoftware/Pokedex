package com.artemissoftware.pokedex.databinding;

import android.graphics.drawable.Drawable;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.artemissoftware.pokedex.ui.pokemon.NotesFragment;
import com.artemissoftware.pokedex.ui.pokemon.OnPokemonListener;
import com.artemissoftware.pokedex.ui.pokemon.adapters.NotesRecyclerAdapter;
import com.artemissoftware.pokedex.ui.pokemon.models.Note;
import com.sackcentury.shinebuttonlib.ShineButton;

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


    @BindingAdapter("favourite")
    public static void setFavourite(ShineButton view, boolean selected) {
         view.setChecked(selected);
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


    @BindingAdapter({"wallpaper"})
    public static void setWallpaper(ConstraintLayout view, int wallpaper) {

        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(ContextCompat.getDrawable(view.getContext(), wallpaper) );
        } else {
            view.setBackground(ContextCompat.getDrawable(view.getContext(), wallpaper));
        }

        view.getBackground().setAlpha(25);

    }

}
