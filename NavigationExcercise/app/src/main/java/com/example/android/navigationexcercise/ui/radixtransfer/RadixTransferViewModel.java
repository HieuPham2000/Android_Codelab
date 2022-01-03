package com.example.android.navigationexcercise.ui.radixtransfer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RadixTransferViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RadixTransferViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}