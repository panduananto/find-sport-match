package com.example.findmatch.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.findmatch.model.UserMatchModel;
import com.example.findmatch.model.SportItemModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

public class SportItemRepository {

    private static final String TAG_SPORT_ITEM = "TAG_SPORT_ITEM";

    private OnFireStoreTaskComplete onFireStoreTaskComplete;

    private FirebaseFirestore mFirebaseRepository = FirebaseFirestore.getInstance();
    private CollectionReference sportItemRef = mFirebaseRepository.collection("SportItem");

    public SportItemRepository(OnFireStoreTaskComplete onFireStoreTaskComplete) {
        this.onFireStoreTaskComplete = onFireStoreTaskComplete;
    }

    public void getSportItem() {
        sportItemRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    return;
                }

                List<SportItemModel> sportListUpdate = new ArrayList<>();
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    if (document.exists()) {
                        sportListUpdate.add(document.toObject(SportItemModel.class));
                    }
                }
                onFireStoreTaskComplete.sportItemDataAdded(sportListUpdate);
            }
        });
    }

    public interface OnFireStoreTaskComplete {
        void sportItemDataAdded(List<SportItemModel> sportItemModelList);
        void onError(Exception e);
    }
}
