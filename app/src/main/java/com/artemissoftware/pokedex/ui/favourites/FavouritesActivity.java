package com.artemissoftware.pokedex.ui.favourites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.artemissoftware.pokedex.BaseActivity;
import com.artemissoftware.pokedex.R;
import com.artemissoftware.pokedex.databinding.ActivityFavouritesBinding;
import com.artemissoftware.pokedex.ui.favourites.adapters.FavouriteViewHolder;
import com.artemissoftware.pokedex.ui.favourites.adapters.FavouritesRecyclerAdapter;
import com.artemissoftware.pokedex.ui.pokemon.PokemonViewModel;
import com.artemissoftware.pokedex.util.Resource;
import com.artemissoftware.pokedex.util.adapters.RecyclerItemTouchHelper;
import com.artemissoftware.pokedex.util.adapters.RecyclerItemTouchHelperListener;
import com.artemissoftware.pokedex.util.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class FavouritesActivity extends BaseActivity implements RecyclerItemTouchHelperListener {


    private ActivityFavouritesBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private PokemonViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this, providerFactory).get(PokemonViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourites);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(viewModel);


        dialog = new SweetAlertDialog(this);
        subscribeObservers();

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.rclFavorites);

        viewModel.getFavourites();
    }


    private void subscribeObservers() {

        viewModel.observeMessages().observe(this, new Observer<Resource>() {
            @Override
            public void onChanged(Resource resource) {

                switch (resource.status){

                    case SUCCESS:

                        ((FavouritesRecyclerAdapter)binding.rclFavorites.getAdapter()).removeItem((Integer) resource.data);
                        break;

                    case ERROR:

                        dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText(resource.message)
                                .setContentText((String) resource.data)
                                .show();
                        break;

                }
            }
        });
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        if (viewHolder instanceof FavouriteViewHolder) {
            viewModel.removeFavourite(viewHolder.getAdapterPosition());
        }

    }
}
