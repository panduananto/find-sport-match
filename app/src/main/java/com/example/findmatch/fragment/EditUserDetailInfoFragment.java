package com.example.findmatch.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.findmatch.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditUserDetailInfoFragment extends Fragment {

    private ImageView imageViewBackArrow;
    private EditText editTextUserName, editTextUserFullName;
    private EditText editTextUserFullAddress, editTextUserBio, editTextUserTelpNumber;
    private Button buttonSignUpComplete, buttonResetInput;
    private ProgressBar progressBarComplete;

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFireStore;
    private String userID;

    public EditUserDetailInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_user_detail_info, container, false);
        view.bringToFront();
        editTextUserName = (EditText) view.findViewById(R.id.editText_userNameOnEdit);
        editTextUserFullName = (EditText) view.findViewById(R.id.editText_fullNameOnEdit);
        editTextUserFullAddress = (EditText) view.findViewById(R.id.editText_fullAddressOnEdit);
        editTextUserBio = (EditText) view.findViewById(R.id.editText_bioOnEdit);
        editTextUserTelpNumber = (EditText) view.findViewById(R.id.editText_telpNumberOnEdit);
        progressBarComplete = (ProgressBar) view.findViewById(R.id.progressBar_onCompleteEdit);

        Bundle bundle = getArguments();
        if (bundle != null) {
            final String userNameOld = bundle.getString("userNameOld");
            final String fullNameOld = bundle.getString("fullNameOld");
            final String fullAddressOld = bundle.getString("fullAddressOld");
            final String bioOld = bundle.getString("bioOld");
            final String telpNumberOld = bundle.getString("telpNumberOld");

            editTextUserName.setText(userNameOld);
            editTextUserFullName.setText(fullNameOld);
            editTextUserFullAddress.setText(fullAddressOld);
            editTextUserBio.setText(bioOld);
            editTextUserTelpNumber.setText(telpNumberOld);
        }

        imageViewBackArrow = (ImageView) view.findViewById(R.id.imageView_backArrowOnEditProfile);
        imageViewBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentManager.popBackStack();
            }
        });

        buttonResetInput =(Button) view.findViewById(R.id.button_resetInputEdit);
        buttonResetInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextUserName.setText("");
                editTextUserFullName.setText("");
                editTextUserFullAddress.setText("");
                editTextUserBio.setText("");
                editTextUserTelpNumber.setText("");
            }
        });

        return view;
    }
}
