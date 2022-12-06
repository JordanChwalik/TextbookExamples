package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.List;

public class CrimeListFragment extends Fragment {
  private static final String TAG = "CrimesListFragment";

  public CrimesListViewModel crimesListViewModel = new CrimesListViewModel();
  private RecyclerView crimeRecyclerView;
  private CrimeAdapter adapter = null;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d(TAG, "Total Crimes: " + crimesListViewModel.getSize());
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
    crimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
    crimeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    updateUI();
    return view;
  }

  private void updateUI() {
    List<Crime> crimes = crimesListViewModel.crimes;
    adapter = new CrimeAdapter(crimes);
    crimeRecyclerView.setAdapter(adapter);

  }

  private class CrimeHolder extends ViewHolder implements
      OnClickListener {
    public CrimeHolder(@NonNull View itemView) {
      super(itemView);
      itemView.setOnClickListener(this);
    }

    private TextView titleTextView = itemView.findViewById(R.id.crime_title);
    private TextView dateTextView = itemView.findViewById(R.id.crime_date);
    private ImageView solvedImageView=itemView.findViewById(R.id.crime_solved);

    private Crime crime;

    public void bind(Crime crime) {
      this.crime = crime;
      titleTextView.setText(this.crime.getTitle());
      dateTextView.setText(this.crime.getDate().toString());
      if (crime.isSolved()) {
        solvedImageView.setVisibility(View.VISIBLE);
      } else {
        solvedImageView.setVisibility(View.GONE);
      }
    }


    @Override
    public void onClick(View v) {
      Toast.makeText(itemView.getContext(), crime.getTitle() + " pressed!", Toast.LENGTH_SHORT).show();
    }
  }

  private class CrimeAdapter extends RecyclerView.Adapter {
    List<Crime> crimeList;

    public CrimeAdapter(List<Crime> crimeList) {
      this.crimeList = crimeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = getLayoutInflater().inflate(R.layout.list_item_crime, parent, false);
      return new CrimeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      Crime crime = crimeList.get(position);
      CrimeHolder holder1 = (CrimeHolder) holder;
      holder1.bind(crime);
    }

    @Override
    public int getItemCount() {
      return crimeList.size();
    }
  }
}
