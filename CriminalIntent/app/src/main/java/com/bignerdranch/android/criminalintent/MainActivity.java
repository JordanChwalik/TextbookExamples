package com.bignerdranch.android.criminalintent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Fragment currentFragment = getSupportFragmentManager().findFragmentById(
        R.id.fragment_container);

    if (currentFragment == null) {
      Fragment fragment = new CrimeFragment();
      getSupportFragmentManager()
          .beginTransaction()
          .add(R.id.fragment_container, fragment)
          .commit();
    }

  }
}