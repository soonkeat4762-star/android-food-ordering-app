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

public class LoginActivity extends AppCompatActivity {

    EditText txtEmail, txtPassword;
    Button btnLogin;
    TextView signupText;

    DatabaseHelper userDataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // make the activity on full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        userDataHelper = new DatabaseHelper(this);

        txtEmail = findViewById(R.id.login_email);
        txtPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.login_button);
        signupText = findViewById(R.id.signupRedirectText);

        Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        Animation scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);

        btnLogin.setOnClickListener(v -> {

            v.startAnimation(scaleDown);
            v.postDelayed(() -> {
                v.startAnimation(scaleUp);

                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                //Check the email and password are valid
                if (isValidCredentials(email, password)){
                    boolean loginSuccess = userDataHelper.checkLogin(email, password);
                    if (loginSuccess){
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,HomepageActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid account. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid account. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }, 100);
        });

        signupText.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }

    private boolean isValidCredentials(String email, String password){
        return !email.isEmpty() && !password.isEmpty();
    }
}