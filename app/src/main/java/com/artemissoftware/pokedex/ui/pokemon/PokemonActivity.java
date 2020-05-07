package com.artemissoftware.pokedex.ui.pokemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.artemissoftware.pokedex.R;
import com.artemissoftware.pokedex.ui.pokemon.adapters.InfoPagerAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

public class PokemonActivity extends AppCompatActivity {


    private static final int ABOUT_FRAGMENT = 0;



    private ViewPager viewpager_container;


    private AboutFragment aboutFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        Bundle bundle = getIntent().getExtras();

        String id = "";

        if(bundle != null)
            id = bundle.getString(getString(R.string.key_pokemon_id));


        viewpager_container = findViewById(R.id.viewpager_container);



/*
        Toolbar myToolbar = (Toolbar) findViewById(R.id.z_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ctl.setTitle("Best Coupons Deals");

*/
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


}
