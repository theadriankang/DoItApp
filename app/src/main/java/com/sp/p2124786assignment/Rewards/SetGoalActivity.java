package com.sp.p2124786assignment.Rewards;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.sp.p2124786assignment.Database.GoalDBHelper;
import com.sp.p2124786assignment.Database.TaskDBHelper;
import com.sp.p2124786assignment.MainActivity;
import com.sp.p2124786assignment.Model.GoalModel;
import com.sp.p2124786assignment.Model.TaskModel;
import com.sp.p2124786assignment.R;
import com.sp.p2124786assignment.Tasks.AddTaskActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SetGoalActivity extends AppCompatActivity {

    private EditText goalName;
    private EditText pointsCostGoal;
    private ImageView goalUploadImage;
    private MaterialButton setGoalButton;

    GoalModel goalModel;


    public int PICK_IMAGE = 1;
    private Uri selectedImageUri = null;
    private Bitmap bitmap = null;
    private static final int RESULT_OK = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_goal);
        getSupportActionBar().hide();

        goalName = findViewById(R.id.goalName);
        pointsCostGoal = findViewById(R.id.pointsCostGoal);
        goalUploadImage = findViewById(R.id.goalUploadImage);
        setGoalButton = findViewById(R.id.setGoal_Button);


        goalUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Prompt the user to upload the image
                    chooseImage();
                }

            private void chooseImage() {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Choose Picture"), PICK_IMAGE);
            }


        });

        setGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String goal =  goalName.getText().toString();
                String point =  pointsCostGoal.getText().toString();


                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                if (bitmap != null) {

                    bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
                    byte[] bArray = bos.toByteArray();

                    if(bArray != null) {

                        if(!goal.equals("") && !point.equals("") ) {

                            GoalModel goalModel;

                            // Code to save the 3 inputs into SQL database
                            // taskDB = new TaskDBHelper(AddTaskActivity.this);
//                    int pointsReq = Integer.parseInt(inputNewPointsReq);
//                    taskDB.insertUserDetails(inputNewTaskName, pointsReq, inputNewTaskDesc);

                            try {
                                goalModel = new GoalModel(-1, goal, point, bitmap);
                            }
                            catch (Exception e)
                            {
                                Toast.makeText(SetGoalActivity.this, "There was an error\nPlease try again", Toast.LENGTH_SHORT).show();
                                goalModel = new GoalModel(-1, "error", "error", null);
                            }

                            GoalDBHelper goalDBHelper = new GoalDBHelper(SetGoalActivity.this);
                            boolean success = goalDBHelper.addOneData(goalModel);

                            // Wish user good luck in completing the added task in TOAST
                            // Centralise the toast message
                            /**
                             Toast toast = Toast.makeText(AddTaskActivity.this, "Task Added!\nYou can do it "  + name +"!", Toast.LENGTH_LONG);
                             TextView goodluckMsg = (TextView) toast.getView().findViewById(android.R.id.message);
                             if( goodluckMsg != null) goodluckMsg.setGravity(Gravity.CENTER);
                             toast.show();
                             */

                            // Go back to Main page
                            Intent intent = new Intent(SetGoalActivity.this, RewardActivity.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(getApplicationContext(), "Goal not saved\nPlease fill in every field", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Goal not saved\nImage is too large", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Goal not saved\nPlease put in an image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE) {
                selectedImageUri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                    bitmap = Bitmap.createScaledBitmap(bitmap,  600,392,true);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (selectedImageUri != null) {

                    goalUploadImage.setImageURI(selectedImageUri);
                    goalUploadImage.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(getApplicationContext(), "Photo is too large", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}