package com.example.findmatch.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.findmatch.fragment.LoginFragment;
import com.example.findmatch.R;
import com.example.findmatch.fragment.RegisterFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView_signIn, textView_signUp;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_signIn = (TextView) findViewById(R.id.textView_signIn);
        textView_signUp = (TextView) findViewById(R.id.textView_signUp);

        LoginFragment mLoginFragment = new LoginFragment();
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

        mAuth = FirebaseAuth.getInstance();

        textView_signIn.setOnClickListener(this);
        textView_signUp.setOnClickListener(this);

        mFragmentTransaction.add(R.id.screenLoginRegister, mLoginFragment, LoginFragment.class.getSimpleName());
        mFragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(homeIntent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.textView_signIn) {
            LoginFragment mLoginFragment = new LoginFragment();
            FragmentManager mFragmentManager = getSupportFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

            mFragmentTransaction.replace(R.id.screenLoginRegister, mLoginFragment, LoginFragment.class.getSimpleName());
            mFragmentTransaction.addToBackStack(null);

            mFragmentTransaction.commit();
        }
        if (v.getId() == R.id.textView_signUp) {
            RegisterFragment mRegisterFragment = new RegisterFragment();
            FragmentManager mFragmentManager = getSupportFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

            mFragmentTransaction.replace(R.id.screenLoginRegister, mRegisterFragment, RegisterFragment.class.getSimpleName());
            mFragmentTransaction.addToBackStack(null);

            mFragmentTransaction.commit();
        }
    }
}
