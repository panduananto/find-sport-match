package com.example.findmatch.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.findmatch.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    EditText editTextUsername, editTextEmail, editTextPassword;
    Button buttonSignUp;
    ProgressBar progressBarOnRegister;

    private FirebaseAuth mAuth;

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
        buttonSignUp = (Button) view.findViewById(R.id.button_signup);
        progressBarOnRegister = (ProgressBar) view.findViewById(R.id.progressbar_onRegister);

        buttonSignUp.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        return view;
    }

    private void registerUser() {
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

        progressBarOnRegister.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(emailInput, passwordInput)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBarOnRegister.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(),
                                    "User Registered Successfull",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(),
                                    "Some error occured",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_signup) {
            registerUser();
        }
    }
}
