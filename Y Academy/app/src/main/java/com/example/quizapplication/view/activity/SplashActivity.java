package com.example.quizapplication.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.example.quizapplication.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 3000; // Adjust the delay as needed
    private final Handler splashHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        // Add a delay before checking session and navigating
        splashHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkSessionAndNavigate();
            }
        }, SPLASH_DELAY);
    }

    private void checkSessionAndNavigate() {
        SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            startActivity(new Intent(this, NavBarActivity.class));
        } else {
            startActivity(new Intent(this, GetStartActivity.class));
        }

        finish();
    }

    @Override
    protected void onDestroy() {
        splashHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
