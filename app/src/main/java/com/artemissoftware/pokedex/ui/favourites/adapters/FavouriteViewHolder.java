package com.artemissoftware.pokedex.ui.favourites.adapters;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.artemissoftware.pokedex.databinding.ItemFavouriteBinding;

public class FavouriteViewHolder extends RecyclerView.ViewHolder {

    ItemFavouriteBinding binding;

    public FavouriteViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }


    public ItemFavouriteBinding getBinding() {
        return binding;
    }
}
