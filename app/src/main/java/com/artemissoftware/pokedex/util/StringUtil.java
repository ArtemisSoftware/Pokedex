package com.artemissoftware.pokedex.util;

import java.text.DecimalFormat;

public class StringUtil {


    public static String reference(String number){

        DecimalFormat df = new DecimalFormat("000");
        return "#" + df.format(Integer.parseInt(number));
    }


    public static String size(int number){
        return number + "";
    }


}
