package com.sp.p2124786assignment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.sp.p2124786assignment.About.AboutActivity;
import com.sp.p2124786assignment.Adapter.TaskAdapter;
import com.sp.p2124786assignment.Database.TaskDBHelper;
import com.sp.p2124786assignment.Database.UserDatabaseHelper;
import com.sp.p2124786assignment.Rewards.RewardActivity;
import com.sp.p2124786assignment.Rewards.SetGoalActivity;
import com.sp.p2124786assignment.Splash.CompleteSplashActivity;
import com.sp.p2124786assignment.Tasks.AddTaskActivity;
import com.sp.p2124786assignment.Tasks.TasksActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView greetMsg;
    private Button aboutPageButton;
    private Button completetask;
    private Button toRewards;
    private FloatingActionButton addTaskButton;

    // Task
    RecyclerView tasksMainRV;
    ArrayList<String> taskTitle, taskPoints, taskDesc, taskLon, taskLat;
    TaskDBHelper taskDB;
    TaskAdapter taskAdapter;

    // Drawer
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;

    //public ActionBarDrawerToggle toggle;
    // private UserDatabaseHelper userHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initialiseComponents();

        setUpComponents();

        buttonFunctions();

    }

    private void buttonFunctions() {
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        /*
        Test Page Buttons
        aboutPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        // Test Gif splash page
        completetask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CompleteSplashActivity.class);
                startActivity(intent);
            }
        });


        toRewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RewardActivity.class);
                startActivity(intent);
            }
        });

         */

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId())
                {
                    case R.id.nav_addtasks:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(getApplicationContext(), AddTaskActivity.class));
                        break;

                    case R.id.nav_exit:
                        Log.i("MENU_DRAWER_TAG", "Fitness item is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                        homeIntent.addCategory( Intent.CATEGORY_HOME );
                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(homeIntent);
                        break;

                    case R.id.nav_tasks:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(getApplicationContext(), TasksActivity.class));
                        break;

                    case R.id.nav_rewards:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(getApplicationContext(), RewardActivity.class));
                        break;

                    case R.id.nav_setgoal:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(getApplicationContext(), SetGoalActivity.class));
                        break;

                    case R.id.nav_about:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                        break;


                }
                return true;
            }
        });
    }

    private void setUpComponents() {
        // Task
        taskDB = new TaskDBHelper(this);
        taskTitle = new ArrayList<>();
        taskPoints = new ArrayList<>();
        taskDesc = new ArrayList<>();
        tasksMainRV = findViewById(R.id.tasksMainRV);
        taskAdapter = new TaskAdapter(this, taskTitle, taskPoints, taskDesc);
        tasksMainRV.setAdapter(taskAdapter);
        tasksMainRV.setLayoutManager(new LinearLayoutManager(this));
        displayData();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initialiseComponents() {
        // getSupportActionBar().hide();

        greetMsg = findViewById(R.id.textView5);
        // Add Task Button (FAB Corner)
        addTaskButton = findViewById(R.id.addtask_button);


        /*

        // Temp Solution -> go to About Page
        aboutPageButton = findViewById(R.id.toAboutPage);

        // Temp Solution -> go to Completetask Page
        completetask = findViewById(R.id.toCompleteTask);

        // Temp Solution -> go to Rewards Page
        toRewards = findViewById(R.id.toRewards);

         */


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        // toolbar = findViewById(R.id.toolbar);

        UserDatabaseHelper userDatabaseHelper = new UserDatabaseHelper(MainActivity.this);
        // Toast.makeText(MainFragment.this, userDatabaseHelper.getUserName().toString(), Toast.LENGTH_SHORT).show();

        String name = userDatabaseHelper.getUserName().toString();
        greetMsg.setText("Hey " + name + "!");

//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) addTaskButton.getLayoutParams();
//        layoutParams.setAnchorId(R.id.linearLayout3);
//        addTaskButton.setLayoutParams(layoutParams);
    }

    private void displayData() {

        Cursor cursor = taskDB.getData();
        if (cursor.getCount()==0) {
            Toast.makeText(MainActivity.this, "No Tasks for Now", Toast.LENGTH_LONG).show();
            return;
        } else {
            while(cursor.moveToNext()){
                taskTitle.add(cursor.getString(1));
                taskPoints.add(cursor.getString(2));
                taskDesc.add(cursor.getString(3));
                // taskLat.add(cursor.getString(5));
                // taskLon.add(cursor.getString(6));
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
     }
     */
}