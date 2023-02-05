package com.sp.p2124786assignment.Tasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.sp.p2124786assignment.R;
import com.sp.p2124786assignment.Splash.CompleteSplashActivity;

public class CompletedTaskActivity extends AppCompatActivity {

    private TextView completedTask_taskName;
    private TextView completedTask_taskPoints;
    private MaterialButton acknowledgeCompleteTask;

    private Intent intent;

    private String completing_taskName, completing_taskPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completed_task);
        getSupportActionBar().hide();

        intent = getIntent();
        getTransferredData();
        setDataAsText();


        completedTask_taskName = findViewById(R.id.completedTask_taskName);
        completedTask_taskPoints = findViewById(R.id.completedTask_taskPoints);
        acknowledgeCompleteTask = findViewById(R.id.acknowledgeCompleteTask);
        acknowledgeCompleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (CompletedTaskActivity.this, CompleteSplashActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void setDataAsText() {
        completedTask_taskName.setText(completing_taskName);
        completedTask_taskPoints.setText(completing_taskPoints + " points");

    }

    private void getTransferredData() {
        completing_taskName = intent.getStringExtra("Task Name");
        completing_taskPoints = intent.getStringExtra("Task Points");
    }
}