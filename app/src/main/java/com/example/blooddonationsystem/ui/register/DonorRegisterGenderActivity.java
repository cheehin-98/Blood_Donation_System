package com.example.blooddonationsystem.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blooddonationsystem.R;

public class DonorRegisterGenderActivity extends AppCompatActivity {

    private ImageView btnBackToPrevious ;
    RadioButton radioButton;
    private Button buttonNext;
    private String userType;
    private String Birthday;
    private String Gender;
    private RadioGroup donor_gender_radio_group;
    private RadioButton radio_button_male;
    private RadioButton radio_button_female;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_donor_registration_gender);
        buttonNext = findViewById(R.id.button_next_donor_gender);
        donor_gender_radio_group = findViewById(R.id.donor_gender_radio_group);
        radio_button_male = findViewById(R.id.radio_button_male);
        radio_button_female = findViewById(R.id.radio_button_female);
        userType = getIntent().getExtras().get("userType").toString();
        Birthday = getIntent().getExtras().get("Birthday").toString();


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioId = donor_gender_radio_group.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                if (radioButton == radio_button_male) {
                    Gender = "Male";
                } else if  (radioButton == radio_button_female){
                    Gender = "Female";
                }

                if(donor_gender_radio_group.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select Gender", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int selectedId = donor_gender_radio_group.getCheckedRadioButtonId();
                    radioButton = (RadioButton)findViewById(selectedId);
                    Toast.makeText(getApplicationContext(), radioButton.getText().toString()+" is selected", Toast.LENGTH_SHORT).show();
                }
                NextDonorRegisterScreen();
                Log.e("INFO", "Donor " + userType);
                Log.e("INFO", "Birthday " + Birthday);
            }
        });

        btnBackToPrevious = (ImageView) findViewById((R.id.img_back_button_gender));
        btnBackToPrevious.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               BackToBirthdayPage();
            }
        });
    }
    public void checkGender(View view){
        int radioId = donor_gender_radio_group.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "You are:" + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
    private  void NextDonorRegisterScreen(){
        Intent intent   = new Intent(this, DonorRegisterContactActivity.class);
        intent.putExtra("userType", userType);
        intent.putExtra("Birthday", Birthday);
        intent.putExtra("Gender", Gender);

        startActivity(intent);
    }

    private  void BackToBirthdayPage(){
       Intent intent   = new Intent(this, DonorRegisterBirthdayActivity.class);
        this.onBackPressed();
    }

}



