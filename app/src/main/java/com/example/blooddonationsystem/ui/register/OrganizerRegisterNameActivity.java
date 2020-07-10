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

public class OrganizerRegisterNameActivity extends AppCompatActivity {
    private Button buttonNextpage;
    private ImageView buttonPreviouspage;
    private TextInputLayout input_organizer_name;
    private TextInputEditText text_edit_organizer_name;
    private String userType;
    private String organizerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_organizer_registration_name);
        buttonNextpage = (Button) findViewById(R.id.button_next_organizer_name);
        input_organizer_name = findViewById(R.id.input_organizer_name);
        text_edit_organizer_name = findViewById(R.id.text_edit_organizer_name);
        text_edit_organizer_name.addTextChangedListener(NextOrganizerPage);
        userType = getIntent().getExtras().get("userType").toString();


        buttonNextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String OrganizerName = text_edit_organizer_name.getText().toString().trim();
                organizerName = OrganizerName;
                NextOrganizerRegisterScreen();
                Log.e("INFO", "Organizer " + userType);
            }
        });
        buttonPreviouspage = (ImageView) findViewById((R.id.img_back_button_organizer_name));
        buttonPreviouspage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                BackToRegisterPage();
            }
        });
    }
    public void confirmInput(View v) {
        if (!validateOrganizerName() ) {
            return;
        }
        String input = "Organizer Name: " + input_organizer_name.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }

    private boolean validateOrganizerName() {
        String Email = input_organizer_name.getEditText().getText().toString().trim();
        if (Email.isEmpty()) {
            input_organizer_name.setError("Field can't be empty");
            return false;
        } else {
            input_organizer_name.setError(null);
            return true;
        }
    }
    private TextWatcher NextOrganizerPage = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String OrganizerName = text_edit_organizer_name.getText().toString().trim();
            validateOrganizerName();
            buttonNextpage.setEnabled(!OrganizerName.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private  void NextOrganizerRegisterScreen(){
        Intent intent   = new Intent(this, OrganizerRegisterContactActivity.class);
        intent.putExtra("userType", userType);
        intent.putExtra("organizerName", organizerName);

        startActivity(intent);
    }
    private  void BackToRegisterPage(){
        Intent intent   = new Intent(this, RegisterActivity.class);
        this.onBackPressed();
    }


}
