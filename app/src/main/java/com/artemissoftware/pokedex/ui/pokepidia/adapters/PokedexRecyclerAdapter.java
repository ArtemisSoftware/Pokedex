package com.artemissoftware.pokedex.ui.pokepidia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.artemissoftware.pokedex.R;
import com.artemissoftware.pokedex.databinding.ItemPokedexBinding;
import com.artemissoftware.pokedex.requests.models.PokedexResults.*;

import java.util.ArrayList;
import java.util.List;

public class PokedexRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<PokemonInfo> registers = new ArrayList<>();
    private Context context;

    public PokedexRecyclerAdapter(Context context, List<PokemonInfo> items) {
        registers = items;
        this.context = context;
    }



    @Override
    public PokedexViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemPokedexBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_pokedex, parent, false);
        return new PokedexViewHolder(binding.getRoot());
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        PokemonInfo register = registers.get(position);
        ((PokedexViewHolder)holder).binding.setPokemon(register);
        ((PokedexViewHolder)holder).binding.setListener((OnPokedexListener) context);

        ((PokedexViewHolder)holder).binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return registers.size();
    }


    public void refreshList(List<PokemonInfo> products){
        registers.clear();
        registers.addAll(products);
        notifyDataSetChanged();
    }
}
