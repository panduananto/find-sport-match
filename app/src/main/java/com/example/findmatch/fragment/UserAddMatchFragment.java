package com.example.findmatch.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.findmatch.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserAddMatchFragment extends Fragment {

    public static final String TAG_ADDMATCH = "ADD_MATCH";
    public static String sportItemName;

    private ImageView imageViewBackArrow;
    private Spinner spinnerSportItem;
    private EditText editTextSportTitle;
    private EditText editTextSportAddressLocation;
    private EditText editTextCurrentPlayer, editTextMaxPlayer;
    private Button buttonAddMatch, buttonResetInput;

    private FirebaseFirestore mFireStore;
    private FirebaseAuth mAuth;
    private String userId;

    public UserAddMatchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_add_match, container, false);

        editTextSportTitle = (EditText) view.findViewById(R.id.editText_sportTitle);
        editTextSportAddressLocation = (EditText) view.findViewById(R.id.editText_sportAddressLocation);
        editTextCurrentPlayer = (EditText) view.findViewById(R.id.editText_currentPlayer);
        editTextMaxPlayer = (EditText) view.findViewById(R.id.editText_maxPlayer);

        imageViewBackArrow = (ImageView) view.findViewById(R.id.imageView_backArrowUserAddMatch);
        imageViewBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentManager.popBackStack();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        mFireStore = FirebaseFirestore.getInstance();
        CollectionReference sportItemRef = mFireStore.collection("SportItem");
        final List<String> sportItemList = new ArrayList<>();
        final ArrayAdapter<String> sportItemAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, sportItemList);
        sportItemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSportItem = (Spinner) view.findViewById(R.id.spinner_sportItem);
        spinnerSportItem.setAdapter(sportItemAdapter);
        sportItemRef.get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String sportItem = document.getString("sportName");
                        sportItemList.add(sportItem);
                    }
                    sportItemAdapter.notifyDataSetChanged();
                }
            }
        });

        spinnerSportItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sportItemName = spinnerSportItem.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "You must select one Sport Tag!", Toast.LENGTH_SHORT).show();
            }
        });

        buttonResetInput = (Button) view.findViewById(R.id.button_resetMatch);
        buttonResetInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSportTitle.setText("");
                editTextSportAddressLocation.setText("");
                editTextCurrentPlayer.setText("");
                editTextMaxPlayer.setText("");
            }
        });

        buttonAddMatch = (Button) view.findViewById(R.id.button_addMatch);
        buttonAddMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextSportTitle.getText().toString().isEmpty() ||
                        editTextSportAddressLocation.getText().toString().isEmpty() ||
                        editTextCurrentPlayer.getText().toString().isEmpty() ||
                        editTextMaxPlayer.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "You cannot have empty fields!", Toast.LENGTH_SHORT).show();
                    return;
                }

                DocumentReference userMatchRef = mFireStore.collection("MatchUser").document();
                Map<String, Object> newMatchData = new HashMap<>();
                newMatchData.put("sportTag", sportItemName);
                newMatchData.put("sportTitle", editTextSportTitle.getText().toString());
                newMatchData.put("sportAddressLocation", editTextSportAddressLocation.getText().toString());
                newMatchData.put("statusPlay", "MENUNGGU LAWAN");
                newMatchData.put("statusMatch", "play");
                newMatchData.put("currentPlayer", editTextCurrentPlayer.getText().toString());
                newMatchData.put("maxPlayer", editTextMaxPlayer.getText().toString());
                newMatchData.put("userId", userId);
                userMatchRef.set(newMatchData).addOnSuccessListener(
                        new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Match is successfully added!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Failed adding new match!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }
}
