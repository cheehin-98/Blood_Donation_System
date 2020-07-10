package com.example.blooddonationsystem.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blooddonationsystem.R;

public class DonorRegisterBloodTypeActivity extends AppCompatActivity {

    private Button buttonNext;
    private ImageView btnBackToPrevious ;
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
    RadioButton radioButton;
    private RadioGroup donor_blood_type_radio_group ;
    private RadioButton radio_button_blood_a;
    private RadioButton radio_button_blood_b;
    private RadioButton radio_button_blood_ab;
    private RadioButton radio_button_blood_o;
    private RadioButton radio_button_blood_empty_blood;
    private String BloodType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_donor_registration_blood_type);
        buttonNext =  findViewById(R.id.button_next_blood_type);
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

        donor_blood_type_radio_group = findViewById(R.id.donor_blood_type_radio_group);
        radio_button_blood_a = findViewById(R.id.radio_button_blood_a);
        radio_button_blood_b = findViewById(R.id.radio_button_blood_b);
        radio_button_blood_ab =findViewById(R.id.radio_button_blood_ab);
        radio_button_blood_o = findViewById(R.id.radio_button_blood_o);
        radio_button_blood_empty_blood = findViewById(R.id.radio_button_empty_blood);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = donor_blood_type_radio_group.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                if (radioButton == radio_button_blood_a) {
                    BloodType = "A";
                } else if  (radioButton == radio_button_blood_b){
                    BloodType = "B";
                }else if (radioButton == radio_button_blood_ab){
                    BloodType = "AB";
                }else if (radioButton == radio_button_blood_o){
                    BloodType = "O";
                }else if (radioButton == radio_button_blood_empty_blood){
                    BloodType = "No idea";
                }

                if(donor_blood_type_radio_group.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select Blood Type", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int selectedId = donor_blood_type_radio_group.getCheckedRadioButtonId();
                    radioButton = findViewById(selectedId);
                    Toast.makeText(getApplicationContext(), radioButton.getText().toString()+" is selected", Toast.LENGTH_SHORT).show();
                }

                RegisterEmailPasswordpage();
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
            }
        });

        btnBackToPrevious = findViewById((R.id.img_back_button_blood_type));
        btnBackToPrevious.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                BackToAddressPage();
            }
        });
    }
    public void checkBloodType(View view){
        int radioId = donor_blood_type_radio_group.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "You have blood/ " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

    private  void RegisterEmailPasswordpage(){
        Intent intent   = new Intent(this, DonorRegisterEmailPasswordActivity.class);
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
        startActivity(intent);
    }

    private  void BackToAddressPage(){
        Intent intent   = new Intent(this, DonorRegisterAddressActivity.class);
        this.onBackPressed();
    }
}
