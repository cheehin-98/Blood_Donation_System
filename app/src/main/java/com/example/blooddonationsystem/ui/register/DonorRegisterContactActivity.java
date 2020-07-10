package com.example.blooddonationsystem.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blooddonationsystem.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class DonorRegisterContactActivity extends AppCompatActivity {
    private Button buttonNext;
    private ImageView btnBackToPrevious;
    private TextView GenerateMobileNumber;
    private CountryCodePicker ccp;
    private EditText editMobileNumber;
    private TextInputLayout input_donor_contact;
    private TextInputEditText text_edit_donor_contact;
    private String userType;
    private String Birthday;
    private String Gender;
    private String Contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_donor_registration_contact);
        buttonNext =  findViewById(R.id.button_next_donor_contact);
        input_donor_contact = findViewById(R.id.input_donor_contact);
        text_edit_donor_contact= findViewById(R.id.text_edit_donor_contact);

        userType = getIntent().getExtras().get("userType").toString();
        Birthday = getIntent().getExtras().get("Birthday").toString();
        Gender = getIntent().getExtras().get("Gender").toString();

        text_edit_donor_contact.addTextChangedListener(NextPage);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                init();
                String fullnumber = ccp.getFullNumber() + editMobileNumber.getText().toString();
                GenerateMobileNumber.setText(fullnumber);
                Contact = fullnumber;
                NextDonorRegisterScreen();
                Log.e("INFO", "Donor " + userType);
                Log.e("INFO", "Birthday " + Birthday);
                Log.e("INFO", "Gender " + Gender);

            }
        });

        btnBackToPrevious = (ImageView) findViewById((R.id.img_back_button_contact));
        btnBackToPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackToGenderPage();
            }
        });
    }
    public void confirmInput(View v) {
        if (!validateContact() ) {
            return;
        }
        String input = "Contact: " + input_donor_contact.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }

    private boolean validateContact() {
        String contact = input_donor_contact.getEditText().getText().toString().trim();
        if (contact.isEmpty()) {
            input_donor_contact.setError("Field can't be empty");
            return false;
        } else{
            input_donor_contact.setError(null);
            return true;
        }

    }
    private TextWatcher NextPage = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String donorContact = text_edit_donor_contact.getText().toString().trim();
            validateContact();
            buttonNext.setEnabled(!donorContact.isEmpty());


        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
      public void init() {
            ccp = findViewById(R.id.ccp_donor_contact);
            editMobileNumber = findViewById(R.id.text_edit_donor_contact);
            GenerateMobileNumber = findViewById(R.id.mobilenumber);
            editMobileNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getNumber();
                }

                private void getNumber() {
                    String fullname = ccp.getFullNumber() + editMobileNumber.getText().toString();
                    GenerateMobileNumber.setText(fullname);

                }
            });
        }


        private void NextDonorRegisterScreen() {
            Intent intent = new Intent(this, DonorRegisterFullNameActivity.class);
            intent.putExtra("userType", userType);
            intent.putExtra("Birthday", Birthday);
            intent.putExtra("Gender", Gender);
            intent.putExtra("Contact", Contact);
            startActivity(intent);
        }

        private void BackToGenderPage() {
            Intent intent = new Intent(this, DonorRegisterGenderActivity.class);
            this.onBackPressed();
        }

    }




