package com.artemissoftware.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.artemissoftware.pokedex.ui.encyclopedia.PokedexActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Intent intent = new Intent(this, PokedexActivity.class);
        //startActivity(intent);
    }
}
