package com.example.findmatch.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.findmatch.R;
import com.example.findmatch.adapter.MatchItemAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchRecyclerCardFragment extends Fragment {

    RecyclerView recyclerViewMatch;
    String sportTag[];
    String sportLocation[];
    String sportAddressLocation[];
    String statusPlay[];
    String spotAvailable[];

    public MatchRecyclerCardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_match_recycler_card, container, false);

        recyclerViewMatch = (RecyclerView)view.findViewById(R.id.recyclerViewMatch);
        sportTag = getResources().getStringArray(R.array.sportTag);
        sportLocation = getResources().getStringArray(R.array.sportLocation);
        sportAddressLocation = getResources().getStringArray(R.array.sportAddressLocation);
        statusPlay = getResources().getStringArray(R.array.statusPlay);
        spotAvailable = getResources().getStringArray(R.array.spotAvailable);

        MatchItemAdapter mMatchItem = new MatchItemAdapter(getContext(), sportTag, sportLocation, sportAddressLocation, statusPlay, spotAvailable);
        recyclerViewMatch.setAdapter(mMatchItem);
        recyclerViewMatch.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}
