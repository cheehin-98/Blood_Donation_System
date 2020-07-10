package com.example.blooddonationsystem.ui.donor_find_blood_drive;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FindBloodDriveViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FindBloodDriveViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Find Blood Drive fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

