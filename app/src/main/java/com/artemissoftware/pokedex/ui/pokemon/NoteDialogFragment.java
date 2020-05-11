package com.artemissoftware.pokedex.ui.pokemon;

import android.os.Bundle;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.artemissoftware.pokedex.R;

public class NoteDialogFragment extends DialogFragment {


    public NoteDialogFragment() {
        // Empty constructor required for DialogFragment
    }

    public static NoteDialogFragment newInstance(String title) {
        NoteDialogFragment frag = new NoteDialogFragment();
        //Bundle args = new Bundle();
        //args.putString("title", title);
        //frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment_note, null);


        String title = "Title";//getArguments().getString("title");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage("Are you sure?");

        alertDialogBuilder.setView(view);


        alertDialogBuilder.setPositiveButton("OK",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // on success
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }

        });

        return alertDialogBuilder.create();
    }
}