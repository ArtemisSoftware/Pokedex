package com.artemissoftware.pokedex;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.constraintlayout.widget.ConstraintLayout;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    public ProgressBar mProgressBar;
    public SweetAlertDialog dialog;
    //public AwesomeSuccessDialog pDialog;
/*
    @Override
    public void setContentView(int layoutResID) {

        ConstraintLayout constraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout frameLayout = constraintLayout.findViewById(R.id.activity_content);
        mProgressBar = constraintLayout.findViewById(R.id.progress_bar);

        getLayoutInflater().inflate(layoutResID, frameLayout, true);

        //pDialog = new AwesomeSuccessDialog(this);


        super.setContentView(constraintLayout);
    }
*/
    public void showProgressBar(boolean visible) {

        mProgressBar.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }
}