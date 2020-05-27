package com.example.findmatch.repository;

import androidx.annotation.NonNull;

import com.example.findmatch.model.MatchItemModel;
import com.example.findmatch.model.SportItemModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class FirebaseRepository {

    private OnFireStoreTaskComplete onFireStoreTaskComplete;

    private FirebaseFirestore mFirebaseRepository = FirebaseFirestore.getInstance();
    private CollectionReference sportItemRef = mFirebaseRepository.collection("SportItem");
    private CollectionReference matchItemRef = mFirebaseRepository.collection("MatchUser");
    private Query matchItemQuery = matchItemRef.whereEqualTo("statusMatch", "play");

    public FirebaseRepository(OnFireStoreTaskComplete onFireStoreTaskComplete) {
        this.onFireStoreTaskComplete = onFireStoreTaskComplete;
    }

    public void getSportItem() {
        sportItemRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    onFireStoreTaskComplete.sportItemDataAdded(task.getResult().toObjects(SportItemModel.class));
                } else {
                    onFireStoreTaskComplete.onError(task.getException());
                }
            }
        });
    }

    public void getMatchItem() {
        matchItemQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    onFireStoreTaskComplete.matchItemDataAdded(task.getResult().toObjects(MatchItemModel.class));
                } else {
                    onFireStoreTaskComplete.onError(task.getException());
                }
            }
        });
    }

    public interface OnFireStoreTaskComplete {
        void sportItemDataAdded(List<SportItemModel> sportItemModelList);
        void matchItemDataAdded(List<MatchItemModel> matchItemModelList);
        void onError(Exception e);
    }
}
