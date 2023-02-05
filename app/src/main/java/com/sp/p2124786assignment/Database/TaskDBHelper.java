package com.sp.p2124786assignment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.sp.p2124786assignment.Model.TaskModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TaskDBHelper extends SQLiteOpenHelper {


    private Context context;
    private static final String DATABASE_NAME = "tasks.db";
    private static final int SCHEMA_VERSION = 1;

    private static final String TABLE_NAME = "tasks";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TASKNAME = "task_name";
    private static final String COLUMN_TASKPOINTS = "task_points";
    private static final String COLUMN_TASKDESC = "task_description";
    private static final String COLUMN_TASKPIC = "COLUMN_TASKPIC";
    private static final String COLUMN_LAT = "lat";
    private static final String COLUMN_LON = "lon";

    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInByte;
    private Double lon;
    private Double lat;

    public TaskDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASKNAME + " TEXT, " +
                COLUMN_TASKPOINTS + " INTEGER, " +
                COLUMN_TASKDESC + " TEXT, " +
                COLUMN_LAT + " REAL," +
                COLUMN_LON + " REAL," +
                COLUMN_TASKPIC + " BLOB)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean addOneData(TaskModel taskModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        Bitmap imageToStoreBitmap = taskModel.getTaskPic();

        byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        imageInByte = byteArrayOutputStream.toByteArray();

        cv.put(COLUMN_TASKNAME, taskModel.getTaskName());
        cv.put(COLUMN_TASKPOINTS, taskModel.getTaskPoints());
        cv.put(COLUMN_TASKDESC, taskModel.getTaskDesc());
        cv.put(COLUMN_TASKPIC, imageInByte);
        cv.put(COLUMN_LAT,lat);
        cv.put(COLUMN_LON, lon);

        long insert = db.insert(TABLE_NAME, null, cv);
        if (insert == -1)
            return false;
        else
            return true;
    }

    public List<TaskModel> getEveryTaskData()
    {
        List<TaskModel> returnList = new ArrayList<>();

        // get data from the database
        String queryString = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst())
        {
            do
            {
                int taskID = cursor.getInt(0);
                String taskName = cursor.getString(1);
                String taskPoints = cursor.getString(2);
                String taskDesc = cursor.getString(3);
                double taskLon = cursor.getDouble(5);
                double taskLat = cursor.getDouble(6);


                TaskModel newData = new TaskModel(taskID, taskName, taskPoints, taskDesc, null, taskLon, taskLat);
                returnList.add(newData);
            } while (cursor.moveToNext());
        }
        else
        {
            // failure. do not add anything to the list.
        }
        // close both the cursor and the db when done.
        cursor.close();
        db.close();
        return returnList;
    }

    public void insertUserDetails(String task_name, int task_points, String task_desc) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TASKNAME, task_name);
        cv.put(COLUMN_TASKPOINTS, task_points);
        cv.put(COLUMN_TASKDESC, task_desc);
        cv.put(COLUMN_LAT, lat);
        cv.put(COLUMN_LON, lon);

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1) {
            Toast.makeText(context, "Failed to add task", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Task Added!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME, null);
        return cursor;
    }
}
