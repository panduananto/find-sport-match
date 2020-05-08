package com.example.findmatch.fragment;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.findmatch.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailInfoRegisterFragment extends Fragment {

    private ImageView imageViewBackArrow;
    private EditText editTextUserName, editTextUserFullName;
    private EditText editTextUserFullAddress, editTextUserBio, editTextUserTelpNumber;
    private Button buttonSignUpComplete;
    private ProgressBar progressBarComplete;

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFireStore;
    private String userID;

    public UserDetailInfoRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_detail_info_register, container, false);
        editTextUserName = (EditText) view.findViewById(R.id.editText_userName);
        editTextUserFullName = (EditText) view.findViewById(R.id.editText_fullName);
        editTextUserFullAddress = (EditText) view.findViewById(R.id.editText_fullAddress);
        editTextUserBio = (EditText) view.findViewById(R.id.editText_bio);
        editTextUserTelpNumber = (EditText) view.findViewById(R.id.editText_telpNumber);
        progressBarComplete = (ProgressBar) view.findViewById(R.id.progressBar_onComplete);

        Bundle bundle = getArguments();
        final String emailInputFromFragment = bundle.getString("emailInput");
        final String passwordInputFromFragment = bundle.getString("passwordInput");

        imageViewBackArrow = (ImageView) view
                .findViewById(R.id.imageView_backArrow);
        imageViewBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager mFragmentManager = getActivity()
                        .getSupportFragmentManager();
                mFragmentManager.popBackStack();
            }
        });
        buttonSignUpComplete = (Button) view
                .findViewById(R.id.button_signUpComplete);
        buttonSignUpComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(emailInputFromFragment, passwordInputFromFragment);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mFireStore = FirebaseFirestore.getInstance();

        return view;
    }

    public void registerUser(final String emailInput, String passwordInput) {
        final String userNameInput = editTextUserName.getText().toString().trim();
        final String fullNameInput = editTextUserFullName.getText().toString().trim();
        final String fullAddressInput = editTextUserFullAddress.getText().toString().trim();
        final String bioInput = editTextUserBio.getText().toString().trim();
        final String TelpNumberInput = editTextUserTelpNumber.getText().toString().trim();

        if (userNameInput.isEmpty()) {
            editTextUserName.setError("Username is required");
            editTextUserName.requestFocus();
            return;
        }
        if (fullNameInput.isEmpty()) {
            editTextUserFullName.setError("Your full name is required");
            editTextUserFullName.requestFocus();
            return;
        }
        if (fullAddressInput.isEmpty()) {
            editTextUserFullAddress.setError("Your full address is required");
            editTextUserFullAddress.requestFocus();
            return;
        }
        if (bioInput.isEmpty()) {
            editTextUserBio.setError("You need to tell us about you");
            editTextUserBio.requestFocus();
            return;
        }
        if (TelpNumberInput.isEmpty()) {
            editTextUserTelpNumber.setError("Your phone number is required");
            editTextUserTelpNumber.requestFocus();
            return;
        }

        progressBarComplete.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(emailInput, passwordInput)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBarComplete.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference mDocumentReference = mFireStore
                                    .collection("Users")
                                    .document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("user_email", emailInput);
                            user.put("user_username", userNameInput);
                            user.put("user_full_name", fullNameInput);
                            user.put("user_full_address", fullAddressInput);
                            user.put("user_bio", bioInput);
                            user.put("user_telp_number", TelpNumberInput);
                            mDocumentReference.set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(getContext(),
                                                    "User registration complete",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            if (task.getException() instanceof
                                    FirebaseAuthUserCollisionException) {
                                Toast.makeText(getContext(),
                                        "This email address is already registered",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(),
                                        task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}
