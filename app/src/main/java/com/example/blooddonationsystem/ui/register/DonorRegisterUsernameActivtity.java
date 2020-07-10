package com.example.blooddonationsystem.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blooddonationsystem.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class DonorRegisterUsernameActivtity extends AppCompatActivity {
    private Button buttonNext;
    private ImageView btnBackToPrevious ;
    private TextInputLayout input_donor_username;
    private TextInputEditText text_edit_donor_username;
    private String userType;
    private String Birthday;
    private String Gender;
    private String Contact;
    private String FullName;
    private String Username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_donor_registration_username);
        buttonNext = findViewById(R.id.button_next_donor_username);
        input_donor_username = findViewById(R.id.input_donor_username);
        text_edit_donor_username = findViewById(R.id.text_edit_donor_username);
        text_edit_donor_username.addTextChangedListener(NextPage);
        userType = getIntent().getExtras().get("userType").toString();
        Birthday = getIntent().getExtras().get("Birthday").toString();
        Gender = getIntent().getExtras().get("Gender").toString();
        Contact = getIntent().getExtras().get("Contact").toString();
        FullName = getIntent().getExtras().get("FullName").toString();


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String donorUsername = text_edit_donor_username.getText().toString().trim();
                Username = donorUsername;
                NextDonorRegisterScreen();
                Log.e("INFO", "Donor " + userType);
                Log.e("INFO", "Birthday " + Birthday);
                Log.e("INFO", "Gender " + Gender);
                Log.e("INFO", "Contact " + Contact);
                Log.e("INFO", "FullName " + FullName);
            }
        });
        btnBackToPrevious =  findViewById((R.id.img_back_button_username));
        btnBackToPrevious.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                BackToFullNamePage();
            }
        });

    }
    public void confirmInput(View v) {
        if (!validateUsername() ) {
            return;
        }
        String input = "Username: " + input_donor_username.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
    private boolean validateUsername() {
        String usernameInput = input_donor_username.getEditText().getText().toString().trim();
        if (usernameInput.isEmpty()) {
            input_donor_username.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            input_donor_username.setError("Username too long");
            return false;
        } else {
            input_donor_username.setError(null);
            return true;
        }
    }
    private TextWatcher NextPage = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String donorUsername = text_edit_donor_username.getText().toString().trim();
            validateUsername();
            buttonNext.setEnabled(!donorUsername.isEmpty());


        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private  void NextDonorRegisterScreen(){
        Intent intent   = new Intent(this, DonorRegisterProfilePictureActivity.class);
        intent.putExtra("userType", userType);
        intent.putExtra("Birthday", Birthday);
        intent.putExtra("Gender", Gender);
        intent.putExtra("Contact", Contact);
        intent.putExtra("FullName", FullName);
        intent.putExtra("Username", Username);
        startActivity(intent);
    }
    private  void BackToFullNamePage(){
        Intent intent   = new Intent(this, DonorRegisterFullNameActivity.class);
        this.onBackPressed();
    }


}
