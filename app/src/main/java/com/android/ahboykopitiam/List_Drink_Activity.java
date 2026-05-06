package com.android.ahboykopitiam;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;


public class List_Drink_Activity extends AppCompatActivity {

    Button btnbackDrinks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_list_drink);

        Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        Animation scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);

        btnbackDrinks = findViewById(R.id.btnbackdrink);
        btnbackDrinks.setOnClickListener(v -> {
            v.startAnimation(scaleDown);
            v.postDelayed(() -> v.startAnimation(scaleUp), 100);
            showBackConfirmationDialog();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

    }

    private void showBackConfirmationDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(List_Drink_Activity.this);
        builder.setTitle("Menu Drinks");
        builder.setMessage("Are you sure you want to leave the drinks menu? Remember, you can always return to check out our exciting options!");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", (dialogInterface, i) -> List_Drink_Activity.super.onBackPressed());
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