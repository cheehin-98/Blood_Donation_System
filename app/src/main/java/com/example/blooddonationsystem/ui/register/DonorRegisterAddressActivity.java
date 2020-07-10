package com.example.blooddonationsystem.ui.register;

import android.content.Intent;
import android.net.Uri;
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

public class DonorRegisterAddressActivity extends AppCompatActivity {
    private Button buttonNext;
    private ImageView btnBackToPrevious;
    private TextInputLayout input_donor_address_line_1;
    private TextInputLayout input_donor_address_line_2;
    private TextInputLayout input_donor_city;
    private TextInputLayout input_donor_state;
    private TextInputLayout input_donor_country;
    private TextInputLayout input_donor_zipcode;

    private TextInputEditText edit_donor_address_line_1;
    private TextInputEditText edit_donor_address_line_2;
    private TextInputEditText edit_donor_city;
    private TextInputEditText edit_donor_state;
    private TextInputEditText edit_donor_country;
    private TextInputEditText edit_donor_zipcode;

    private String userType;
    private String Birthday;
    private String Gender;
    private String Contact;
    private String FullName;
    private String Username;
    private String Image;
    private String AddressLine1;
    private String AddressLine2;
    private String City;
    private String State;
    private String Country;
    private String Zipcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_donor_registration_address);

        input_donor_address_line_1 = findViewById(R.id.input_donor_address_line_1);
        input_donor_address_line_2 = findViewById(R.id.input_donor_address_line_2);
        input_donor_city = findViewById(R.id.input_donor_city);
        input_donor_state = findViewById(R.id.input_donor_state);
        input_donor_country = findViewById(R.id.input_donor_country);
        input_donor_zipcode = findViewById(R.id.input_donor_zipcode);

        edit_donor_address_line_1 = findViewById(R.id.text_edit_donor_address_line_1);
        edit_donor_address_line_2 = findViewById(R.id.text_edit_donor_address_line_2);
        edit_donor_city = findViewById(R.id.text_edit_donor_city);
        edit_donor_country = findViewById(R.id.text_edit_donor_country);
        edit_donor_state = findViewById(R.id.text_edit_donor_state);
        edit_donor_zipcode = findViewById(R.id.text_edit_donor_zipcode);

        btnBackToPrevious = findViewById((R.id.img_back_button_address));
        buttonNext = findViewById(R.id.button_next_donor_address);

        edit_donor_address_line_1.addTextChangedListener(NextPage);
        edit_donor_address_line_2.addTextChangedListener(NextPage);
        edit_donor_city.addTextChangedListener(NextPage);
        edit_donor_state.addTextChangedListener(NextPage);
        edit_donor_country.addTextChangedListener(NextPage);
        edit_donor_zipcode.addTextChangedListener(NextPage);

        userType = getIntent().getExtras().get("userType").toString();
        Birthday = getIntent().getExtras().get("Birthday").toString();
        Gender = getIntent().getExtras().get("Gender").toString();
        Contact = getIntent().getExtras().get("Contact").toString();
        FullName = getIntent().getExtras().get("FullName").toString();
        Username = getIntent().getExtras().get("Username").toString();
        Image = getIntent().getExtras().get("Image").toString();



        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //confirmInput();
                String addressline1 = edit_donor_address_line_1.getText().toString().trim();
                String addressline2 = edit_donor_address_line_2.getText().toString().trim();
                String city = edit_donor_city.getText().toString().trim();
                String state = edit_donor_state.getText().toString().trim();
                String country = edit_donor_country.getText().toString().trim();
                String zipcode = edit_donor_zipcode.getText().toString().trim();
                AddressLine1 = addressline1;
                AddressLine2 = addressline2;
                City = city;
                State = state;
                Country = country;
                Zipcode =zipcode;
                NextDonorRegisterScreen();
                Log.e("INFO", "Donor " + userType);
                Log.e("INFO", "Birthday " + Birthday);
                Log.e("INFO", "Gender " + Gender);
                Log.e("INFO", "Contact " + Contact);
                Log.e("INFO", "FullName " + FullName);
                Log.e("INFO", "Username " + Username);
                Log.e("INFO", "Image" + Image);
            }
        });


        btnBackToPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackToProfilePicturePage();
            }
        });

    }


    public void confirmInput(View v) {
        if (!validateAddress1() | !validateAddress2() | !validateCity() | !validateState() | !validateCountry() | !validateZipCode()) {
            return;
        }
        String input = "Address Line 1: " + input_donor_address_line_1.getEditText().getText().toString();
        input += "\n";
        input += "Address Line 2: " + input_donor_address_line_2.getEditText().getText().toString();
        input += "\n";
        input += "City: " + input_donor_city.getEditText().getText().toString();
        input += "\n";
        input += "State: " + input_donor_state.getEditText().getText().toString();
        input += "\n";
        input += "Country: " + input_donor_country.getEditText().getText().toString();
        input += "\n";
        input += "Zip Code: " + input_donor_zipcode.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
    private boolean validateAddress1() {
        String addresslineInput1 = input_donor_address_line_1.getEditText().getText().toString().trim();
        if (addresslineInput1.isEmpty()) {
            input_donor_address_line_1.setError("Field can't be empty");
            return false;
        } else {
            input_donor_address_line_1.setError(null);
            return true;
        }
    }

    private boolean validateAddress2() {
        String addresslineInput2 = input_donor_address_line_2.getEditText().getText().toString().trim();
        if (addresslineInput2.isEmpty()) {
            input_donor_address_line_2.setError("Field can't be empty");
            return false;
        } else {
            input_donor_address_line_2.setError(null);
            return true;
        }
    }

    private boolean validateCity() {
        String City = input_donor_city.getEditText().getText().toString().trim();
        if (City.isEmpty()) {
            input_donor_city.setError("Field can't be empty");
            return false;
        } else {
            input_donor_city.setError(null);
            return true;
        }
    }

    private boolean validateState() {
        String State = input_donor_state.getEditText().getText().toString().trim();
        if (State.isEmpty()) {
            input_donor_state.setError("Field can't be empty");
            return false;
        } else {
            input_donor_state.setError(null);
            return true;
        }
    }

    private boolean validateCountry() {
        String Country = input_donor_country.getEditText().getText().toString().trim();
        if (Country.isEmpty()) {
            input_donor_country.setError("Field can't be empty");
            return false;
        } else {
            input_donor_country.setError(null);
            return true;
        }
    }

    private boolean validateZipCode() {
        String ZipCode = input_donor_zipcode.getEditText().getText().toString().trim();
        if (ZipCode.isEmpty()) {
            input_donor_zipcode.setError("Field can't be empty");
            return false;
        } else {
            input_donor_zipcode.setError(null);
            return true;
        }
    }

    private TextWatcher NextPage = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String addressline1 = edit_donor_address_line_1.getText().toString().trim();
            String addressline2 = edit_donor_address_line_2.getText().toString().trim();
            String city = edit_donor_city.getText().toString().trim();
            String state = edit_donor_state.getText().toString().trim();
            String country = edit_donor_country.getText().toString().trim();
            String zipcode = edit_donor_zipcode.getText().toString().trim();

            validateAddress1();
            validateAddress2();
            validateCity();
            validateCountry();
            validateState();
            validateZipCode();

            buttonNext.setEnabled(!addressline1.isEmpty() && !addressline2.isEmpty() && !city.isEmpty() && !state.isEmpty() && !country.isEmpty() && !zipcode.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void NextDonorRegisterScreen() {
        Intent intent = new Intent(this, DonorRegisterBloodTypeActivity.class);
        intent.putExtra("userType", userType);
        intent.putExtra("Birthday", Birthday);
        intent.putExtra("Gender", Gender);
        intent.putExtra("Contact", Contact);
        intent.putExtra("FullName", FullName);
        intent.putExtra("Username", Username);
        intent.putExtra("Image", Image);
        intent.putExtra("Address Line 1", AddressLine1);
        intent.putExtra("Address Line 2", AddressLine2);
        intent.putExtra("City", City);
        intent.putExtra("State", State);
        intent.putExtra("Country", Country);
        intent.putExtra("Zipcode", Zipcode);

        startActivity(intent);

    }
    private void BackToProfilePicturePage() {
        Intent intent = new Intent(this, DonorRegisterProfilePictureActivity.class);
        this.onBackPressed();
    }

}
