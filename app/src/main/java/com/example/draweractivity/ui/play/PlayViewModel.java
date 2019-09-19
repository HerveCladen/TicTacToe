package com.example.draweractivity.ui.play;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlayViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PlayViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Tic Tac Toe");
    }

    public LiveData<String> getText() {
        return mText;
    }
}