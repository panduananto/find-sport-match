package com.example.findmatch.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.findmatch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchOwner extends Fragment {

    private Button buttonFinishMatch;
    private FrameLayout noMatchViewContainer;

    public MatchOwner() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_match_owner, container, false);

        buttonFinishMatch = (Button) view.findViewById(R.id.button_finishMatch);
        buttonFinishMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(MatchOwner.this).commit();
            }
        });

        return view;
    }
}
