package com.sp.p2124786assignment.Splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sp.p2124786assignment.Database.UserDatabaseHelper;
import com.sp.p2124786assignment.MainActivity;
import com.sp.p2124786assignment.R;

public class CompleteSplashActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    private TextView congratsMsg;
    private String nameOfUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_completed);
        getSupportActionBar().hide();

        congratsMsg = findViewById(R.id.congratatsMsg_completeActivitySplash);

        // Get the name of user from UserDB
        getNameData();
        // Set the name in TextView along with a message
        setDataAsText();

        // For the "noice" GIF
        mediaPlayer = MediaPlayer.create(this, R.raw.noice_video);
        mediaPlayer.start();

        // Splash Page for 2 Seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(CompleteSplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },1800);
    }

    private void setDataAsText() {
        congratsMsg.setText("Great job\n" + nameOfUser + "!");

    }

    private void getNameData() {
        UserDatabaseHelper userDatabaseHelper = new UserDatabaseHelper(CompleteSplashActivity.this);
        nameOfUser = userDatabaseHelper.getUserName().toString();
    }
}