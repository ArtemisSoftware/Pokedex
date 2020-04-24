package com.artemissoftware.pokedex.ui.encyclopedia;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.artemissoftware.pokedex.BaseActivity;
import com.artemissoftware.pokedex.R;
import com.artemissoftware.pokedex.databinding.ActivityPokedexBinding;
import com.artemissoftware.pokedex.requests.models.PokedexResults;
import com.artemissoftware.pokedex.ui.Resource;
import com.artemissoftware.pokedex.ui.encyclopedia.adapters.OnPokedexListener;
import com.artemissoftware.pokedex.ui.encyclopedia.adapters.PokedexRecyclerAdapter;
import com.artemissoftware.pokedex.util.viewmodel.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PokedexActivity extends BaseActivity implements OnPokedexListener {

    ActivityPokedexBinding mainBinding;

    private PokedexViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

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

    }
}
