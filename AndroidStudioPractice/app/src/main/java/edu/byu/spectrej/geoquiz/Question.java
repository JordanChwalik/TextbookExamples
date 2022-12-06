package edu.byu.spectrej.geoquiz;

import androidx.annotation.StringRes;

public class Question {
    private int textResID;
    private boolean answer;
    private boolean used;

    public Question(@StringRes int textResID, Boolean answer) {
        this.textResID = textResID;
        this.answer = answer;
        this.used = false;
    }

    public int getTextResID() {
        return textResID;
    }

    public void setTextResID(int textResID) {
        this.textResID = textResID;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public boolean getUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
