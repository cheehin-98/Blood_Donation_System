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

public class OrganizerRegisterContactActivity extends AppCompatActivity {
    private Button buttonNextPage;
    private ImageView buttonPreviousPage;
    private TextView GenerateMobileNumber;
    private CountryCodePicker ccpOrganizer;
    private EditText editMobileNumber;
    private TextInputLayout input_organizer_contact;
    private TextInputEditText text_edit_organizer_contact;
    private String userType;
    private String organizerName;
    private String organizerContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_organizer_registration_contact);
        buttonNextPage = (Button) findViewById(R.id.button_next_organizer_contact);
        input_organizer_contact = findViewById(R.id.input_organizer_contact);
        text_edit_organizer_contact= findViewById(R.id.text_edit_organizer_contact);
        text_edit_organizer_contact.addTextChangedListener(NextOrganizerPage);

        userType = getIntent().getExtras().get("userType").toString();
        organizerName = getIntent().getExtras().get("organizerName").toString();



        buttonNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
                String fullnumber = ccpOrganizer.getFullNumber() + editMobileNumber.getText().toString();
                GenerateMobileNumber.setText(fullnumber);
                organizerContact = fullnumber;
                NextOrganizerRegisterScreen();
                Log.e("INFO", "Organizer " + userType);
                Log.e("INFO", "Organizer Name " + organizerName);

            }
        });
        buttonPreviousPage = (ImageView) findViewById((R.id.img_back_button_organizer_contact));
        buttonPreviousPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                BackToOrganizerNamePage();
            }
        });
    }
    public void confirmInput(View v) {
        if (!validateContact() ) {
            return;
        }
        String input = "Contact: " + input_organizer_contact.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
    private boolean validateContact() {
        String password = input_organizer_contact.getEditText().getText().toString().trim();
        if (password.isEmpty()) {
            input_organizer_contact.setError("Field can't be empty");
            return false;
        } else{
            input_organizer_contact.setError(null);
            return true;
        }

    }
    private TextWatcher NextOrganizerPage = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String organizerContact = text_edit_organizer_contact.getText().toString().trim();
            validateContact();
            buttonNextPage.setEnabled(!organizerContact.isEmpty());


        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    public void init() {
        ccpOrganizer = findViewById(R.id.ccp_organizer_contact);
        editMobileNumber = findViewById(R.id.text_edit_organizer_contact);
        GenerateMobileNumber = findViewById(R.id.mobileNumberOrganizer);

        editMobileNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNumber();
            }

            private void getNumber() {
                String fullname = ccpOrganizer.getFullNumber() + editMobileNumber.getText().toString();
                GenerateMobileNumber.setText(fullname);
            }
        });
    }
    private  void NextOrganizerRegisterScreen(){
        Intent intent   = new Intent(this, OrganizerRegisterAddressActivity.class);
        intent.putExtra("userType", userType);
        intent.putExtra("organizerName", organizerName);
        intent.putExtra("organizerContact", organizerContact);

        startActivity(intent);
    }

    private  void BackToOrganizerNamePage(){
        Intent intent   = new Intent(this, OrganizerRegisterNameActivity.class);
        this.onBackPressed();
    }

}








