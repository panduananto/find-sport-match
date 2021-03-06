package com.example.findmatch.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findmatch.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchOwnerFragment extends Fragment {

    private String TAG_MATCHUSER = "MATCHUSER_TAG";
    private String documentId;
    private String statusPlay;

    private TextView textViewSportTagOwner, textViewSportTitleOwner;
    private TextView textViewSportAddressLocationOwner, textViewStatusPlayOwner;
    private TextView textViewCurrentPlayerOwner, textViewMaxPlayerOwner;
    private Button buttonFinishMatch;

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFireStore;
    private String userId;

    public MatchOwnerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_match_owner, container, false);
        textViewSportTagOwner = (TextView) view.findViewById(R.id.textView_sportTagOther);
        textViewSportTitleOwner = (TextView) view.findViewById(R.id.textView_sportTitleOther);
        textViewSportAddressLocationOwner = (TextView) view.findViewById(R.id.textView_sportAddressLocationOther);
        textViewStatusPlayOwner = (TextView) view.findViewById(R.id.textView_statusPlayOther);
        textViewCurrentPlayerOwner = (TextView) view.findViewById(R.id.textView_currentPlayerOther);
        textViewMaxPlayerOwner = (TextView) view.findViewById(R.id.textView_maxPlayerOther);

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

                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    if (document.exists()) {
                        textViewSportTagOwner.setText(document.getString("sportTag"));
                        textViewSportTitleOwner.setText(document.getString("sportTitle"));
                        textViewSportAddressLocationOwner.setText(document.getString("sportAddressLocation"));
                        textViewStatusPlayOwner.setText(document.getString("statusPlay"));
                        textViewCurrentPlayerOwner.setText(document.getLong("currentPlayer").toString());
                        textViewMaxPlayerOwner.setText(document.getLong("maxPlayer").toString());
                    }
                    documentId = document.getId();
                    statusPlay = document.getString("statusPlay");
                    if (statusPlay.equalsIgnoreCase("SEDANG MAIN")) {
                        textViewStatusPlayOwner.setTextColor(Color.parseColor("#48bb78"));
                    } else {
                        textViewStatusPlayOwner.setTextColor(Color.parseColor("#4299E1"));
                    }
                }
            }
        });

        buttonFinishMatch = (Button) view.findViewById(R.id.button_finishMatch);
        buttonFinishMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference mDocumentReference = mFireStore.collection("MatchUser").document(documentId);
                Map<String, Object> updateStatusMatch = new HashMap<>();
                updateStatusMatch.put("statusMatch", "finish");
                mDocumentReference.update(updateStatusMatch).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Match finished!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Failed updating data!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        textViewStatusPlayOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference mDocumentReference = mFireStore.collection("MatchUser").document(documentId);
                Map<String, Object> updateStatusPlay = new HashMap<>();
                updateStatusPlay.put("statusPlay", "SEDANG MAIN");
                mDocumentReference.update(updateStatusPlay).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Status play updated to SEDANG MAIN", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Failed updating data!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }
}
