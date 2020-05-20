package com.artemissoftware.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.artemissoftware.pokedex.databinding.ActivityMainBinding;
import com.artemissoftware.pokedex.ui.pokepidia.PokedexActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.crdPokedex.setOnClickListener(this);
        //Intent intent = new Intent(this, PokedexActivity.class);
        //startActivity(intent);

    }

    @Override
    public void onClick(View v) {

        Intent intent = null;


        switch (v.getId()){

            case R.id.crd_pokedex:

                intent = new Intent(this, PokedexActivity.class);
                break;

            default:

                break;

        }

        if(intent != null){
            startActivity(intent);
        }
    }
}
