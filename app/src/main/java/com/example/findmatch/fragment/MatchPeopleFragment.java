package com.example.findmatch.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.findmatch.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchPeopleFragment extends Fragment {

    private TextView textViewAddGame;

    private FrameLayout noMatchViewContainer;

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFireStore;
    private String userId;

    public MatchPeopleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_match_people, container, false);
        noMatchViewContainer = (FrameLayout) view.findViewById(R.id.container_youNoGame);
        MatchRecyclerCardFragment mMatchRecyclerCardFragment = new MatchRecyclerCardFragment();
        FragmentManager mFragmentManager = getChildFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

        mFragmentTransaction.add(R.id.container_fragmentMatchPeople,
                mMatchRecyclerCardFragment, MatchRecyclerCardFragment.class.getSimpleName());

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        mFireStore = FirebaseFirestore.getInstance();
        CollectionReference matchUserReference = mFireStore.collection("MatchUser");
        Query matchUserQuery = matchUserReference
                .whereEqualTo("userId", userId)
                .whereEqualTo("statusMatch", "play");
        matchUserQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    return;
                }

                if (queryDocumentSnapshots.isEmpty()) {
                    final View noMatchView = inflater.inflate(R.layout.user_no_match_view, null);
                    noMatchViewContainer.addView(noMatchView);
                } else {
                    MatchOwnerFragment mMatchOwner = new MatchOwnerFragment();
                    FragmentManager mMatchOwnerManager = getChildFragmentManager();
                    FragmentTransaction mMatchOwnerTransaction = mMatchOwnerManager.beginTransaction();
                    mMatchOwnerTransaction.add(R.id.container_youNoGame,
                            mMatchOwner,
                            MatchOwnerFragment.class.getSimpleName());
                    mMatchOwnerTransaction.commit();
                }
            }
        });

        mFragmentTransaction.commit();

        textViewAddGame = (TextView) view.findViewById(R.id.textView_addGame);
        textViewAddGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAddMatchFragment mUserAddMatchFragment = new UserAddMatchFragment();
                FragmentManager mUserAddMatchManager = getFragmentManager();
                FragmentTransaction mUserAddMatchTransaction = mUserAddMatchManager.beginTransaction();

                mUserAddMatchTransaction.add(R.id.screenHome,
                        mUserAddMatchFragment,
                        UserAddMatchFragment.class.getSimpleName());
                mUserAddMatchTransaction.addToBackStack(null);
                mUserAddMatchTransaction.commit();
            }
        });

        return view;
    }
}
