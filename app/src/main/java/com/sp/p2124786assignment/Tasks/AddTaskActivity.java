package com.sp.p2124786assignment.Tasks;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.sp.p2124786assignment.Database.TaskDBHelper;
import com.sp.p2124786assignment.Database.UserDatabaseHelper;
import com.sp.p2124786assignment.Location.GPSTracker;
import com.sp.p2124786assignment.Location.MapsActivity;
import com.sp.p2124786assignment.MainActivity;
import com.sp.p2124786assignment.Model.TaskModel;
import com.sp.p2124786assignment.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddTaskActivity extends AppCompatActivity {

    private EditText taskName;
    private EditText pointsReq;
    private EditText taskDesc;
    private TextView locationTV;
    private ImageView getCoordinates_Button;
    private MaterialButton saveNewTask;
    private MaterialButton accessMaps;

    private ImageView uploadTaskPic;

    private TextView location = null;
    private GPSTracker gpsTracker;
    private double mylat = 0.0d;
    private double mylon = 0.0d;
    private double lati = 0.0d;
    private double longi = 0.0d;

    private double lat = 0.0d;
    private double lon = 0.0d;

    TaskDBHelper taskDB;

    private Context mContext;

    public int PICK_IMAGE = 1;
    private Uri selectedImageUri = null;
    private Bitmap bitmap = null;
    private static final int RESULT_OK = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        getSupportActionBar().hide();

        taskName = findViewById(R.id.taskName);
        pointsReq = findViewById(R.id.pointsReq);
        taskDesc = findViewById(R.id.taskDesc);
        locationTV = findViewById(R.id.location);
        gpsTracker = new GPSTracker(AddTaskActivity.this);

        saveNewTask = findViewById(R.id.saveNewTasks);
        accessMaps = findViewById(R.id.accessMaps);
        uploadTaskPic = findViewById(R.id.uploadTaskPic);
        getCoordinates_Button = findViewById(R.id.getCoordinates_Button);

        taskDB = new TaskDBHelper(AddTaskActivity.this);

        UserDatabaseHelper userDatabaseHelper = new UserDatabaseHelper(AddTaskActivity.this);
        String name = userDatabaseHelper.getUserName().toString();

        // Button to trigger Google Maps and get Decimal Coordinates
        getCoordinates_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Coordinates from Maps
                if(gpsTracker.canGetLocation()) {
                    lat = gpsTracker.getLatitude();
                    lon = gpsTracker.getLongitude();
                    locationTV.setText(String.valueOf(lat) + ", " + String.valueOf(lon));
                } else {
                    locationTV.setText("Unable to get location");
                }
            }
        });

        // See Maps
        accessMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mylat = gpsTracker.getLatitude();
                mylon = gpsTracker.getLongitude();


                Intent intent = new Intent (AddTaskActivity.this, MapsActivity.class);

                intent.putExtra("LATITUDE", lati);
                intent.putExtra("LONGITUDE", longi);
                intent.putExtra("MYLATITUDE", mylat);
                intent.putExtra("MYLONGITUDE", mylon);
                startActivity(intent);
            }
        });




        // Button to Save Task Details
        saveNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputNewTaskName = taskName.getText().toString();
                String inputNewPointsReq = pointsReq.getText().toString();
                String inputNewTaskDesc = taskDesc.getText().toString();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                if (bitmap != null)
                {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
                    byte[] bArray = bos.toByteArray();


                        if(inputNewTaskName.isEmpty() || inputNewPointsReq.isEmpty() || inputNewTaskDesc.isEmpty() || bos.size() <= 0) {
                            Toast.makeText(AddTaskActivity.this, "Please complete all the blanks.", Toast.LENGTH_LONG).show();
                        } else {

                            TaskModel taskModel;

                            // Code to save the 3 inputs into SQL database
                            // taskDB = new TaskDBHelper(AddTaskActivity.this);
//                    int pointsReq = Integer.parseInt(inputNewPointsReq);
//                    taskDB.insertUserDetails(inputNewTaskName, pointsReq, inputNewTaskDesc);

                            try {
                                taskModel = new TaskModel(-1, inputNewTaskName, inputNewPointsReq, inputNewTaskDesc, bitmap, lat, lon);
                            }
                            catch (Exception e)
                            {
                                Toast.makeText(AddTaskActivity.this, "There was an error\nPlease try again", Toast.LENGTH_SHORT).show();
                                taskModel = new TaskModel(-1, "error", "error", "error", null, 0, 0);
                            }

                            TaskDBHelper taskDBHelper = new TaskDBHelper(AddTaskActivity.this);
                            boolean success = taskDBHelper.addOneData(taskModel);

                            // Wish user good luck in completing the added task in TOAST
                            // Centralise the toast message

                            /*
                             Toast toast = Toast.makeText(AddTaskActivity.this, "Task Added!\nYou can do it "  + name +"!", Toast.LENGTH_LONG);
                             TextView goodluckMsg = (TextView) toast.getView().findViewById(android.R.id.message);
                             if( goodluckMsg != null) goodluckMsg.setGravity(Gravity.CENTER);
                             toast.show();

                             */

                            Toast.makeText(AddTaskActivity.this, "Task Added!\nYou can do it " + name + "!", Toast.LENGTH_LONG).show();


                            // Go back to Main page
                            Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                }



            }
        });

        uploadTaskPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        gpsTracker.stopUsingGPS();
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Choose Picture"), PICK_IMAGE);
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

                    uploadTaskPic.setImageURI(selectedImageUri);

                }else{
                    Toast.makeText(mContext, "File size of photo is too large!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


}