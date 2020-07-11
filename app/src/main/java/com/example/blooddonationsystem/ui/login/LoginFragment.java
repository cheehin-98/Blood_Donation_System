package com.example.blooddonationsystem.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.blooddonationsystem.MainActivity;
import com.example.blooddonationsystem.R;
import com.example.blooddonationsystem.ui.register.OrganizerRegisterEmailPasswordActivity;
import com.example.blooddonationsystem.ui.register.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class LoginFragment extends Fragment {
    private AppBarConfiguration mAppBarConfiguration;
    private Button buttonLogin;
    private Button buttonRegister;
    private TextInputEditText edit_email;
    private TextInputEditText edit_password;
    private FirebaseAuth mAuth;
    private TextInputLayout inputEmail;
    private TextInputLayout inputPassword;
    private ProgressBar progressBarLogin;
    private FirebaseFirestore db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        buttonRegister = view.findViewById(R.id.btn_register);
        buttonLogin = view.findViewById(R.id.btn_Login);
        inputEmail = view.findViewById(R.id.text_email);
        inputPassword = view.findViewById(R.id.text_password);

        edit_email = view.findViewById(R.id.edit_email);
        edit_password = view.findViewById(R.id.edit_password);
        progressBarLogin = view.findViewById(R.id.progressBarLogin);
        mAuth = FirebaseAuth.getInstance();
        edit_email.addTextChangedListener(NextPage);
        edit_password.addTextChangedListener(NextPage);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String userEmail = edit_email.getText().toString().trim();
                String userPassword = edit_password.getText().toString().trim();


                 progressBarLogin.setVisibility(View.VISIBLE);

                 mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()){

                             // snapshort currentuser id - follow youtbe
                             /*
                              * if(task.issuccful)
                              * {
                              *       Intent intent   = new Intent(this, MainActivity.class);
                                         intent.putExtra("currentuserid", currentuserid);
                               *  startActivity(new Intent(getActivity(), MainActivity.class));
                              * }
                              */

                             Toast.makeText(LoginFragment.this.getActivity(), "Login Successfully!", Toast.LENGTH_SHORT).show();
                             // intent currentuserid
                             startActivity(new Intent(getActivity(), MainActivity.class));
                         }else {
                             Toast.makeText(LoginFragment.this.getActivity(), "Error Occur." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                             progressBarLogin.setVisibility(View.GONE);
                         }
                     }
                 });
                     }
                 });
        return view;
    }
    public void confirmInput(View view) {
        if (!validateEmail() | !validatePassword()) {
            return;
        }
        String input = "Email: " + inputEmail.getEditText().getText().toString();
        input += "\n";
        input += "Password: " + inputPassword.getEditText().getText().toString();

        Toast.makeText(LoginFragment.this.getActivity(), input, Toast.LENGTH_SHORT).show();
    }
    private boolean validateEmail() {
        String Email = inputEmail.getEditText().getText().toString().trim();
        if (Email.isEmpty()) {
            inputEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            inputEmail.setError("Please enter a valid email address");
            return false;
        } else {
            inputEmail.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String password = inputPassword.getEditText().getText().toString().trim();
        if (password.isEmpty()) {
            inputPassword.setError("Field can't be empty");
            return false;
        } else {
            inputPassword.setError(null);
            return true;
        }

    }
    private TextWatcher NextPage = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String userEmail = edit_email.getText().toString().trim();
            String userPassword = edit_password.getText().toString().trim();
            validateEmail();
            validatePassword();
            buttonLogin.setEnabled(!userEmail.isEmpty() && !userPassword.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}





















