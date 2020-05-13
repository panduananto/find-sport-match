package com.example.findmatch.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.findmatch.model.SportItemModel;
import com.example.findmatch.repository.FirebaseRepository;

import java.util.List;

public class SportItemViewModel extends ViewModel implements FirebaseRepository.OnFireStoreTaskComplete {

    private MutableLiveData<List<SportItemModel>> sportItemModelData = new MutableLiveData<>();

    public LiveData<List<SportItemModel>> getSportItemModelData() {
        return sportItemModelData;
    }

    private FirebaseRepository mFirebaseRepository = new FirebaseRepository(this);

    public SportItemViewModel() {
        mFirebaseRepository.getSportItem();
    }

    @Override
    public void sportItemDataAdded(List<SportItemModel> sportItemModelList) {
        sportItemModelData.setValue(sportItemModelList);
    }

    @Override
    public void onError(Exception e) {

    }
}
