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
import com.artemissoftware.pokedex.requests.models.PokemonResponse;
import com.artemissoftware.pokedex.ui.Resource;
import com.artemissoftware.pokedex.ui.pokemon.adapters.InfoPagerAdapter;
import com.artemissoftware.pokedex.util.viewmodel.ViewModelProviderFactory;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

public class PokemonActivity extends BaseActivity implements OnPokemonListener, NoteDialogFragment.NoteDialogListener{


    ActivityPokemonBinding activityPokemonBinding;


    @Inject
    ViewModelProviderFactory providerFactory;

    private PokemonViewModel viewModel;


    private static final int ABOUT_FRAGMENT = 0;
    private AboutFragment aboutFragment;

    private static final int NOTES_FRAGMENT = 1;
    private NotesFragment notesFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);


        viewModel = ViewModelProviders.of(this, providerFactory).get(PokemonViewModel.class);


        activityPokemonBinding = DataBindingUtil.setContentView(this, R.layout.activity_pokemon);
        activityPokemonBinding.setListener(this);
        activityPokemonBinding.setLifecycleOwner(this);
        activityPokemonBinding.setViewmodel(viewModel);


        final Toolbar toolbar = findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("Parallax Tabs");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setupViewPager();


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


    }


    private void setupViewPager(){

        InfoPagerAdapter adapter = new InfoPagerAdapter(getSupportFragmentManager());

        aboutFragment = new AboutFragment();
        notesFragment = new NotesFragment();

        adapter.addFragment(aboutFragment);
        adapter.addFragment(notesFragment);


        activityPokemonBinding.viewpagerContainer.setAdapter(adapter);

        activityPokemonBinding.tab.setupWithViewPager(activityPokemonBinding.viewpagerContainer);

        activityPokemonBinding.tab.getTabAt(ABOUT_FRAGMENT).setText(getString(R.string.tag_fragment_about));
        activityPokemonBinding.tab.getTabAt(NOTES_FRAGMENT).setText(getString(R.string.tag_fragment_notes));

    }



    private void subscribeObservers() {

        viewModel.observePokemon().observe(this, new Observer<Resource>() {
            @Override
            public void onChanged(Resource resource) {


                //Timber.d("onChanged: " + resource.toString());

                switch (resource.status){

                    case SUCCESS:

                        ((InfoPagerAdapter) activityPokemonBinding.viewpagerContainer.getAdapter()).update((PokemonResponse) resource.data);
                        ((InfoPagerAdapter) activityPokemonBinding.viewpagerContainer.getAdapter()).updateNotes(viewModel.getNotes());
                        break;


                    case ERROR:

                        break;

                }
            }
        });
    }


    @Override
    public void setFavourite() {

    }

    @Override
    public void showNoteDialog(PokemonResponse response) {

        NoteDialogFragment dialog = NoteDialogFragment.newInstance(response.name);
        dialog.show(getSupportFragmentManager(), getString(R.string.note));
    }

    @Override
    public void saveNote(String note) {

    }
}
