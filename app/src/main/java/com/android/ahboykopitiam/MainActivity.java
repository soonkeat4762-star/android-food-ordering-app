package com.android.ahboykopitiam;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Removing ActionBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // Load animation
        Animation slideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down_animation);
        // Get TextView
        TextView welcomeTextView = findViewById(R.id.welcome);
        // Start animation
        welcomeTextView.startAnimation(slideAnimation);

        // Load animation
        Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_animation);
        // Get TextView
        TextView descriptionTextView = findViewById(R.id.description);
        // Start animation
        descriptionTextView.startAnimation(zoomAnimation);

        // Login Button
        Button btnLogin = findViewById(R.id.login_btn);
        Animation scaleDownLogin = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        Animation scaleUpLogin = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        btnLogin.setOnClickListener(v -> {
            v.startAnimation(scaleDownLogin );
            v.postDelayed(() -> {
                v.startAnimation(scaleUpLogin );
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }, 100);
        });

        // SignUp Button
        Button btnSignup = findViewById(R.id.signup_btn);
        Animation scaleDownSignup = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        Animation scaleUpSignup = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        btnSignup.setOnClickListener(v -> {
            v.startAnimation(scaleDownSignup );
            v.postDelayed(() -> {
                v.startAnimation(scaleUpSignup);
                startActivity(new Intent(MainActivity.this, SignupActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }, 100);
        });

    }

}


