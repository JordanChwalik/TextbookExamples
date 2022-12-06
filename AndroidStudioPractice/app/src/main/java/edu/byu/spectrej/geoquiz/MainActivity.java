package edu.byu.spectrej.geoquiz;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.view.View.OnClickListener;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
  private final int REQUEST_CODE_CHEAT = 0;
  private final String TAG = "MainActivity";
  private final String KEY_INDEX = "index";

  private TextView questionTextView;
  private TextView scoreTextView;

  private QuizViewModel getQuizViewModel() {
    return ViewModelProviders.of(this).get(QuizViewModel.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d(TAG, "onCreate(Bundle) called");
    setContentView(R.layout.activity_main);

    if (savedInstanceState != null) {
      getQuizViewModel().currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
    }

    Button trueButton = findViewById(R.id.true_button);
    Button falseButton = findViewById(R.id.false_button);
    Button cheatButton = findViewById(R.id.cheat_button);
    ImageButton nextButton = findViewById(R.id.next_button);
    ImageButton previousButton = findViewById(R.id.previous_button);
    questionTextView = findViewById(R.id.question_text_view);
    scoreTextView = findViewById(R.id.score_text_view);

    trueButton.setOnClickListener(view -> checkAnswer(true));

    falseButton.setOnClickListener(view -> checkAnswer(false));

    nextButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getQuizViewModel().moveToNext();
        updateQuestion();
      }
    });

    previousButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getQuizViewModel().moveToPrevious();
        updateQuestion();
      }
    });
    cheatButton.setOnClickListener(v -> {
      //Start cheat activity
      Intent intent = CheatActivity.newIntent(MainActivity.this, getQuizViewModel().getQuestion().getAnswer());
      ActivityOptions options = null;
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        options = ActivityOptions.makeClipRevealAnimation(v, 0, 0, v.getWidth(), v.getHeight());
      }
      startActivityForResult(intent, REQUEST_CODE_CHEAT, options.toBundle());
    });

    questionTextView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getQuizViewModel().moveToNext();
        updateQuestion();
      }
    });

    updateQuestion();
  }

  @Override
  protected void onStart() {
    super.onStart();
    Log.d(TAG, "onStart() called");
  }

  @Override
  protected void onStop() {
    super.onStop();
    Log.d(TAG, "onStop() called");
  }

  @Override
  protected void onPause() {
    super.onPause();
    Log.d(TAG, "onPause() called");
  }

  @Override
  protected void onResume() {
    super.onResume();
    Log.d(TAG, "onResume() called");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "onDestroy() called");
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    Log.i(TAG, "onSaveInstanceState");
    outState.putInt(KEY_INDEX, getQuizViewModel().currentIndex);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode != Activity.RESULT_OK) {
      return;
    }

    if (requestCode == REQUEST_CODE_CHEAT) {
      boolean isCheater = false;
      if (data != null && data.hasExtra(CheatActivity.EXTRA_ANSWER_IS_SHOWN)) {
        isCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_IS_SHOWN, false);
      }
      getQuizViewModel().setCheater(isCheater);
    }
  }

  private void updateQuestion() {
    questionTextView.setText(getQuizViewModel().getQuestion().getTextResID());
  }

  private void displayScore() {
    String scoreString =
        "Score: " + getQuizViewModel().getScore() + "/" + getQuizViewModel().getSize();
    float scorePercent = (float) getQuizViewModel().getScore() / getQuizViewModel().getSize();
    scorePercent *= 100;
    scoreString = scoreString + "\n" + scorePercent + "%";
    scoreTextView.setText(scoreString);
  }

  private void checkAnswer(Boolean response) {
    if (!getQuizViewModel().getQuestion().getUsed()) {
      if (getQuizViewModel().isCheater()) {
        Toast.makeText(this, R.string.judgement_toast, Toast.LENGTH_SHORT).show();
        return;
      }
      if (getQuizViewModel().checkAnswer(response)) {
        Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
      }
    } else {
      Toast.makeText(this, R.string.already_answered_toast, Toast.LENGTH_SHORT).show();
    }
    if (getQuizViewModel().allAnswered()) {
      displayScore();
    }
  }

}