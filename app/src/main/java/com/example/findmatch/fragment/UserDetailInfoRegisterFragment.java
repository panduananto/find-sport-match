package com.example.findmatch.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.findmatch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailInfoRegisterFragment extends Fragment {


    public UserDetailInfoRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_detail_info_register, container, false);

        return view;
    }
}
