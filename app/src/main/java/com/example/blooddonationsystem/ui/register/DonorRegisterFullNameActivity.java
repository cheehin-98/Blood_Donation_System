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

public class DonorRegisterFullNameActivity extends AppCompatActivity {
    private Button buttonNext;
    private ImageView btnBackToPrevious ;
    private TextInputLayout input_donor_fullname;
    private TextInputEditText text_edit_donor_fullname;
    private String userType;
    private String Birthday;
    private String Gender;
    private String Contact;
    private String FullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_donor_registration_fullname);
        buttonNext = (Button) findViewById(R.id.btn_next_fullname);
        input_donor_fullname = findViewById(R.id.input_donor_fullname);
        userType = getIntent().getExtras().get("userType").toString();
        Birthday = getIntent().getExtras().get("Birthday").toString();
        Gender = getIntent().getExtras().get("Gender").toString();
        Contact = getIntent().getExtras().get("Contact").toString();

        text_edit_donor_fullname = findViewById(R.id.text_edit_donor_fullname);

        text_edit_donor_fullname.addTextChangedListener(NextPage);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String donorFullName = text_edit_donor_fullname.getText().toString().trim();
                FullName = donorFullName;
                NextDonorRegisterScreen();
                Log.e("INFO", "Donor " + userType);
                Log.e("INFO", "Birthday " + Birthday);
                Log.e("INFO", "Gender " + Gender);
                Log.e("INFO", "Contact " + Contact);

            }
        });
        btnBackToPrevious = (ImageView) findViewById((R.id.img_back_button_fullname));
        btnBackToPrevious.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                BackToContactPage();
            }
        });
    }
    public void confirmInput(View v) {
        if (!validateFullName() ) {
            return;
        }
        String input = "FullName: " + input_donor_fullname.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
    private boolean validateFullName() {
        String Email = input_donor_fullname.getEditText().getText().toString().trim();
        if (Email.isEmpty()) {
            input_donor_fullname.setError("Field can't be empty");
            return false;
        } else {
            input_donor_fullname.setError(null);
            return true;
        }
    }
    private TextWatcher NextPage = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String donorFullName = text_edit_donor_fullname.getText().toString().trim();
            validateFullName();
            buttonNext.setEnabled(!donorFullName.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private  void NextDonorRegisterScreen(){
        Intent intent   = new Intent(this, DonorRegisterUsernameActivtity.class);
        intent.putExtra("userType", userType);
        intent.putExtra("Birthday", Birthday);
        intent.putExtra("Gender", Gender);
        intent.putExtra("Contact", Contact);
        intent.putExtra("FullName", FullName);
        startActivity(intent);
    }

    private  void BackToContactPage(){
        Intent intent   = new Intent(this, DonorRegisterContactActivity.class);
        this.onBackPressed();
    }

}


