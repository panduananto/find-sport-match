package com.example.findmatch.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.findmatch.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DialogJoinMatchFragment extends DialogFragment {

    private static final String TAG_DIALOGJOINMATCH = "TAG_DIALOGJOINMATCH";

    public String sportMatchIdArg;
    public Long sportCurrentPlayerArg, sportMaxPlayerArg;

    private EditText editTextInputTotalPlayer;
    private TextView textViewCancelJoin, textViewAcceptJoin;

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFireStore;
    private FirebaseUser currentUser;
    private String userID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_join_match, container, false);

        editTextInputTotalPlayer = (EditText) view.findViewById(R.id.editText_inputTotalPlayer);

        Bundle bundle = getArguments();
        if (bundle != null) {
            sportMatchIdArg = bundle.getString("sportMatchIdString");
            sportCurrentPlayerArg = bundle.getLong("sportCurrentPlayerLong");
            sportMaxPlayerArg = bundle.getLong("sportMaxPlayerLong");
        }

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        userID = currentUser.getUid();
        mFireStore = FirebaseFirestore.getInstance();

        textViewCancelJoin = (TextView) view.findViewById(R.id.textView_buttonCancelJoin);
        textViewCancelJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        textViewAcceptJoin = (TextView) view.findViewById(R.id.textView_buttonAcceptJoin);
        textViewAcceptJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userJoinMatch();
            }
        });

        return view;
    }

    public void userJoinMatch() {
        final DocumentReference matchUserRef = mFireStore.collection("MatchUser").document(sportMatchIdArg);
        Long totalPlayer = sportCurrentPlayerArg + Integer.valueOf(editTextInputTotalPlayer.getText().toString());

        if (totalPlayer > sportMaxPlayerArg) {
            Toast.makeText(getContext(),
                    "You entered too many player!", Toast.LENGTH_SHORT).show();
        } else {
            matchUserRef
                    .update("currentPlayer",
                            FieldValue.increment(
                                    Integer.valueOf(editTextInputTotalPlayer.getText().toString())))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Map<String, Object> userJoinData = new HashMap<>();
                            userJoinData.put("userJoin", Arrays.asList(userID));
                            matchUserRef.set(userJoinData, SetOptions.merge()).addOnSuccessListener(
                                    new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(getContext(), "Success joining match!",
                                                    Toast.LENGTH_SHORT).show();
                                            getDialog().dismiss();
                                        }
                                    });
                        }
                    });
        }
    }
}
