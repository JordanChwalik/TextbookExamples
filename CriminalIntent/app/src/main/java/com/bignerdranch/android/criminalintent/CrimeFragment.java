package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CrimeFragment extends Fragment {
  private Crime crime = new Crime();
  private EditText titleField;
  private Button dateButton;
  private CheckBox solvedCheckBox;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_crime, container, false);
    titleField = view.findViewById(R.id.crime_title);

    //Setup dateButton
    dateButton = view.findViewById(R.id.crime_date);
    dateButton.setText(crime.getDate().toString());
    dateButton.setEnabled(false);

    //Setup checkbox
    solvedCheckBox = view.findViewById(R.id.crime_solved);

    return view;
  }

  @Override
  public void onStart() {
    super.onStart();

    //Create a TextWatcher for the titleField
    TextWatcher titleWatcher = new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        crime.setTitle(s.toString());
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    };

    titleField.addTextChangedListener(titleWatcher);

    solvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (solvedCheckBox.isChecked()) {
          crime.setSolved(true);
        } else {
          crime.setSolved(false);
        }
      }
    });
  }
}
