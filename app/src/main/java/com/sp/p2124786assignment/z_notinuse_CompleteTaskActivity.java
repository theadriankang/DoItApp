/* Activity Not in use.
Purpose of activity was to allow user to upload a picture
and record his/her location, after
- completing the task IRL,
- opening the task in the app
- click on complete task

 */






//package com.sp.p2124786assignment;

//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.material.button.MaterialButton;
//import com.sp.p2124786assignment.Database.PhotoDBHelper;
//import com.sp.p2124786assignment.Location.TaskLocation;
//import com.sp.p2124786assignment.Model.PhotoModel;
//
//import java.io.IOException;
/*
public class CompleteTaskActivity extends AppCompatActivity {

    private ImageView uploadTaskPic;
    private TextView location;
    private TextView completeTask_taskName;
    private MaterialButton finalcompleteTaskButton;
    private MaterialButton getLocationButton;

    private TaskLocation gpsTracker;
    private double latitude = 0.0d;
    private double longitude = 0.0d;
    private double myLatitude = 0.0d;
    private double myLongitude = 0.0d;

    public int PICK_IMAGE = 1;
    private Uri selectedImageUri = null;
    private Bitmap bitmap = null;
    private static final int RESULT_OK = -1;

    PhotoDBHelper photoDBHelper;

    private Context mContext;
    private Intent intent;

    private String completeTask_TaskName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_task);
        getSupportActionBar().hide();

        photoDBHelper = new PhotoDBHelper(this);

        uploadTaskPic = findViewById(R.id.uploadTaskPic);
        completeTask_taskName = findViewById(R.id.completeTask_taskName);
        finalcompleteTaskButton = findViewById(R.id.finalcompleteTask_button);
        getLocationButton = findViewById(R.id.getLocationButton);

        location = findViewById(R.id.coordinatesLocation);
        gpsTracker = new TaskLocation(CompleteTaskActivity.this);

        intent = getIntent();

        getTransferredData();

        setDataAsText();




        // Tap on picture to upload picture of task
        uploadTaskPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // upload the pic
                chooseImage();
            }
        });*/

        /*
        // Get Location - Exact Coordinates
        getLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayCoordinates();
            }
        });

         */




        // Show page completed task activity

//        finalcompleteTaskButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (uploadTaskPic == null) {
//                    Toast.makeText(CompleteTaskActivity.this, "Please upload a picture of the completed task", Toast.LENGTH_LONG).show();
//                } else {
//                    // Save the picture to SQL
//
//
//                    // Go to Completed Activity
//                    Intent intent = new Intent(CompleteTaskActivity.this, CompletedTaskActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });
//
//
//    }

    /*

    private void displayCoordinates() {
        Cursor c = helper.getById(getTaskId());
        c.moveToFirst();


        latitude = helper.getLatitude(c);
        longitude = helper.getLongitude(c);
        location.setText(String.valueOf(latitude) + ", " + String.valueOf(longitude));
    }

     */
//    private void chooseImage() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Choose Picture"), PICK_IMAGE);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            if (requestCode == PICK_IMAGE) {
//                selectedImageUri = data.getData();
//                try {
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
//                    bitmap = Bitmap.createScaledBitmap(bitmap,  600,392,true);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                if (selectedImageUri != null) {
//
//                    uploadTaskPic.setImageURI(selectedImageUri);
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(CompleteTaskActivity.this);
//                    builder.setTitle("Do It");
//                    builder.setMessage("Would you like to save the uploaded image?");
//                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {@Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                    });
//                    builder.setPositiveButton("Upload Photo", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            storeImage();
//                        }
//                    });
//                    builder.create().show();
//                }else{
//                    Toast.makeText(mContext, "photo is too large", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }
//
//    private void storeImage() {
//        photoDBHelper.storeData(new PhotoModel(bitmap));
//    }
//
//    private void setDataAsText() {
//        completeTask_taskName.setText(completeTask_TaskName);
//    }
//
//    private void getTransferredData() {
//        completeTask_TaskName = intent.getStringExtra("Task Name");
//    }
//}
//*/