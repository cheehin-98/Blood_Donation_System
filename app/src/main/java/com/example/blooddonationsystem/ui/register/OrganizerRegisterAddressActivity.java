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

public class OrganizerRegisterAddressActivity extends AppCompatActivity {
    private Button buttonNextPage;
    private ImageView buttonPreviousPage;
    private TextInputLayout input_organizer_address_line_1;
    private TextInputLayout input_organizer_address_line_2;
    private TextInputLayout input_organizer_city;
    private TextInputLayout input_organizer_state;
    private TextInputLayout input_organizer_country;
    private TextInputLayout input_organizer_zipcode;

    private TextInputEditText text_edit_organizer_address_line_1;
    private TextInputEditText text_edit_organizer_address_line_2;
    private TextInputEditText text_edit_organizer_city;
    private TextInputEditText text_edit_organizer_state;
    private TextInputEditText text_edit_organizer_country;
    private TextInputEditText text_edit_organizer_zipcode;

    private String userType;
    private String organizerName;
    private String organizerContact;
    private String organizerAddressLine1;
    private String organizerAddressLine2;
    private String organizerCity;
    private String organizerState;
    private String organizerCountry;
    private String organizerZipCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_organizer_registration_address);
        buttonNextPage = (Button) findViewById(R.id.button_next_organizer_address);

        input_organizer_address_line_1 = findViewById(R.id.input_organizer_address_line_1);
        input_organizer_address_line_2 = findViewById(R.id.input_organizer_address_line_2);
        input_organizer_city = findViewById(R.id. input_organizer_city);
        input_organizer_state = findViewById(R.id.input_organizer_state);
        input_organizer_country = findViewById(R.id.input_organizer_country);
        input_organizer_zipcode = findViewById(R.id.input_organizer_zipcode);

        text_edit_organizer_address_line_1 = findViewById(R.id.text_edit_organizer_address_line_1);
        text_edit_organizer_address_line_2 = findViewById(R.id.text_edit_organizer_address_line_2);
        text_edit_organizer_city = findViewById(R.id.text_edit_organizer_city);
        text_edit_organizer_state = findViewById(R.id.text_edit_organizer_state);
        text_edit_organizer_country = findViewById(R.id.text_edit_organizer_country);
        text_edit_organizer_zipcode = findViewById(R.id.text_edit_organizer_zipcode);

        text_edit_organizer_address_line_1.addTextChangedListener(NextOrganizerPage);
        text_edit_organizer_address_line_2.addTextChangedListener(NextOrganizerPage);
        text_edit_organizer_city.addTextChangedListener(NextOrganizerPage);
        text_edit_organizer_state.addTextChangedListener(NextOrganizerPage);
        text_edit_organizer_country.addTextChangedListener(NextOrganizerPage);
        text_edit_organizer_zipcode.addTextChangedListener(NextOrganizerPage);

        userType = getIntent().getExtras().get("userType").toString();
        organizerName = getIntent().getExtras().get("organizerName").toString();
        organizerContact = getIntent().getExtras().get("organizerContact").toString();



        buttonNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addressline1 = text_edit_organizer_address_line_1.getText().toString().trim();
                String addressline2 = text_edit_organizer_address_line_2.getText().toString().trim();
                String city = text_edit_organizer_city.getText().toString().trim();
                String state = text_edit_organizer_state.getText().toString().trim();
                String country = text_edit_organizer_country.getText().toString().trim();
                String zipcode = text_edit_organizer_zipcode.getText().toString().trim();

                organizerAddressLine1 = addressline1;
                organizerAddressLine2 = addressline2;
                organizerCity = city;
                organizerState = state;
                organizerCountry = country;
                organizerZipCode = zipcode;

                NextOrganizerRegisterScreen();
                Log.e("INFO", "Organizer " + userType);
                Log.e("INFO", "Organizer Name " + organizerName);
                Log.e("INFO", "Organizer Contact " + organizerContact);

            }
        });
        buttonPreviousPage = (ImageView) findViewById((R.id.img_back_button_organizer_address));
        buttonPreviousPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                BackToOrganizerContactPage();
            }
        });
    }
    public void confirmInput(View v) {
        if (!validateAddress1() | !validateAddress2() | !validateCity() | !validateState() | !validateCountry() | !validateZipCode()) {
            return;
        }
        String input = "Address Line 1: " + input_organizer_address_line_1.getEditText().getText().toString();
        input += "\n";
        input += "Address Line 2: " + input_organizer_address_line_2.getEditText().getText().toString();
        input += "\n";
        input += "City: " + input_organizer_city.getEditText().getText().toString();
        input += "\n";
        input += "State: " + input_organizer_state.getEditText().getText().toString();
        input += "\n";
        input += "Country: " + input_organizer_country.getEditText().getText().toString();
        input += "\n";
        input += "Zip Code: " + input_organizer_zipcode.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }

    private boolean validateAddress1() {
        String addresslineInput1 = input_organizer_address_line_1.getEditText().getText().toString().trim();
        if (addresslineInput1.isEmpty()) {
            input_organizer_address_line_1.setError("Field can't be empty");
            return false;
        } else {
            input_organizer_address_line_1.setError(null);
            return true;
        }
    }

    private boolean validateAddress2() {
        String addresslineInput2 = input_organizer_address_line_2.getEditText().getText().toString().trim();
        if (addresslineInput2.isEmpty()) {
            input_organizer_address_line_2.setError("Field can't be empty");
            return false;
        } else {
            input_organizer_address_line_2.setError(null);
            return true;
        }
    }

    private boolean validateCity() {
        String City = input_organizer_city.getEditText().getText().toString().trim();
        if (City.isEmpty()) {
            input_organizer_city.setError("Field can't be empty");
            return false;
        } else {
            input_organizer_city.setError(null);
            return true;
        }
    }

    private boolean validateState() {
        String State = input_organizer_state.getEditText().getText().toString().trim();
        if (State.isEmpty()) {
            input_organizer_state.setError("Field can't be empty");
            return false;
        } else {
            input_organizer_state.setError(null);
            return true;
        }
    }

    private boolean validateCountry() {
        String Country = input_organizer_country.getEditText().getText().toString().trim();
        if (Country.isEmpty()) {
            input_organizer_country.setError("Field can't be empty");
            return false;
        } else {
            input_organizer_country.setError(null);
            return true;
        }
    }

    private boolean validateZipCode() {
        String ZipCode = input_organizer_zipcode.getEditText().getText().toString().trim();
        if (ZipCode.isEmpty()) {
            input_organizer_zipcode.setError("Field can't be empty");
            return false;
        } else {
            input_organizer_zipcode.setError(null);
            return true;
        }
    }
    private TextWatcher NextOrganizerPage = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String addressline1 = text_edit_organizer_address_line_1.getText().toString().trim();
            String addressline2 = text_edit_organizer_address_line_2.getText().toString().trim();
            String city = text_edit_organizer_city.getText().toString().trim();
            String state = text_edit_organizer_state.getText().toString().trim();
            String country = text_edit_organizer_country.getText().toString().trim();
            String zipcode = text_edit_organizer_zipcode.getText().toString().trim();

            validateAddress1();
            validateAddress2();
            validateCity();
            validateCountry();
            validateState();
            validateZipCode();

            buttonNextPage.setEnabled(!addressline1.isEmpty() && !addressline2.isEmpty() && !city.isEmpty() && !state.isEmpty() && !country.isEmpty() && !zipcode.isEmpty());
        }
        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private  void NextOrganizerRegisterScreen(){
        Intent intent   = new Intent(this, OrganizerRegisterUsernameActivity.class);
        intent.putExtra("userType", userType);
        intent.putExtra("organizerName", organizerName);
        intent.putExtra("organizerContact", organizerContact);
        intent.putExtra("organizerAddressLine1", organizerAddressLine1);
        intent.putExtra("organizerAddressLine2", organizerAddressLine2);
        intent.putExtra("organizerCity", organizerCity);
        intent.putExtra("organizerState", organizerState);
        intent.putExtra("organizerCountry", organizerCountry);
        intent.putExtra("organizerZipCode", organizerZipCode);
        startActivity(intent);
    }

    private  void BackToOrganizerContactPage(){
        Intent intent   = new Intent(this, OrganizerRegisterContactActivity.class);
        this.onBackPressed();
    }
}
