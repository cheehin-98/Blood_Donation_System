package com.example.blooddonationsystem.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blooddonationsystem.MainActivity;

import com.example.blooddonationsystem.R;

public class RegisterActivity extends AppCompatActivity {
    private Button buttonNext;
    private ImageView btnBackToLogin;
    private RadioButton radioButtonselectDonor;
    private RadioButton radioButtonselectOrganizer;
    private String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);

        btnBackToLogin = (ImageView) findViewById((R.id.img_back_button_login_page));
        btnBackToLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                BackToLoginPage();
            }
        });

        radioButtonselectDonor = findViewById(R.id.radioButton_donor);
        radioButtonselectOrganizer = findViewById(R.id.radioButton_organizer);

        radioButtonselectDonor.setOnClickListener(new View.OnClickListener(){
          @Override
           public void onClick(View view) {
              if (view == radioButtonselectDonor) {
                  userType = "Donor";
                  NextDonorRegisterScreen();
             }

          }
       });
        radioButtonselectOrganizer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (view == radioButtonselectOrganizer) {
                    userType = "Organizer";
                     NextOrganizerScreen();
                }

            }
        });

    }
        private  void NextDonorRegisterScreen(){
            Intent intent   = new Intent(this, DonorRegisterBirthdayActivity.class);
            intent.putExtra("userType", userType);
            startActivity(intent);
        }

        private  void BackToLoginPage(){
        Intent intent   = new Intent(this, MainActivity.class);
        this.onBackPressed();
    }

        private void NextOrganizerScreen(){
        Intent intent   = new Intent(this, OrganizerRegisterNameActivity.class);
        intent.putExtra("userType", userType);
        startActivity(intent);
    }
}
