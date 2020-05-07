package com.artemissoftware.pokedex.ui.pokemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.artemissoftware.pokedex.BaseActivity;
import com.artemissoftware.pokedex.R;
import com.artemissoftware.pokedex.databinding.ActivityPokemonBinding;
import com.artemissoftware.pokedex.ui.Resource;
import com.artemissoftware.pokedex.ui.pokemon.adapters.InfoPagerAdapter;
import com.artemissoftware.pokedex.util.viewmodel.ViewModelProviderFactory;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

public class PokemonActivity extends BaseActivity {


    @Inject
    ViewModelProviderFactory providerFactory;

    private PokemonViewModel viewModel;


    private static final int ABOUT_FRAGMENT = 0;

    private ViewPager viewpager_container;

    private AboutFragment aboutFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);


        viewModel = ViewModelProviders.of(this, providerFactory).get(PokemonViewModel.class);

        viewpager_container = findViewById(R.id.viewpager_container);



        ActivityPokemonBinding activityPokemonBinding = DataBindingUtil.setContentView(this, R.layout.activity_pokemon);

        activityPokemonBinding.setLifecycleOwner(this);
        activityPokemonBinding.setViewmodel(viewModel);

        subscribeObservers();


        Bundle bundle = getIntent().getExtras();

        String id = "";

        if(bundle != null) {
            id = bundle.getString(getString(R.string.key_pokemon_id));
            viewModel.searchPokemon(id);
        }



/*
        Toolbar myToolbar = (Toolbar) findViewById(R.id.z_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ctl.setTitle("Best Coupons Deals");

*/

        final Toolbar toolbar = findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("Parallax Tabs");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setupViewPager();
    }


    private void setupViewPager(){

        InfoPagerAdapter adapter = new InfoPagerAdapter(getSupportFragmentManager());

        aboutFragment = new AboutFragment();
        //mPhotoFragment = new PhotoFragment();

        adapter.addFragment(aboutFragment);
        //adapter.addFragment(aboutFragment);
        //adapter.addFragment(mPhotoFragment);


        viewpager_container.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewpager_container);

        tabLayout.getTabAt(ABOUT_FRAGMENT).setText(getString(R.string.tag_fragment_about));
        //tabLayout.getTabAt(1).setText("getString(R.string.tag_fragment_about)");
        //tabLayout.getTabAt(PHOTO_FRAGMENT).setText(getString(R.string.tag_fragment_photo));

    }



    private void subscribeObservers() {

        viewModel.observePokemon().observe(this, new Observer<Resource>() {
            @Override
            public void onChanged(Resource resource) {


                //Timber.d("onChanged: " + resource.toString());

                switch (resource.status){

                    case ERROR:

                        break;

                }
            }
        });
    }


}
