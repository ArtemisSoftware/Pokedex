package com.artemissoftware.pokedex.ui.pokemon;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.artemissoftware.pokedex.R;
import com.artemissoftware.pokedex.databinding.FragmentAboutBinding;
import com.artemissoftware.pokedex.requests.models.PokemonResponse;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AboutFragment.OnLikeListener} interface
 * to handle interaction events.
 * Use the {@link AboutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutFragment extends Fragment {

    private FragmentAboutBinding aboutBinding;
    private OnLikeListener listener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public AboutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        aboutBinding = FragmentAboutBinding.inflate(inflater);
        aboutBinding.setFragment(this);
        aboutBinding.setListener(listener);
        return aboutBinding.getRoot();
    }



    public void update(PokemonResponse pokemon) {
        aboutBinding.setPokemon(pokemon);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnLikeListener) {
            listener = (OnLikeListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnLikeListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    public interface OnLikeListener {
        void saveFourite(boolean checked);
    }


}
