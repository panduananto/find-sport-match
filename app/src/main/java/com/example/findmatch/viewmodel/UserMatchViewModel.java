package com.example.findmatch.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.findmatch.model.UserMatchModel;
import com.example.findmatch.repository.UserMatchRepository;

import java.util.List;

public class UserMatchViewModel extends ViewModel
        implements UserMatchRepository.OnFireStoreTaskComplete {

    private MutableLiveData<List<UserMatchModel>>
            userMatchModelData = new MutableLiveData<>();

    public LiveData<List<UserMatchModel>> getUserMatchModelData() {
        return userMatchModelData;
    }

    private UserMatchRepository mUserMatchRepository = new UserMatchRepository(this);

    public UserMatchViewModel() {
        mUserMatchRepository.getUserMatch();
    }

    @Override
    public void userMatchDataAdded(List<UserMatchModel> userMatchModelList) {
        userMatchModelData.setValue(userMatchModelList);
    }

    @Override
    public void onError(Exception e) {

    }
}
