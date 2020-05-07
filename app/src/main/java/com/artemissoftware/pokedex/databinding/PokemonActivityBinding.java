package com.artemissoftware.pokedex.databinding;

import androidx.databinding.BindingAdapter;

import com.artemissoftware.pokedex.requests.models.PokemonResponse;

import java.util.List;

import me.gujun.android.taggroup.TagGroup;

public class PokemonActivityBinding {

    @BindingAdapter("tags")
    public static void setTags(TagGroup view, List<String> items) {

        if(items == null){
            return;
        }

        view.setTags(items);
    }

}
