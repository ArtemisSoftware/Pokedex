package com.artemissoftware.pokedex.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.artemissoftware.pokedex.requests.models.PokedexResults;
import com.artemissoftware.pokedex.ui.encyclopedia.adapters.PokedexRecyclerAdapter;

import java.util.List;

public class PokedexActivityBinding {

    private static final int NUM_COLUMNS = 2;

    @BindingAdapter("pokedex")
    public static void setPokedex(RecyclerView view, List<PokedexResults.PokemonInfo> registers) {

        if(registers == null){
            return;
        }


        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new GridLayoutManager(view.getContext(), NUM_COLUMNS));
        }

        PokedexRecyclerAdapter adapter = (PokedexRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new PokedexRecyclerAdapter(view.getContext(), registers);
            view.setAdapter(adapter);
        }

    }

}
