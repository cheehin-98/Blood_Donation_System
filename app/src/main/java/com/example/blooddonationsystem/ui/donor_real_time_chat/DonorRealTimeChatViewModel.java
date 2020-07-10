package com.example.blooddonationsystem.ui.donor_real_time_chat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class DonorRealTimeChatViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public DonorRealTimeChatViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is RealTimeChat fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

