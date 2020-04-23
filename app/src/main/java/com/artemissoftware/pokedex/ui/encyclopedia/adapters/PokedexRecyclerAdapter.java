package com.artemissoftware.pokedex.ui.encyclopedia.adapters;

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

    private List<PokemonInfo> mProducts = new ArrayList<>();
    private Context mContext;

    public PokedexRecyclerAdapter(Context context, List<PokemonInfo> products) {
        mProducts = products;
        mContext = context;
    }



    @Override
    public PokedexViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemPokedexBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_pokedex, parent, false);
        return new PokedexViewHolder(binding.getRoot());
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        PokemonInfo register = mProducts.get(position);
        ((PokedexViewHolder)holder).binding.setPokemon(register);
        //holder.binding.setIMainActivity((IMainActivity) mContext);

        ((PokedexViewHolder)holder).binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }


    public void refreshList(List<PokemonInfo> products){
        mProducts.clear();
        mProducts.addAll(products);
        notifyDataSetChanged();
    }
}
