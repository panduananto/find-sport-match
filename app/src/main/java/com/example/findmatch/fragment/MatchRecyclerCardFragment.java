package com.example.findmatch.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findmatch.R;
import com.example.findmatch.adapter.UserMatchAdapter;
import com.example.findmatch.model.UserMatchModel;
import com.example.findmatch.viewmodel.SportItemViewModel;
import com.example.findmatch.viewmodel.UserMatchViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchRecyclerCardFragment extends Fragment
        implements UserMatchAdapter.OnButtonJoinClick {

    private static final String TAG_MATCHRECYCLER = "TAG_MATCHRECYCLER";

    private UserMatchViewModel mUserMatchViewModel;
    private UserMatchAdapter mUserMatchAdapter;
    private RecyclerView recyclerViewMatch;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private String userID;

    public MatchRecyclerCardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_match_recycler_card, container, false);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        userID = currentUser.getUid();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewMatch = (RecyclerView)view.findViewById(R.id.recyclerViewMatch);
        mUserMatchAdapter = new UserMatchAdapter(this);

        recyclerViewMatch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMatch.setAdapter(mUserMatchAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mUserMatchViewModel = new ViewModelProvider(getActivity()).get(UserMatchViewModel.class);
        mUserMatchViewModel.getUserMatchModelData().observe(getViewLifecycleOwner(), new Observer<List<UserMatchModel>>() {
            @Override
            public void onChanged(List<UserMatchModel> userMatchModelList) {
                mUserMatchAdapter.setmUserMatchModel(userMatchModelList);
                mUserMatchAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClicked(int position) {
        DialogJoinMatchFragment mDialogJoinMatch = new DialogJoinMatchFragment();

        TextView sportUserId = recyclerViewMatch
                .findViewHolderForAdapterPosition(position)
                .itemView.findViewById(R.id.textView_userId);
        TextView sportCurrentPlayer = recyclerViewMatch
                .findViewHolderForAdapterPosition(position)
                .itemView.findViewById(R.id.textView_currentPlayerOther);
        TextView sportMaxPlayer = recyclerViewMatch
                .findViewHolderForAdapterPosition(position)
                .itemView.findViewById(R.id.textView_maxPlayerOther);
        TextView sportMatchId = recyclerViewMatch
                .findViewHolderForAdapterPosition(position)
                .itemView.findViewById(R.id.textView_sportMatchId);

        String sportUserIdString = sportUserId.getText().toString();
        String sportMatchIdString = sportMatchId.getText().toString();
        Long sportCurrentPlayerLong = Long.valueOf(sportCurrentPlayer.getText().toString());
        Long sportMaxPlayerLong = Long.valueOf(sportMaxPlayer.getText().toString());

        if (sportUserIdString.equals(userID)) {
            Toast.makeText(getContext(), "You cannot join your own match!",
                    Toast.LENGTH_SHORT).show();
        } else if (sportCurrentPlayerLong.equals(sportMaxPlayerLong)) {
            Toast.makeText(getContext(), "This match is full!",
                    Toast.LENGTH_SHORT).show();
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("sportMatchIdString", sportMatchIdString);
            bundle.putLong("sportCurrentPlayerLong", sportCurrentPlayerLong);
            bundle.putLong("sportMaxPlayerLong", sportMaxPlayerLong);

            mDialogJoinMatch.setArguments(bundle);
            mDialogJoinMatch.setTargetFragment(MatchRecyclerCardFragment.this, 1);
            mDialogJoinMatch.show(getFragmentManager(), "DialogJoinMatchFragment");
        }
    }
}
