package com.bignerdranch.android.criminalintent;

import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class CrimesListViewModel extends ViewModel {
  List<Crime> crimes = new ArrayList<>();

  public CrimesListViewModel() {
    for (int i = 0; i < 100; ++i) {
      Crime crime = new Crime();
      crime.setTitle("Crime #" + i);
      crime.setSolved(i % 2 == 0);
      crimes.add(crime);
    }
  }

  public int getSize() {
    return crimes.size();
  }
}
