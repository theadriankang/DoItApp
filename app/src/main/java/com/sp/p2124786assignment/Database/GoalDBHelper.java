package com.sp.p2124786assignment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.sp.p2124786assignment.Model.GoalModel;
import com.sp.p2124786assignment.Model.TaskModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GoalDBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "goals.db";
    private static final int SCHEMA_VERSION = 1;

    private static final String TABLE_NAME = "goals";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_GOALNAME = "goal_name";
    private static final String COLUMN_GOALPOINTS = "goal_points";
    private static final String COLUMN_GOALPIC = "goal_pic";

    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInByte;

    public GoalDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_GOALNAME + " TEXT, " +
                COLUMN_GOALPOINTS + " INTEGER, " +
                COLUMN_GOALPIC + " BLOB)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean addOneData(GoalModel goalModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();



        Bitmap imageToStoreBitmap = goalModel.getGoalImage();

        byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        imageInByte = byteArrayOutputStream.toByteArray();

        cv.put(COLUMN_GOALNAME, goalModel.getGoalName());
        cv.put(COLUMN_GOALPOINTS, goalModel.getGoalPoints());
        cv.put(COLUMN_GOALPIC, imageInByte);


        long insert = db.insert(TABLE_NAME, null, cv);
        if (insert == -1)
            return false;
        else
            return true;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME, null);
        return cursor;
    }

}
