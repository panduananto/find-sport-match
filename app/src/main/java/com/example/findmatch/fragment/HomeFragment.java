package com.example.findmatch.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.findmatch.R;

import com.example.findmatch.activity.MainActivity;
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
public class HomeFragment extends Fragment implements View.OnClickListener {

    private TextView usernamePlaceholder;
    private TextView textViewLogout;

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFireStore;
    String userId;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        usernamePlaceholder = (TextView) view
                .findViewById(R.id.textView_usernamePlaceholder);
        textViewLogout = (TextView) view.findViewById(R.id.textView_logout);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        mFireStore = FirebaseFirestore.getInstance();

        DocumentReference mDocumentReference = mFireStore.collection("Users").document(userId);
        mDocumentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot,
                                @Nullable FirebaseFirestoreException e) {
                usernamePlaceholder.setText(documentSnapshot
                        .getString("user_username"));
            }
        });

        textViewLogout.setOnClickListener(this);

        RecyclerCardFragment mRecyclerCardFragment = new RecyclerCardFragment();
        FragmentManager mFragmentManager = getChildFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction
                .add(R.id.container_recyclerViewOnFragmentHome,
                        mRecyclerCardFragment,
                        RecyclerCardFragment.class.getSimpleName());

        mFragmentTransaction.commit();

        return view;
    }

    public void userLogout() {
        mAuth.signOut();
        Intent activityIntent = new Intent(getActivity(), MainActivity.class);
        startActivity(activityIntent);
        getActivity().finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.textView_logout) {
            userLogout();
        }
    }
}
