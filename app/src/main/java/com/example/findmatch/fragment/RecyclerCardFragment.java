package com.example.findmatch.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.findmatch.R;
import com.example.findmatch.adapter.SportItemAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerCardFragment extends Fragment {

    RecyclerView recyclerView;
    String sportName[];
    int imageSport[] = {R.drawable.badminton_background, R.drawable.swimming_background, R.drawable.basketball_background, R.drawable.futsal_background};

    public RecyclerCardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_recycler_card, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        sportName = getResources().getStringArray(R.array.sportName);

        SportItemAdapter mSportItem = new SportItemAdapter(getContext(), sportName, imageSport);
        recyclerView.setAdapter(mSportItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}
