package com.example.findmatch.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.findmatch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchPeopleFragment extends Fragment {

    public MatchPeopleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_match_people, container, false);

        MatchRecyclerCardFragment mMatchRecyclerCardFragment = new MatchRecyclerCardFragment();

        FragmentManager mFragmentManager = getChildFragmentManager();
        FragmentTransaction mFragmentTranscation = mFragmentManager.beginTransaction();
        mFragmentTranscation.add(R.id.container_fragmentMatchPeople, mMatchRecyclerCardFragment, MatchRecyclerCardFragment.class.getSimpleName());

        mFragmentTranscation.commit();

        return view;
    }
}
