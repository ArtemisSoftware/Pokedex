package com.artemissoftware.pokedex.ui.pokepidia;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.artemissoftware.pokedex.BaseActivity;
import com.artemissoftware.pokedex.R;
import com.artemissoftware.pokedex.databinding.ActivityPokedexBinding;
import com.artemissoftware.pokedex.util.Resource;
import com.artemissoftware.pokedex.ui.pokepidia.adapters.OnPokedexListener;
import com.artemissoftware.pokedex.ui.pokemon.PokemonActivity;
import com.artemissoftware.pokedex.util.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

public class PokedexActivity extends BaseActivity implements OnPokedexListener {

    ActivityPokedexBinding mainBinding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private PokedexViewModel viewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_pokedex);
        
        viewModel = ViewModelProviders.of(this, providerFactory).get(PokedexViewModel.class);

        ActivityPokedexBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_pokedex);

        mainBinding.setLifecycleOwner(this);
        mainBinding.setViewmodel(viewModel);

        subscribeObservers();

        viewModel.searchPokedex();

    }


    private void subscribeObservers() {

        viewModel.observePokedex().observe(this, new Observer<Resource>() {
            @Override
            public void onChanged(Resource resource) {


                //Timber.d("onChanged: " + resource.toString());

                switch (resource.status){

                    case SUCCESS:

                        //loadQuestions((List<Question>) resource.data);
                        break;

                    case ERROR:

                        break;

                }
            }
        });
    }


    @Override
    public void onPokedexClick(String id) {

        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.key_pokemon_id), id);

        Intent intent = new Intent(this, PokemonActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
