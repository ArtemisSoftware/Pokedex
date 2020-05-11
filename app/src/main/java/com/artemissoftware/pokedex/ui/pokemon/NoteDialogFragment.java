package com.artemissoftware.pokedex.ui.pokemon;

import android.os.Bundle;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.artemissoftware.pokedex.R;
import com.artemissoftware.pokedex.databinding.DialogFragmentNoteBinding;

public class NoteDialogFragment extends DialogFragment {


    private static final String ARG_NAME = "name";
    private static final String ARG_NOTE = "note";


    public NoteDialogFragment() {
        // Empty constructor required for DialogFragment
    }


    public static NoteDialogFragment newInstance(String name) {
        NoteDialogFragment frag = new NoteDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        DialogFragmentNoteBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_fragment_note, null, false);

        initDialog(binding);


        String title = getActivity().getString(R.string.note);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setView(binding.getRoot());

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


    private void initDialog(DialogFragmentNoteBinding binding) {

        if (getArguments() != null) {
            binding.txtName.setText(getArguments().getString(ARG_NAME));
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
}