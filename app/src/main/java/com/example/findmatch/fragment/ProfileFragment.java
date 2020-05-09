package com.example.findmatch.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.findmatch.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView textViewUserName, textViewBio, textViewFullName;
    private TextView textViewEmail, textViewFullAddress, textViewTelpNumber;

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFireStore;
    String userId;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        textViewUserName = (TextView) view.findViewById(R.id.textView_usernameOnProfile);
        textViewBio = (TextView) view.findViewById(R.id.textView_profileDescription);
        textViewFullName = (TextView) view.findViewById(R.id.textView_placeholderName);
        textViewEmail = (TextView) view.findViewById(R.id.textView_placeholderEmailAddress);
        textViewFullAddress = (TextView) view.findViewById(R.id.textView_placeholderAddress);
        textViewTelpNumber = (TextView) view.findViewById(R.id.textView_placeholderTelpNumber);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        mFireStore = FirebaseFirestore.getInstance();

        DocumentReference mDocumentReference = mFireStore.collection("Users").document(userId);
        mDocumentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                textViewUserName.setText(documentSnapshot.getString("user_username"));
                textViewBio.setText(documentSnapshot.getString("user_bio"));
                textViewFullName.setText(documentSnapshot.getString("user_full_name"));
                textViewEmail.setText(documentSnapshot.getString("user_email"));
                textViewFullAddress.setText(documentSnapshot.getString("user_full_address"));
                textViewTelpNumber.setText(documentSnapshot.getString("user_telp_number"));
            }
        });

        return view;
    }
}
