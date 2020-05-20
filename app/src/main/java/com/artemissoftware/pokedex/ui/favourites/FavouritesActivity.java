package com.artemissoftware.pokedex.ui.favourites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.artemissoftware.pokedex.BaseActivity;
import com.artemissoftware.pokedex.R;
import com.artemissoftware.pokedex.databinding.ActivityFavouritesBinding;
import com.artemissoftware.pokedex.ui.pokemon.PokemonViewModel;
import com.artemissoftware.pokedex.util.Resource;
import com.artemissoftware.pokedex.util.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class FavouritesActivity extends BaseActivity {


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

        subscribeObservers();

        viewModel.getFavourites();
    }


    private void subscribeObservers() {

        viewModel.observeMessages().observe(this, new Observer<Resource>() {
            @Override
            public void onChanged(Resource resource) {

                switch (resource.status){


                    case ERROR:

                        new SweetAlertDialog(getApplicationContext(), SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Oops...")
                                .setContentText("Something went wrong!")
                                .show();
                        break;

                }
            }
        });
    }
}
