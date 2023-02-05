package com.sp.p2124786assignment.About;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.sp.p2124786assignment.MainActivity;
import com.sp.p2124786assignment.R;

public class AboutActivity extends AppCompatActivity {

    private ImageView instagramButton;
    private ImageView namecardButton;
    private ImageView linkedinButton;
    private RelativeLayout documentationDocs;
    private MaterialButton about_backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        getSupportActionBar().hide();

        initializeButton();
        buttonFunction();
    }

    private void initializeButton() {
        // Initialize
        instagramButton = findViewById(R.id.instagramButton);
        namecardButton = findViewById(R.id.namecardButton);
        linkedinButton = findViewById(R.id.linkedinButton);
        documentationDocs = findViewById(R.id.documentationDocs);
        about_backButton = findViewById(R.id.about_backButton);
    }

    private void buttonFunction() {
        instagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.instagram.com/theadriankangg/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        namecardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://onegoodcard.me/profile/adriankang");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        linkedinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.linkedin.com/in/theadriankang/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        documentationDocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://docs.google.com/document/d/1baG0v4RwhQv8-eHqhoJx77wd8fUMG4kqAi2ZwOxFBtA/edit?usp=sharing";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        about_backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}