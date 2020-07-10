package com.example.blooddonationsystem.ui.donor_notification;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DonorNotificationViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public DonorNotificationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Notification fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}



