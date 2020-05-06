package com.artemissoftware.pokedex.ui.pokemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.artemissoftware.pokedex.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class PokemonActivity extends AppCompatActivity {


    private ViewPager viewpager_container;


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
        /*
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        mGalleryFragment = new GalleryFragment();
        mPhotoFragment = new PhotoFragment();

        adapter.addFragment(mGalleryFragment);
        adapter.addFragment(mPhotoFragment);


        viewpager_container.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewpager_container);

        tabLayout.getTabAt(GALLERY_FRAGMENT).setText(getString(R.string.tag_fragment_gallery));
        tabLayout.getTabAt(PHOTO_FRAGMENT).setText(getString(R.string.tag_fragment_photo));
        */
    }


}
