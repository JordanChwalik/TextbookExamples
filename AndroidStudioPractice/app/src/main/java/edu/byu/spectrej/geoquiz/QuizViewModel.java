package edu.byu.spectrej.geoquiz;


import android.util.Log;

import android.widget.Toast;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class QuizViewModel extends ViewModel {
    private List<Question> questionBank = new ArrayList<>();
    public int currentIndex;
    private int score;
    private boolean isCheater = false;
    private final String TAG = "QuizModelView";

    public QuizViewModel() {
        populateQuestionBank();
        currentIndex = 0;
        score = 0;
    }

    private void populateQuestionBank() {
        questionBank.add(new Question(R.string.question_australia, true));
        questionBank.add(new Question(R.string.question_oceans, true));
        questionBank.add(new Question(R.string.question_mideast, false));
        questionBank.add(new Question(R.string.question_africa, false));
        questionBank.add(new Question(R.string.question_americas, true));
        questionBank.add(new Question(R.string.question_asia, true));
    }

    public boolean allAnswered() {
        for (Question question : questionBank) {
            if (!question.getUsed()) {
                return false;
            }
        }
        return true;
    }

    public void moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size();
        resetIsCheater();
    }

    public void moveToPrevious() {
        if (currentIndex == 0) {
            currentIndex = questionBank.size() - 1;
        } else {
            currentIndex -= 1;
        }
        resetIsCheater();
    }
    private void resetIsCheater() {
        isCheater = false;
    }

    public int getSize() {
        return questionBank.size();
    }

    public boolean checkAnswer(Boolean response) {
        if (response == questionBank.get(currentIndex).getAnswer()) {
            questionBank.get(currentIndex).setUsed(true);
            score += 1;
            return true;
        } else {
            questionBank.get(currentIndex).setUsed(true);
            return false;
        }
    }

    public Question getQuestion() {
        return questionBank.get(currentIndex);
    }

    public int getScore() {
        return score;
    }

    public boolean isCheater() {
        return isCheater;
    }

    public void setCheater(boolean cheater) {
        isCheater = cheater;
    }
}
