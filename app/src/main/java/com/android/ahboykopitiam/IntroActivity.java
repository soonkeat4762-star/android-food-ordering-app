package com.android.ahboykopitiam;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager2 screenPager;
    private TabLayout tabIndicator;
    private Button btnNext;
    private int position = 0;
    private Button btnGetStarted;
    private Animation btnAnim;
    private TextView tvSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // make the activity on full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // when this activity is about to be launched, we need to check if it's opened before or not
        if (restorePrefData()) {
            Intent mainActivity = new Intent(this, MainActivity.class);
            startActivity(mainActivity);
            finish();
        }

        // 修改这里：始终显示IntroActivity，不再检查是否首次打开
        setContentView(R.layout.activity_intro);


        // ini views
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(this, R.anim.button_animation);
        tvSkip = findViewById(R.id.tv_skip);

        // fill list screen
        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Overview", "Ah Boy Kopitiam is a coffee shop that offers a delightful combination of coffee and snacks. We are dedicated to providing high-quality beverages and a variety of delicious treats to ensure a comfortable and enjoyable dining experience for our customers.", R.raw.page_view));
        mList.add(new ScreenItem("Our Coffee Selection", "Indulge in our exquisite coffee selection at Ah Boy Kopitiam. From aromatic black coffee to creamy lattes, we offer a variety of expertly crafted beverages to satisfy every coffee lover's palate.", R.raw.coffee_brownpink));
        mList.add(new ScreenItem("Tasty Snacks", "Indulge in our delightful snack selection at Ah Boy Kopitiam. From our famous toasted bread (Roti) to a variety of mouthwatering pastries, we have the perfect treats to satisfy your cravings.", R.raw.page_view3));
        screenPager = findViewById(R.id.screen_viewpager);
        IntroViewPagerAdapter introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);



        // setup tablayout with viewpager
        new TabLayoutMediator(tabIndicator, screenPager, (tab, position) -> {
            // No custom configuration needed
        }).attach();

        // next button click listener
        btnNext.setOnClickListener(v -> {
            position = screenPager.getCurrentItem();
            if (position < mList.size()) {
                position++;
                screenPager.setCurrentItem(position);
            }

            if (position == mList.size() - 1) {

                loadLastScreen();
            }
        });

        // tablayout add change listener
        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size() - 1) {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        // Get Started button click listener
        btnGetStarted.setOnClickListener(v -> {
            // open main activity
            Intent mainActivity = new Intent(this, MainActivity.class);
            startActivity(mainActivity, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            startActivity(mainActivity);
            // Save boolean value to storage
            savePrefsData();
            finish();
        });

        // skip button click listener
        tvSkip.setOnClickListener(v -> screenPager.setCurrentItem(mList.size()));
    }


    // 修改这里：restorePrefData() 方法始终返回false，无论之前是否打开过IntroActivity
    private boolean restorePrefData() {

        return false;
    }

    private void savePrefsData() {
        SharedPreferences pref = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend", false);
        editor.apply();
}


    // show the GETSTARTED Button and hide the indicator and the next button
    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);

        // setup animation
        btnGetStarted.setAnimation(btnAnim);
    }
}
