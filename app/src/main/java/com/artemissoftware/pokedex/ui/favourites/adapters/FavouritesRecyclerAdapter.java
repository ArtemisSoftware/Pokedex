package com.artemissoftware.pokedex.ui.favourites.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.artemissoftware.pokedex.R;
import com.artemissoftware.pokedex.databinding.ItemFavouriteBinding;
import com.artemissoftware.pokedex.ui.favourites.model.Favourite;
import com.artemissoftware.pokedex.ui.pokemon.models.Pokemon;
import com.artemissoftware.pokedex.ui.pokepidia.adapters.OnPokedexListener;

import java.util.ArrayList;
import java.util.List;

public class FavouritesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Favourite> items = new ArrayList<>();
    private Context context;

    public FavouritesRecyclerAdapter(Context context, List<Favourite> items) {
        this.items = items;
        this.context = context;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemFavouriteBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_favourite, parent, false);
        return new FavouriteViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Favourite register = items.get(position);
        ((FavouriteViewHolder)holder).binding.setFavourite(register);
        ((FavouriteViewHolder)holder).binding.setListener((OnPokedexListener) context);

        ((FavouriteViewHolder)holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void removeItem(int position){

        items.remove(position);
        notifyDataSetChanged();
    }
}
