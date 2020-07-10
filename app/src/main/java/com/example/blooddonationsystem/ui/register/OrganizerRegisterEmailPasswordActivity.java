package com.example.blooddonationsystem.ui.register;


import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.blooddonationsystem.MainActivity;
import com.example.blooddonationsystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.regex.Pattern;

public class OrganizerRegisterEmailPasswordActivity extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 4 characters
                    "$");
    private Button buttonRegister;
    private ImageView img_back_button_organizer_email;
    private TextInputLayout input_organizer_email;
    private TextInputEditText text_edit_organizer_email;
    private TextInputLayout input_organizer_password;
    private TextInputEditText text_edit_organizer_password;
    private ProgressBar progressBarOrganizerRegister;
    private TextView organizer;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db ;
    private FirebaseStorage storage;
    private Uri dataUri;

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
    private String organizerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_organizer_registration_email_password);
        buttonRegister = findViewById(R.id.button_register_organizer_email_password);
        img_back_button_organizer_email = findViewById(R.id.img_back_button_organizer_email);
        input_organizer_email = findViewById(R.id.input_organizer_email);
        text_edit_organizer_email = findViewById(R.id.text_edit_organizer_email);
        input_organizer_password = findViewById(R.id.input_organizer_password);
        text_edit_organizer_password = findViewById(R.id.text_edit_organizer_password);
        organizer = findViewById(R.id.text_organizer_registration_email_password);
        progressBarOrganizerRegister = findViewById(R.id.progressBarOrganizerRegister);
        mAuth = FirebaseAuth.getInstance();
       // db = FirebaseFirestore.getInstance();
        //storage = FirebaseStorage.getInstance();
        text_edit_organizer_email.addTextChangedListener(NextPage);
        text_edit_organizer_password.addTextChangedListener(NextPage);
        //dataUri = Uri.EMPTY;

      /*  if (mAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }*/
        organizerName = getIntent().getExtras().get("organizerName").toString();
        organizerContact = getIntent().getExtras().get("organizerContact").toString();
        organizerAddressLine1 = getIntent().getExtras().get("organizerAddressLine1").toString();
        organizerAddressLine2= getIntent().getExtras().get("organizerAddressLine2").toString();
        organizerCity = getIntent().getExtras().get("organizerCity").toString();
        organizerState = getIntent().getExtras().get("organizerState").toString();
        organizerCountry = getIntent().getExtras().get("organizerCountry").toString();
        organizerZipCode = getIntent().getExtras().get("organizerZipCode").toString();
        organizerUsername = getIntent().getExtras().get("organizerUsername").toString();
        organizerImage = getIntent().getExtras().get("organizerImage").toString();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String organizerEmail = text_edit_organizer_email.getText().toString().trim();
                final String organizerPassword = text_edit_organizer_password.getText().toString().trim();
                final String organizerUser = organizer.getText().toString().trim();

                progressBarOrganizerRegister.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(organizerEmail, organizerPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(OrganizerRegisterEmailPasswordActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(OrganizerRegisterEmailPasswordActivity.this, "Error Occur." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBarOrganizerRegister.setVisibility(View.GONE);
                        }
                    }
                });
                Log.e("INFO", "Organizer " + userType);
                Log.e("INFO", "Organizer Name " + organizerName);
                Log.e("INFO", "Organizer Contact " + organizerContact);
                Log.e("INFO", "Organizer Address Line 1 " + organizerAddressLine1);
                Log.e("INFO", "Organizer Address Line 2 " + organizerAddressLine2);
                Log.e("INFO", "Organizer City " + organizerCity);
                Log.e("INFO", "Organizer State " + organizerState);
                Log.e("INFO", "Organizer Country " + organizerCountry);
                Log.e("INFO", "Organizer Zip Code " + organizerZipCode);
                Log.e("INFO", "Organizer Username " + organizerUsername);
                Log.e("INFO", "Organizer Image " + organizerImage);
                        Register();
            }
        });
        img_back_button_organizer_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Backtopreviouspage();
            }
        });

    }
    public void confirmInput(View view) {
        if (!validateEmail() | !validatePassword()) {
            return;
        }
        String input = "Email: " + input_organizer_email.getEditText().getText().toString();
        input += "\n";
        input += "Password: " + input_organizer_password.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
    private boolean validateEmail() {
        String Email = input_organizer_email.getEditText().getText().toString().trim();
        if (Email.isEmpty()) {
            input_organizer_email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            input_organizer_email.setError("Please enter a valid email address");
            return false;
        } else {
            input_organizer_email.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String password = input_organizer_password.getEditText().getText().toString().trim();
        if (password.isEmpty()) {
            input_organizer_password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            input_organizer_password.setError("Password too weak");
            return false;
        } else {
            input_organizer_password.setError(null);
            return true;
        }

    }

    private TextWatcher NextPage = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String organizerEmail = text_edit_organizer_email.getText().toString().trim();
            String organizerPassword = text_edit_organizer_password.getText().toString().trim();
            validateEmail();
            validatePassword();
            buttonRegister.setEnabled(!organizerEmail.isEmpty() && !organizerPassword.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private void Backtopreviouspage (){
            Intent intent   = new Intent(this, OrganizerRegisterProfilePictureActivity.class);
            onBackPressed();
    }


        private void Register(){
            Intent intent   = new Intent(this, MainActivity.class);
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
            intent.putExtra("organizerImage", organizerImage);
            startActivity(intent);
        }
}
