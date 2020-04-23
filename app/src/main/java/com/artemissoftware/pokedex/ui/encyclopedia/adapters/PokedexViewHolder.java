package com.artemissoftware.pokedex.ui.encyclopedia.adapters;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class PokedexViewHolder extends RecyclerView.ViewHolder{

    ViewDataBinding binding;

    public PokedexViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }
}

