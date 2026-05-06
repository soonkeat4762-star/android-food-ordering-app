package com.android.ahboykopitiam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class HomepageActivity extends AppCompatActivity {

    // Create DatabaseHelper object
    private DatabaseHelper databaseHelper;

    com.airbnb.lottie.LottieAnimationView animate_image_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize DatabaseHelper object
        databaseHelper = new DatabaseHelper(this);

        // make the activity on full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_homepage);

        // Image Animation

        // Find your LottieAnimationView in the layout
        animate_image_view = findViewById(R.id.coffee_img);

        // Load your animation
        Animation logoAnimation = AnimationUtils.loadAnimation(HomepageActivity.this, R.anim.zoom_animation);

        // Start your animation
        animate_image_view.startAnimation(logoAnimation);

        logoAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        // Ah Boy Text
        TextView txtAhBoy = findViewById(R.id.txt_ah_boy);
        txtAhBoy.setAlpha(0f);
        txtAhBoy.animate().alpha(1f).setDuration(3000);

        // Kopitiam Text
        TextView txtKopitiam = findViewById(R.id.txt_kopitiam);
        txtKopitiam.setAlpha(0f);
        txtKopitiam.animate().alpha(1f).setDuration(3000);

        // Description Text
        TextView txtDesKopitiam = findViewById(R.id.homepage_des);
        txtDesKopitiam.setAlpha(0f);
        txtDesKopitiam.animate().alpha(1f).setDuration(3000);

        // Drinks Menu Button
        Button btnDrink = findViewById(R.id.btnlistdrink);
        Animation scaleDownDrink = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        Animation scaleUpDrink = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        btnDrink.setOnClickListener(v -> {
            v.startAnimation(scaleDownDrink );
            v.postDelayed(() -> v.startAnimation(scaleUpDrink), 100);
            startActivity(new Intent(HomepageActivity.this, List_Drink_Activity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        // Foods Menu Button
        Button btnFood = findViewById(R.id.btnlistfood);
        Animation scaleDownFood = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        Animation scaleUpFood = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        btnFood.setOnClickListener(v -> {
            v.startAnimation(scaleDownFood );
            v.postDelayed(() -> {
                v.startAnimation(scaleUpFood );
                startActivity(new Intent(HomepageActivity.this, List_Food_Activity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }, 100);
        });


        // List of User Button
        Button btnUser = findViewById(R.id.btnlistuser);
        Animation scaleDownUser = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        Animation scaleUpUser = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        btnUser.setText(R.string.view_lists_of_user);
        btnUser.setOnClickListener(v -> {
            v.startAnimation(scaleDownUser);
            v.postDelayed(() -> {
                v.startAnimation(scaleUpUser);

                // Get all users
                List<User> userList = databaseHelper.getAllUsers();

                // Create a StringBuilder to hold the user data
                StringBuilder userData = new StringBuilder();

                // Add each user's full name and email to the StringBuilder
                for (User user : userList) {
                    userData.append("\n\t\t\tFull Name: ").append(user.getFullName()).append("\n\n");
                    userData.append("\t\t\tEmail: ").append(user.getEmail()).append("\n\n\n");
                }

                // Create the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(HomepageActivity.this);
                builder.setTitle("List Data Users");
                builder.setCancelable(true);  // if you want the dialog to be dismissable with the back button

                // Inflate the custom layout/view
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_alert_dialog, null);
                builder.setView(dialogView);

                // Reference to TextView of the custom layout
                TextView textView = dialogView.findViewById(R.id.textViewUserData);
                textView.setText(userData.toString());

                // Add Close button
                builder.setPositiveButton("Close", (dialog, which) -> {
                    // Perform your action on click
                    dialog.dismiss();
                });

                AlertDialog alertDialog = builder.create();

                alertDialog.setOnShowListener(dialog -> {
                    Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);

                    int buttonWidth = 280;
                    LinearLayout.LayoutParams positiveButtonLayoutParams = new LinearLayout.LayoutParams(buttonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);

                    int buttonMargin = 26;
                    positiveButtonLayoutParams.setMargins(buttonMargin, 0, 0, 0);

                    positiveButton.setLayoutParams(positiveButtonLayoutParams);

                    int positiveButtonTextColor = Color.WHITE;
                    positiveButton.setTextColor(positiveButtonTextColor);

                    alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setBackgroundColor(Color.parseColor("#ab8963"));
                });

                alertDialog.show();

            }, 100);
        });



        // Logout Button
        Button btnLogout = findViewById(R.id.btnlogoutkopitiam);
        Animation scaleDownLogout = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        Animation scaleUpLogout = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        btnLogout.setOnClickListener(v -> {
            v.startAnimation(scaleDownLogout);
            v.postDelayed(() -> v.startAnimation(scaleUpLogout), 100);
            showBackConfirmationDialog();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });


    }

    private void showBackConfirmationDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(HomepageActivity.this);
        builder.setTitle("Confirm Exit");
        builder.setMessage("Are you sure you want to exit?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", (dialogInterface, i) -> HomepageActivity.super.onBackPressed());
        builder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());

        android.app.AlertDialog alertDialog = builder.create();

        alertDialog.setOnShowListener(dialog -> {

            Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);


            int buttonWidth = 180;
            LinearLayout.LayoutParams negativeButtonLayoutParams = new LinearLayout.LayoutParams(buttonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams positiveButtonLayoutParams = new LinearLayout.LayoutParams(buttonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);


            int buttonMargin = 16;
            negativeButtonLayoutParams.setMargins(0, 0, buttonMargin, 0);
            positiveButtonLayoutParams.setMargins(buttonMargin, 0, 0, 0);

            negativeButton.setLayoutParams(negativeButtonLayoutParams);
            positiveButton.setLayoutParams(positiveButtonLayoutParams);


            int negativeButtonTextColor = Color.WHITE;
            int positiveButtonTextColor = Color.WHITE;
            negativeButton.setTextColor(negativeButtonTextColor);
            positiveButton.setTextColor(positiveButtonTextColor);


            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setBackgroundColor(Color.parseColor("#ab8963"));
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setBackgroundColor(Color.parseColor("#ab8963"));

        });

        alertDialog.show();
    }

    public void onBackPressed() {

        showBackConfirmationDialog();
    }


}