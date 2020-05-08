package com.artemissoftware.pokedex.ui.pokemon.adapters;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.artemissoftware.pokedex.databinding.ItemNoteBinding;

public class NoteViewHolder extends RecyclerView.ViewHolder{

    ItemNoteBinding binding;

    public NoteViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }
}
