package com.example.blooddonationsystem.ui.register;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blooddonationsystem.R;

import java.util.Calendar;

public class DonorRegisterBirthdayActivity extends AppCompatActivity {
    private Button buttonNext;
    private ImageView btnBackToPrevious;
    private TextView selectDate;
    DatePickerDialog.OnDateSetListener setListener;
    private String userType;
    private String Birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_donor_registration_birthday);
        buttonNext = findViewById(R.id.btn_donor_birth_next);
        btnBackToPrevious = findViewById((R.id.img_back_button));
        selectDate = findViewById(R.id.select_date);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        userType = getIntent().getExtras().get("userType").toString();

        selectDate.addTextChangedListener(NextPage);

       /* private FirebaseAuth mAuth;

        mAuth = FirebaseAuth.getInstance();

        string senderUserID;
        senderUserID = mAuth.getCurrentUser().getUid();*/

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DonorRegisterBirthdayActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
                Log.e("INFO", "Donor " + userType);

            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                //Log.d(TAG, "onDateSet: date mm/dd/yyy: "+ month + "/" + day + "/" + year);
                    month = month + 1;
                    String date = month+ "/" +day +"/"+ year;
                    selectDate.setText(date);
                    Birthday = date;
            }
        };

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NextDonorRegisterScreen();
            }
        });


        btnBackToPrevious.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                BackToRegisterPage();
            }
        });
    }

    private TextWatcher NextPage = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String select_Date = selectDate.getText().toString().trim();
            buttonNext.setEnabled(!select_Date.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };



    private  void NextDonorRegisterScreen(){
        Intent intent   = new Intent(this, DonorRegisterGenderActivity.class);
        intent.putExtra("userType", userType);
        intent.putExtra("Birthday", Birthday);
        startActivity(intent);
    }

    private  void BackToRegisterPage(){
        Intent intent   = new Intent(this, RegisterActivity.class);
        this.onBackPressed();
    }


}



