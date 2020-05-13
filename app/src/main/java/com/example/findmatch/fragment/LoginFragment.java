package com.example.findmatch.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findmatch.R;
import com.example.findmatch.activity.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private Button buttonLogin;
    private TextView textNoAccount;
    private EditText editTextEmail, editTextPassword;
    private ProgressBar progressBarOnLogin;

    private FirebaseAuth mAuth;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        buttonLogin = (Button) view.findViewById(R.id.button_login);
        textNoAccount = (TextView) view.findViewById(R.id.textView_noAccountYet);
        editTextEmail = (EditText) view.findViewById(R.id.editText_email);
        editTextPassword = (EditText) view.findViewById(R.id.editText_password);
        progressBarOnLogin = (ProgressBar) view.findViewById(R.id.progressBar_onLogin);

        mAuth = FirebaseAuth.getInstance();

        buttonLogin.setOnClickListener(this);
        textNoAccount.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_login) {
            loginUser();
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

    public void loginUser() {
        final String emailInput = editTextEmail.getText().toString().trim();
        final String passwordInput = editTextPassword.getText().toString().trim();

        if (emailInput.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if(passwordInput.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        progressBarOnLogin.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(emailInput, passwordInput)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBarOnLogin.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "User login success", Toast.LENGTH_SHORT).show();
                    Intent homeIntent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(homeIntent);
                } else {
                    Toast.makeText(getContext(),
                            task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
