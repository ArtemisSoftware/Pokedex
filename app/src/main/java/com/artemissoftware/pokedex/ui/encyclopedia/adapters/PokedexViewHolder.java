package com.artemissoftware.pokedex.ui.encyclopedia.adapters;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.artemissoftware.pokedex.databinding.ItemPokedexBinding;

public class PokedexViewHolder extends RecyclerView.ViewHolder{

    ItemPokedexBinding binding;

    public PokedexViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }
}

