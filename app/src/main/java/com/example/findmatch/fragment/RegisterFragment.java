package com.example.findmatch.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.findmatch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    private EditText editTextEmail, editTextPassword;
    private Button buttonNext;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        editTextEmail = (EditText) view.findViewById(R.id.editText_emailOnRegister);
        editTextPassword = (EditText) view.findViewById(R.id.editText_passwordOnRegister);
        buttonNext = (Button) view.findViewById(R.id.button_next);

        buttonNext.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_next) {
            UserDetailInfoRegisterFragment
                    mUserDetailInfoRegisterFragment = new UserDetailInfoRegisterFragment();
            FragmentManager mFragmentManager = getFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager
                    .beginTransaction();
            String emailInput = editTextEmail.getText().toString().trim();
            String passwordInput = editTextPassword.getText().toString().trim();

            if (emailInput.isEmpty()) {
                editTextEmail.setError("Email is required");
                editTextEmail.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                editTextEmail.setError("Please enter a valid email");
                editTextEmail.requestFocus();
                return;
            }
            if (passwordInput.isEmpty()) {
                editTextPassword.setError("Password is required");
                editTextPassword.requestFocus();
                return;
            }
            if (passwordInput.length() < 6) {
                editTextPassword.setError("Minimum password is 6 character");
                editTextPassword.requestFocus();
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putString("emailInput", emailInput);
            bundle.putString("passwordInput", passwordInput);
            mUserDetailInfoRegisterFragment.setArguments(bundle);

            mFragmentTransaction.add(R.id.screenLoginRegister,
                    mUserDetailInfoRegisterFragment,
                    UserDetailInfoRegisterFragment.class.getSimpleName());
            mFragmentTransaction.addToBackStack(null);

            mFragmentTransaction.commit();
        }
    }
}
