package com.example.android.navigationexcercise.ui.unittransfer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UnitTransferViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UnitTransferViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is unit transfer fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}