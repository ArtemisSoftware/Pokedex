package com.artemissoftware.pokedex.util;

import java.text.DecimalFormat;

public class ImageUtil {


    public static String getPokemonImageUrl(String number){
        return ApiConstants.ALTERNATIVE_IMAGE_URL.replace("number", number);
    }

}
