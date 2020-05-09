package com.example.findmatch.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    private ImageView userProfilePicture;

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
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot,
                                @Nullable FirebaseFirestoreException e) {
                textViewUserName.setText(documentSnapshot.getString("user_username"));
                textViewBio.setText(documentSnapshot.getString("user_bio"));
                textViewFullName.setText(documentSnapshot.getString("user_full_name"));
                textViewEmail.setText(documentSnapshot.getString("user_email"));
                textViewFullAddress.setText(documentSnapshot.getString("user_full_address"));
                textViewTelpNumber.setText(documentSnapshot.getString("user_telp_number"));
            }
        });

        userProfilePicture = (ImageView) view.findViewById(R.id.imageView_photoProfile);
        userProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                userProfilePicture.setImageURI(imageUri);
            }
        }
    }
}
