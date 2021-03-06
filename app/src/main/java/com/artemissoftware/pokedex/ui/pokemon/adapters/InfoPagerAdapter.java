package com.artemissoftware.pokedex.ui.pokemon.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.artemissoftware.pokedex.requests.models.PokemonResponse;
import com.artemissoftware.pokedex.ui.pokemon.AboutFragment;
import com.artemissoftware.pokedex.ui.pokemon.NotesFragment;
import com.artemissoftware.pokedex.ui.pokemon.models.Note;

import java.util.ArrayList;
import java.util.List;

public class InfoPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();

    public InfoPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }


    public void addFragment(Fragment fragment){
        mFragmentList.add(fragment);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void update(PokemonResponse data) {

        ((AboutFragment) mFragmentList.get(0)).update(data);
    }

    public void updateNotes(List<Note> data) {

        ((NotesFragment) mFragmentList.get(1)).update(data);
    }
}

