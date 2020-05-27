package com.example.findmatch.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.findmatch.model.SportItemModel;
import com.example.findmatch.model.UserMatchModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class UserMatchRepository {

    private static final String TAG_USER_MATCH = "TAG_USER_MATCH";

    private OnFireStoreTaskComplete onFireStoreTaskComplete;

    private FirebaseFirestore mFirebaseRepository = FirebaseFirestore.getInstance();
    private CollectionReference userMatchRef = mFirebaseRepository.collection("MatchUser");
    private Query userMatchQuery = userMatchRef.whereEqualTo("statusMatch", "play");

    public UserMatchRepository(OnFireStoreTaskComplete onFireStoreTaskComplete) {
        this.onFireStoreTaskComplete = onFireStoreTaskComplete;
    }

    public void getUserMatch() {
        userMatchQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    Log.w(TAG_USER_MATCH, "Listen failed.", e);
                    return;
                }

                List<UserMatchModel> userMatchList = new ArrayList<>();
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    if (document.exists()) {
                        userMatchList.add(document.toObject(UserMatchModel.class));
                    }
                }
                onFireStoreTaskComplete.userMatchDataAdded(userMatchList);
            }
        });
    }

    public interface OnFireStoreTaskComplete {
        void userMatchDataAdded(List<UserMatchModel> userMatchModelList);
        void onError(Exception e);
    }
}
