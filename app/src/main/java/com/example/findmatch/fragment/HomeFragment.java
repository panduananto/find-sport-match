package com.example.findmatch.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.findmatch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView usernamePlaceholder = (TextView)view.findViewById(R.id.textView_usernamePlaceholder);

        RecyclerCardFragment mRecyclerCardFragment = new RecyclerCardFragment();

        FragmentManager mFragmentManager = getChildFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.container_recyclerViewOnFragmentHome, mRecyclerCardFragment, RecyclerCardFragment.class.getSimpleName());

        mFragmentTransaction.commit();

        return view;
    }
}
