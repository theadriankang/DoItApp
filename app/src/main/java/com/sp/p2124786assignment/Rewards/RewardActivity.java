package com.sp.p2124786assignment.Rewards;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.button.MaterialButton;
import com.sp.p2124786assignment.Adapter.GoalAdapter;
import com.sp.p2124786assignment.Database.GoalDBHelper;
import com.sp.p2124786assignment.MainActivity;
import com.sp.p2124786assignment.R;

import java.util.ArrayList;

public class RewardActivity extends AppCompatActivity {

    private TextView rewardsPointsToGo;
    private TextView rewards_GoalPoints;
    private androidx.recyclerview.widget.RecyclerView rv_goal;
    private MaterialButton saveNewRewards;

    GoalDBHelper db;
    ArrayList<String> goalName, goalPoint;
    ArrayList<Bitmap> goalPhoto;
    GoalAdapter goalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward);
        getSupportActionBar().hide();

        initUI();


        saveNewRewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to SetGoalActivity

                Intent intent = new Intent(RewardActivity.this, SetGoalActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initUI() {

        goalPoint = new ArrayList<>();
        goalName = new ArrayList<>();

        db = new GoalDBHelper(this);

        // rewardsPointsToGo = findViewById(R.id.rewardsPointsToGo);
        // rewards_GoalPoints = findViewById(R.id.rewards_GoalPoints);
        rv_goal = findViewById(R.id.rv_goal);
        saveNewRewards = findViewById(R.id.saveNewRewards);

        goalAdapter = new GoalAdapter(this, goalName, goalPoint);
        rv_goal.setAdapter(goalAdapter);
        rv_goal.setLayoutManager(new LinearLayoutManager(this));

        storeDatainArray();

    }

    private void storeDatainArray() {
        Cursor cursor = db.getData();
        if (cursor.getCount()==0) {
            Toast.makeText(RewardActivity.this, "No goals yet\nSet some now!", Toast.LENGTH_LONG).show();
            return;
        } else {
            while(cursor.moveToNext()){
                goalName.add(cursor.getString(1));
                goalPoint.add(cursor.getString(2));
            }
        }
    }
}