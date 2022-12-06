package edu.byu.spectrej.geoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CheatActivity extends AppCompatActivity {
  private static final String INTENT_KEY = "com.bignerdranch.android.geoquiz.answer_is_true";
  public static final String EXTRA_ANSWER_IS_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";
  private Boolean answerIsTrue;
  private TextView answerTextView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cheat);

    this.answerIsTrue = getIntent().getBooleanExtra(INTENT_KEY, false);
    this.answerTextView = findViewById(R.id.answer_text_view);
    Button showAnswerButton = findViewById(R.id.show_answer_button);
    showAnswerButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        int answerText = (answerIsTrue ? R.string.true_button : R.string.false_button);
        answerTextView.setText(answerText);
        setAnswerShownResult(true);
      }
    });
  }

  private void setAnswerShownResult(Boolean isAnswerShown) {
    Intent data = new Intent();
    data.putExtra(EXTRA_ANSWER_IS_SHOWN, isAnswerShown);
    setResult(Activity.RESULT_OK, data);
  }

  public static Intent newIntent(Context packageContext, Boolean answerIsTrue) {
    Intent intent = new Intent(packageContext, CheatActivity.class);
    intent.putExtra(INTENT_KEY, answerIsTrue);
    return intent;
  }
}