package com.sp.p2124786assignment.Tasks;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.sp.p2124786assignment.R;
import com.sp.p2124786assignment.Splash.CompleteSplashActivity;
import com.sp.p2124786assignment.Tasks.CompletedTaskActivity;

public class OpenTask extends AppCompatActivity {

    private TextView taskNameTV;
    private TextView taskPointsTV;
    private TextView taskDescriptionTV;
    private TextView taskLocation;
    private MaterialButton completeTask_button;

    private Intent intent;

    private String open_taskName, open_taskPoints, open_taskDesc, open_taskLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_task);
        getSupportActionBar().hide();

        intent = getIntent();

        taskNameTV = findViewById(R.id.taskNameTV);
        taskPointsTV = findViewById(R.id.taskPointsTV);
        taskDescriptionTV = findViewById(R.id.taskDescription);
        taskLocation = findViewById(R.id.taskLocationTV);
        completeTask_button = findViewById(R.id.finalcompleteTask_button);

        getTransferredData();

        setDataAsText();

        completeTask_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CompleteSplashActivity.class);
                intent.putExtra("Task Name", open_taskName);
                intent.putExtra("Task Points", open_taskName);
                intent.putExtra("Task Description", open_taskName);
                intent.putExtra("Task Location", open_taskLocation);
                startActivity(intent);


            }
        });


    }

    private void setDataAsText() {
        taskNameTV.setText(open_taskName);
        taskPointsTV.setText(open_taskPoints + " points");
        taskDescriptionTV.setText(open_taskDesc);
        taskLocation.setText(open_taskLocation);
    }

    private void getTransferredData() {
        open_taskName = intent.getStringExtra("Task Name");
        open_taskPoints = intent.getStringExtra("Task Points");
        open_taskDesc = intent.getStringExtra("Task Description");
        open_taskLocation = intent.getStringExtra("Task Location");
    }
}