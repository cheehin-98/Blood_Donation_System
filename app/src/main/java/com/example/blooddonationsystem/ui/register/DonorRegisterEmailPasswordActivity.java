package com.example.blooddonationsystem.ui.register;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.example.blooddonationsystem.MainActivity;
import com.example.blooddonationsystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class DonorRegisterEmailPasswordActivity extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 6 characters
                    "$");
    public static final String TAG = "TAG";
    private Button buttonRegister;
    private ImageView btnBackToPrevious;
    private TextInputLayout input_donor_email;
    TextInputEditText text_edit_donor_email;
    private TextInputLayout input_donor_password;
    TextInputEditText text_edit_donor_password;
    private ProgressBar progressBarDonor;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    String userType;
    String Birthday;
    String Gender;
    String Contact;
    String FullName;
    String Username;
    String Image;
    String AddressLine1;
    String AddressLine2;
    String City;
    String State;
    String Country;
    String Zipcode;
    String BloodType;
    String UserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_donor_registration_email);
        buttonRegister = findViewById(R.id.button_register_donor_email_password);
        input_donor_email = findViewById(R.id.input_donor_email);
        text_edit_donor_email = findViewById(R.id.text_edit_donor_email);
        input_donor_password = findViewById(R.id.input_donor_password);
        text_edit_donor_password = findViewById(R.id.text_edit_donor_password);
        btnBackToPrevious = findViewById(R.id.img_back_button_email);
        progressBarDonor = findViewById(R.id.progressBarDonor);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        text_edit_donor_password.addTextChangedListener(NextPage);
        text_edit_donor_email.addTextChangedListener(NextPage);

        userType = getIntent().getExtras().get("userType").toString();
        Birthday = getIntent().getExtras().get("Birthday").toString();
        Gender = getIntent().getExtras().get("Gender").toString();
        Contact = getIntent().getExtras().get("Contact").toString();
        FullName = getIntent().getExtras().get("FullName").toString();
        Username = getIntent().getExtras().get("Username").toString();
        Image = getIntent().getExtras().get("Image").toString();
        AddressLine1 = getIntent().getExtras().get("Address Line 1").toString();
        AddressLine2 = getIntent().getExtras().get("Address Line 2").toString();
        State = getIntent().getExtras().get("State").toString();
        City = getIntent().getExtras().get("City").toString();
        Country = getIntent().getExtras().get("Country").toString();
        Zipcode = getIntent().getExtras().get("Zipcode").toString();
        BloodType = getIntent().getExtras().get("Blood Type").toString();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String donorEmail = text_edit_donor_email.getText().toString().trim();
                final String donorPassword = text_edit_donor_email.getText().toString().trim();
                 progressBarDonor.setVisibility(View.VISIBLE);
                 mAuth.createUserWithEmailAndPassword(donorEmail, donorPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DonorRegisterEmailPasswordActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                            UserID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = db.collection("User Type").document(UserID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("UserType", userType);
                            user.put("DonorBirthday", Birthday);
                            user.put("DonorGender", Gender);
                            user.put("DonorContact", Contact);
                            user.put("DonorEmail", donorEmail);
                            user.put("DonorFullName", FullName);
                            user.put("DonorUsername", Username);
                            user.put("DonorImage", Image);
                            user.put("DonorAddressLine1", AddressLine1);
                            user.put("DonorAddressLine2", AddressLine2);
                            user.put("DonorCity", City);
                            user.put("DonorState", State);
                            user.put("DonorCountry", Country);
                            user.put("DonorZipCode", Zipcode);
                            user.put("DonorBloodType", BloodType);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for " + UserID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(DonorRegisterEmailPasswordActivity.this, "Error Occur." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBarDonor.setVisibility(View.GONE);
                        }
                    }
                });
                Log.e("INFO", "Donor " + userType);
                Log.e("INFO", "Birthday " + Birthday);
                Log.e("INFO", "Gender " + Gender);
                Log.e("INFO", "Contact " + Contact);
                Log.e("INFO", "FullName " + FullName);
                Log.e("INFO", "Username " + Username);
                Log.e("INFO", "Image" + Image);
                Log.e("INFO", "Address Line 1 " + AddressLine1);
                Log.e("INFO", "Address Line 2 " + AddressLine2);
                Log.e("INFO", "City " + City);
                Log.e("INFO", "State " + State);
                Log.e("INFO", "Country " + Country);
                Log.e("INFO", "Zipcode " + Zipcode);
                Log.e("INFO", "Blood Type " + BloodType);

            }
        });
        btnBackToPrevious.setOnClickListener(new View.OnClickListener() {
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
        String input = "Email: " + input_donor_email.getEditText().getText().toString();
        input += "\n";
        input += "Password: " + input_donor_password.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }

    private boolean validateEmail() {
        String Email = input_donor_email.getEditText().getText().toString().trim();
        if (Email.isEmpty()) {
            input_donor_email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            input_donor_email.setError("Please enter a valid email address");
            return false;
        } else {
            input_donor_email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String password = input_donor_password.getEditText().getText().toString().trim();
        if (password.isEmpty()) {
            input_donor_password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            input_donor_password.setError("Password too weak");
            return false;
        } else {
            input_donor_password.setError(null);
            return true;
        }
    }

    private TextWatcher NextPage = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String donorEmail = text_edit_donor_email.getText().toString().trim();
            String donorPassword = text_edit_donor_password.getText().toString().trim();
            validateEmail();
            validatePassword();
            buttonRegister.setEnabled(!donorEmail.isEmpty() && !donorPassword.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void Backtopreviouspage() {
        Intent intent = new Intent(this, DonorRegisterBloodTypeActivity.class);
        onBackPressed();
    }

    private void Register() {
        /*Intent intent = new Intent(this, MainActivity.class);
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
        intent.putExtra("Blood Type", BloodType);
        startActivity(intent);*/
        this.finish();
    }
}














