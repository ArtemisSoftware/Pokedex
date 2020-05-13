package com.artemissoftware.pokedex.ui.pokemon.adapters;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.artemissoftware.pokedex.databinding.ItemNoteBinding;
import com.artemissoftware.pokedex.ui.pokemon.NotesFragment;

public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

    ItemNoteBinding binding;
    private NotesFragment.OnNoteLongPressListener onItemLongListener;


    public NoteViewHolder(View itemView, NotesFragment.OnNoteLongPressListener onItemLongListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemLongListener = onItemLongListener;
        itemView.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
        onItemLongListener.onNoteLongClick(binding.getNote());//onItemLongClick(/*getAdapterPosition()*/);
        return true;
    }
}
