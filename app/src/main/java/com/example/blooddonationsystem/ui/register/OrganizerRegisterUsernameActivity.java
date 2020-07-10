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

public class OrganizerRegisterUsernameActivity extends AppCompatActivity {
    private Button buttonNextPage;
    private ImageView buttonPreviousPage;
    private TextInputLayout input_organizer_username;
    private TextInputEditText text_edit_organizer_username;

    private String userType;
    private String organizerName;
    private String organizerContact;
    private String organizerAddressLine1;
    private String organizerAddressLine2;
    private String organizerCity;
    private String organizerState;
    private String organizerCountry;
    private String organizerZipCode;
    private String organizerUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_organizer_registration_username);
        buttonNextPage = (Button) findViewById(R.id.button_next_organizer_username);
        input_organizer_username = findViewById(R.id.input_organizer_username);
        text_edit_organizer_username = findViewById(R.id.text_edit_organizer_username);
        text_edit_organizer_username.addTextChangedListener(NextOrganizerPage);

        userType = getIntent().getExtras().get("userType").toString();
        organizerName = getIntent().getExtras().get("organizerName").toString();
        organizerContact = getIntent().getExtras().get("organizerContact").toString();
        organizerAddressLine1 = getIntent().getExtras().get("organizerAddressLine1").toString();
        organizerAddressLine2= getIntent().getExtras().get("organizerAddressLine2").toString();
        organizerCity = getIntent().getExtras().get("organizerCity").toString();
        organizerState = getIntent().getExtras().get("organizerState").toString();
        organizerCountry = getIntent().getExtras().get("organizerCountry").toString();
        organizerZipCode = getIntent().getExtras().get("organizerZipCode").toString();


        buttonNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = text_edit_organizer_username.getText().toString().trim();
                organizerUsername = username;
                NextOrganizerRegisterScreen();
                Log.e("INFO", "Organizer " + userType);
                Log.e("INFO", "Organizer Name " + organizerName);
                Log.e("INFO", "Organizer Contact " + organizerContact);
                Log.e("INFO", "Organizer Address Line 1 " + organizerAddressLine1);
                Log.e("INFO", "Organizer Address Line 2 " + organizerAddressLine2);
                Log.e("INFO", "Organizer City " + organizerCity);
                Log.e("INFO", "Organizer State " + organizerState);
                Log.e("INFO", "Organizer Country " + organizerCountry);
                Log.e("INFO", "Organizer Zip Code " + organizerZipCode);
            }
        });
        buttonPreviousPage = (ImageView) findViewById((R.id.img_back_button_organizer_username));
        buttonPreviousPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                BackToOrganizerAddressPage();
            }
        });
    }
    public void confirmInput(View v) {
        if (!validateUsername() ) {
            return;
        }
        String input = "Username: " + input_organizer_username.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
    private boolean validateUsername() {
        String usernameInput = input_organizer_username.getEditText().getText().toString().trim();
        if (usernameInput.isEmpty()) {
            input_organizer_username.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            input_organizer_username.setError("Username too long");
            return false;
        } else {
            input_organizer_username.setError(null);
            return true;
        }
    }
    private TextWatcher NextOrganizerPage = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String organizerUsername = text_edit_organizer_username.getText().toString().trim();
            validateUsername();
            buttonNextPage.setEnabled(!organizerUsername.isEmpty());


        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private  void NextOrganizerRegisterScreen(){
        Intent intent   = new Intent(this, OrganizerRegisterProfilePictureActivity.class);
        intent.putExtra("userType", userType);
        intent.putExtra("organizerName", organizerName);
        intent.putExtra("organizerContact", organizerContact);
        intent.putExtra("organizerAddressLine1", organizerAddressLine1);
        intent.putExtra("organizerAddressLine2", organizerAddressLine2);
        intent.putExtra("organizerCity", organizerCity);
        intent.putExtra("organizerState", organizerState);
        intent.putExtra("organizerCountry", organizerCountry);
        intent.putExtra("organizerZipCode", organizerZipCode);
        intent.putExtra("organizerUsername", organizerUsername);
        startActivity(intent);
    }

    private  void BackToOrganizerAddressPage(){
        Intent intent   = new Intent(this, OrganizerRegisterAddressActivity.class);
        this.onBackPressed();
    }
}


