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
import com.example.findmatch.adapter.SportItemAdapter;
import com.example.findmatch.model.SportItemModel;
import com.example.findmatch.viewmodel.SportItemViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SportItemFragment extends Fragment {

    private RecyclerView recyclerView;
    private SportItemViewModel mSportItemViewModel;
    private SportItemAdapter mSportItemAdapter;

    public SportItemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_recycler_card, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mSportItemAdapter = new SportItemAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mSportItemAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mSportItemViewModel = new ViewModelProvider(getActivity()).get(SportItemViewModel.class);
        mSportItemViewModel.getSportItemModelData().observe(getViewLifecycleOwner(), new Observer<List<SportItemModel>>() {
            @Override
            public void onChanged(List<SportItemModel> sportItemModels) {
                mSportItemAdapter.setmSportItemModel(sportItemModels);
                mSportItemAdapter.notifyDataSetChanged();
            }
        });
    }
}
