package com.example.findmatch.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.findmatch.R;
import com.example.findmatch.adapter.UserMatchAdapter;
import com.example.findmatch.model.UserMatchModel;
import com.example.findmatch.viewmodel.SportItemViewModel;
import com.example.findmatch.viewmodel.UserMatchViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchRecyclerCardFragment extends Fragment {

    private UserMatchViewModel mUserMatchViewModel;
    private UserMatchAdapter mUserMatchAdapter;
    private RecyclerView recyclerViewMatch;


    public MatchRecyclerCardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_match_recycler_card, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewMatch = (RecyclerView)view.findViewById(R.id.recyclerViewMatch);
        mUserMatchAdapter = new UserMatchAdapter();

        recyclerViewMatch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMatch.setAdapter(mUserMatchAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mUserMatchViewModel = new ViewModelProvider(getActivity()).get(UserMatchViewModel.class);
        mUserMatchViewModel
                .getUserMatchModelData()
                .observe(getViewLifecycleOwner(), new Observer<List<UserMatchModel>>() {
            @Override
            public void onChanged(List<UserMatchModel> userMatchModelList) {
                mUserMatchAdapter.setmUserMatchModel(userMatchModelList);
                mUserMatchAdapter.notifyDataSetChanged();
            }
        });
    }
}
