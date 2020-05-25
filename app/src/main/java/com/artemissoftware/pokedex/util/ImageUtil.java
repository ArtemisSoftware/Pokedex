package com.artemissoftware.pokedex.util;

import com.artemissoftware.pokedex.R;

import java.text.DecimalFormat;
import java.util.Random;

public class ImageUtil {


    public static String getPokemonImageUrl(String number){
        return ApiConstants.ALTERNATIVE_IMAGE_URL.replace("number", number);
    }


    public static int getRandomBackground() {

        int[] array = {R.drawable.pokemon_bg_1, R.drawable.pokemon_bg_6};
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

}
