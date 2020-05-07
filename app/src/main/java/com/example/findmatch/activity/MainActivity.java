package com.example.findmatch.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.findmatch.fragment.LoginFragment;
import com.example.findmatch.R;
import com.example.findmatch.fragment.RegisterFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //deklarasi panggil textview sesuai id
        TextView textView_signIn = (TextView) findViewById(R.id.textView_signIn);
        textView_signIn.setOnClickListener(this);

        TextView textView_signUp = (TextView) findViewById(R.id.textView_signUp);
        textView_signUp.setOnClickListener(this);

        //deklarasi panggil fragment
        LoginFragment mLoginFragment = new LoginFragment();

        //deklarasi panggil fragment ke main_activity
        //default fragment yang muncul di main_activity adalah fragment_login
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

        //tambahkan LoginFragment ke dalam screenLoginRegister
        mFragmentTransaction.add(R.id.screenLoginRegister, mLoginFragment, LoginFragment.class.getSimpleName());

        mFragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        //kalo klik signin pindah ke loginFragment
        if (v.getId() == R.id.textView_signIn) {
            LoginFragment mLoginFragment = new LoginFragment();
            FragmentManager mFragmentManager = getSupportFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

            mFragmentTransaction.replace(R.id.screenLoginRegister, mLoginFragment, LoginFragment.class.getSimpleName());
            mFragmentTransaction.addToBackStack(null);

            mFragmentTransaction.commit();
        }
        //kalo klik signup pindah ke registerFragment
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
