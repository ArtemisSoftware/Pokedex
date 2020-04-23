package com.artemissoftware.pokedex.ui.encyclopedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.artemissoftware.pokedex.R;
import com.artemissoftware.pokedex.ui.Resource;
import com.artemissoftware.pokedex.util.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

public class PokedexActivity extends AppCompatActivity {


    private PokedexViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);


        viewModel = ViewModelProviders.of(this, providerFactory).get(PokedexViewModel.class);

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
}
