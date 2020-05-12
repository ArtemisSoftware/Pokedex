package com.artemissoftware.pokedex.ui.pokemon;

import android.content.Context;
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
import com.artemissoftware.pokedex.ui.pokemon.models.Note;

import java.util.Date;

public class NoteDialogFragment extends DialogFragment {


    private DialogFragmentNoteBinding binding;

    private NoteDialogListener listener;

    private static final String ARG_ID_POKEMON = "idPokemon";
    private static final String ARG_NAME = "name";
    private static final String ARG_NOTE = "note";


    public NoteDialogFragment() {
        // Empty constructor required for DialogFragment
    }


    public static NoteDialogFragment newInstance(String idPokemon, String name) {
        NoteDialogFragment frag = new NoteDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ID_POKEMON, idPokemon);
        args.putString(ARG_NAME, name);
        frag.setArguments(args);
        return frag;
    }

    public static NoteDialogFragment newInstance(String name, Note note) {
        NoteDialogFragment frag = new NoteDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putParcelable(ARG_NOTE, note);
        frag.setArguments(args);
        return frag;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_fragment_note, null, false);

        initDialog();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(getActivity().getString(R.string.note));
        alertDialogBuilder.setView(binding.getRoot());

        alertDialogBuilder.setPositiveButton("OK",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveNote();
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


    private void initDialog() {

        if (getArguments() != null) {
            binding.txtName.setText(getArguments().getString(ARG_NAME));

            if(getArguments().containsKey(ARG_NOTE) == true){

                binding.setNote(getArguments().getParcelable(ARG_NOTE));
            }
        }
    }


    /**
     * Call this method to send the data back to the parent fragment
     */
    public void saveNote() {

        Note note;

        if(getArguments().containsKey(ARG_NOTE) == true){
            note = getArguments().getParcelable(ARG_NOTE);

            note.setDescription(binding.txtInpNote.getText().toString());
            note.setRegisterDate(new Date());
        }
        else{
            note = new Note(Integer.parseInt(getArguments().getString(ARG_ID_POKEMON)), binding.txtInpNote.getText().toString(), new Date());
        }


        listener.saveNote(note);
        dismiss();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof NoteDialogListener) {
            listener = (NoteDialogListener) context;
        }
        else {
            throw new RuntimeException(context.toString() + " must implement NoteDialogListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    public interface NoteDialogListener {

        void saveNote(Note note);
    }


}