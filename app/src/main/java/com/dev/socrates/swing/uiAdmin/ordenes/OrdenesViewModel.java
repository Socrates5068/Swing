package com.dev.socrates.swing.uiAdmin.ordenes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrdenesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OrdenesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ordenes fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}