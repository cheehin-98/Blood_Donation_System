package com.example.blooddonationsystem.ui.donor_find_blood_drive;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.blooddonationsystem.R;

public class FindBloodDriveFragment extends Fragment {

    private FindBloodDriveViewModel findBloodDriveViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        findBloodDriveViewModel =
               ViewModelProviders.of(this).get(FindBloodDriveViewModel.class);
        View root = inflater.inflate(R.layout.fragment_donor_find_blood_drive, container, false);
         final TextView textView = root.findViewById(R.id.text_hello);
        findBloodDriveViewModel.getText().observe(this, new Observer<String>() {
          @Override
         public void onChanged(@Nullable String s) {
            textView.setText(s);
         }
          });
          return root;
         }
}


