package com.artemissoftware.pokedex.ui.pokemon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.artemissoftware.pokedex.R;
import com.artemissoftware.pokedex.databinding.ItemNoteBinding;
import com.artemissoftware.pokedex.ui.pokemon.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Note> items = new ArrayList<>();
    private Context mContext;

    public NotesRecyclerAdapter(Context context, List<Note> items) {
        this.items = items;
        mContext = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemNoteBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_note, parent, false);
        return new NoteViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Note register = items.get(position);
        ((NoteViewHolder)holder).binding.setNote(register);
        //((PokedexViewHolder)holder).binding.setListener((OnPokedexListener) mContext);

        ((NoteViewHolder)holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
