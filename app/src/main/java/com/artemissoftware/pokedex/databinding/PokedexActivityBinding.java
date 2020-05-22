package com.artemissoftware.pokedex.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.artemissoftware.pokedex.requests.models.PokedexResults;
import com.artemissoftware.pokedex.ui.favourites.adapters.FavouritesRecyclerAdapter;
import com.artemissoftware.pokedex.ui.pokemon.models.Pokemon;
import com.artemissoftware.pokedex.ui.pokepidia.adapters.PokedexRecyclerAdapter;

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


    @BindingAdapter("favourites")
    public static void setFavourites(RecyclerView view, List<Pokemon> registers) {

        if(registers == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        FavouritesRecyclerAdapter adapter = (FavouritesRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new FavouritesRecyclerAdapter(registers);

            view.setItemAnimator(new DefaultItemAnimator());
            view.getItemAnimator().setRemoveDuration(500);
            view.setAdapter(adapter);
        }
        /*
        else{
            adapter.update(registers);
        }
        */
    }


}
