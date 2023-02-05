package com.sp.p2124786assignment.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.sp.p2124786assignment.FirstTimeUse.FirstOpenActivity;
import com.sp.p2124786assignment.MainActivity;
import com.sp.p2124786assignment.R;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        getSupportActionBar().hide();

        // Splash Page for 2 Seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences = getSharedPreferences(FirstOpenActivity.PREFS_NAME, 0);
                boolean hasLoggedBefore = sharedPreferences.getBoolean("hasLoggedBefore", false);

                if (hasLoggedBefore) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, FirstOpenActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },2000);
    }
}