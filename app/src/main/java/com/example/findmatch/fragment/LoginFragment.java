package com.example.findmatch.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.findmatch.R;
import com.example.findmatch.activity.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button buttonLogin = (Button) view.findViewById(R.id.button_login);
        TextView textNoAccount = (TextView) view.findViewById(R.id.textView_noAccountYet);

        buttonLogin.setOnClickListener(this);
        textNoAccount.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_login) {
            //EditText inputUsername = (EditText)view.findViewById(R.id.editText_username);
            Intent homeIntent = new Intent(getActivity(), HomeActivity.class);
            //homeIntent.putExtra("username", inputUsername.getText().toString());
            startActivity(homeIntent);
        }

        if (v.getId() == R.id.textView_noAccountYet) {
            RegisterFragment mRegisterFragment = new RegisterFragment();
            FragmentManager mFragmentManager = getFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

            mFragmentTransaction.replace(R.id.screenLoginRegister, mRegisterFragment, RegisterFragment.class.getSimpleName());
            mFragmentTransaction.addToBackStack(null);

            mFragmentTransaction.commit();
        }
    }
}
