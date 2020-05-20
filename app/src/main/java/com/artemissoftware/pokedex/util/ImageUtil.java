package com.artemissoftware.pokedex.util;

import java.text.DecimalFormat;

public class ImageUtil {


    public String referenceId(String number){

        DecimalFormat df = new DecimalFormat("000");
        return "#" + df.format(Integer.parseInt(number));
    }


    public String getPokemonImageUrl(String number){
        return ApiConstants.ALTERNATIVE_IMAGE_URL.replace("number", number);
    }

}
