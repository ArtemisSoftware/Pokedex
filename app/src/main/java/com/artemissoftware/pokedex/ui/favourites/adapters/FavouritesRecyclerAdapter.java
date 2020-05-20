package com.artemissoftware.pokedex.ui.favourites.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.artemissoftware.pokedex.R;
import com.artemissoftware.pokedex.databinding.ItemFavouriteBinding;
import com.artemissoftware.pokedex.ui.pokemon.models.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class FavouritesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Pokemon> items = new ArrayList<>();

    public FavouritesRecyclerAdapter(List<Pokemon> items) {
        this.items = items;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemFavouriteBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_favourite, parent, false);
        return new FavouriteViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Pokemon register = items.get(position);
        ((FavouriteViewHolder)holder).binding.setPokemon(register);

        ((FavouriteViewHolder)holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
