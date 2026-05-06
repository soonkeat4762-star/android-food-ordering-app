package com.android.ahboykopitiam;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);


        setContentView(R.layout.activity_splashscreen);

        TextView name = findViewById(R.id.name);
        TextView slogan = findViewById(R.id.slogan);

        com.airbnb.lottie.LottieAnimationView splash_image_view = findViewById(R.id.splash_image_view);

        View topView1 = findViewById(R.id.topView1);
        View topView2 = findViewById(R.id.topView2);
        View topView3 = findViewById(R.id.topView3);
        View bottomView1 = findViewById(R.id.bottomView1);
        View bottomView2 = findViewById(R.id.bottomView2);
        View bottomView3 = findViewById(R.id.bottomView3);
        Animation logoAnimation = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.zoom_animation);
        Animation nameAnimation = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.slide_down_animation);

        Animation topView1Animation = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.top_views_animation);
        Animation topView2Animation = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.top_views_animation);
        Animation topView3Animation = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.top_views_animation);

        Animation bottomView1Animation = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.bottom_views_animation);
        Animation bottomView2Animation = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.bottom_views_animation);
        Animation bottomView3Animation = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.bottom_views_animation);

        topView1.startAnimation(topView1Animation);
        bottomView1.startAnimation(bottomView1Animation);

        topView1Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                topView2.setVisibility(View.VISIBLE);
                bottomView2.setVisibility(View.VISIBLE);
                topView2.startAnimation(topView2Animation);
                bottomView2.startAnimation(bottomView2Animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        topView2Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                topView3.setVisibility(View.VISIBLE);
                bottomView3.setVisibility(View.VISIBLE);
                topView3.startAnimation(topView3Animation);
                bottomView3.startAnimation(bottomView3Animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        topView3Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                splash_image_view.setVisibility(View.VISIBLE);
                splash_image_view.startAnimation(logoAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        logoAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                name.setVisibility(View.VISIBLE);
                name.startAnimation(nameAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        nameAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                slogan.setVisibility(View.VISIBLE);
                String animateTxt = slogan.getText().toString();
                slogan.setText("");
                int[] count = {0};
                new CountDownTimer(animateTxt.length() * 200L, 200) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (count[0] < animateTxt.length()) {
                            slogan.setText(getString(R.string.slogan_text, slogan.getText().toString() + animateTxt.charAt(count[0])));
                            count[0]++;
                        }
                    }

                    @Override
                    public void onFinish() {


                        startIntroScreen();

                    }
                }.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });


    }

    private void startIntroScreen() {

        Intent intent = new Intent(SplashScreen.this,IntroActivity.class);
        startActivity(intent);
        finish();
    }
}

