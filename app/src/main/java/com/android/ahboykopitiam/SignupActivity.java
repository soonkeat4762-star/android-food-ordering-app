package com.android.ahboykopitiam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText txtFullName, txtEmail, txtPhone, txtPassword, txtConfirmPass;
    Button btnRegister;

    TextView loginText;
    DatabaseHelper userDataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

        userDataHelper = new DatabaseHelper(this);

        txtFullName = findViewById(R.id.signup_fullname);
        txtEmail = findViewById(R.id.signup_email);
        txtPhone = findViewById(R.id.signup_phone);
        txtPassword = findViewById(R.id.signup_password);
        txtConfirmPass = findViewById(R.id.signup_confirm);
        btnRegister = findViewById(R.id.signup_button);
        loginText = findViewById(R.id.loginRedirectText);

        Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        Animation scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);

        btnRegister.setOnClickListener(v -> {
            v.startAnimation(scaleDown);
            v.postDelayed(() -> {
                v.startAnimation(scaleUp);
                // Registration logic goes here
                String fullName = txtFullName.getText().toString();
                String email = txtEmail.getText().toString();
                String phone = txtPhone.getText().toString();
                String password = txtPassword.getText().toString();
                String confirmPass = txtConfirmPass.getText().toString();

                if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPass.isEmpty()){
                    Toast.makeText(SignupActivity.this,"Please fill all the data!!!", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPass)) {
                    Toast.makeText(SignupActivity.this, "Please make sure the confirm password is same with password.", Toast.LENGTH_SHORT).show();
                } else if (userDataHelper.checkEmailExists(email)) {
                    Toast.makeText(SignupActivity.this,"This email has been registered, please try other", Toast.LENGTH_SHORT).show();
                } else {
                    boolean success = userDataHelper.insertData(fullName,email,phone,password);
                    if (success){
                        Toast.makeText(SignupActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                        txtFullName.setText("");
                        txtEmail.setText("");
                        txtPhone.setText("");
                        txtPassword.setText("");
                        txtConfirmPass.setText("");
                    } else {
                        Toast.makeText(SignupActivity.this,"Register Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }, 100);
        });

        loginText.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}
