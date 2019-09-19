package com.example.draweractivity.ui.help;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HelpViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HelpViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("The player who is playing \"X\" always goes first. Players alternate placing Xs and Os on the board until either one player has three in a row, horizontally, vertically, or diagonally; or all nine squares are filled. If a player is able to draw three of their Xs or three of their Os in a row, then that player wins.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}