package com.sp.p2124786assignment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import com.sp.p2124786assignment.Model.PhotoModel;

import java.io.ByteArrayOutputStream;

public class PhotoDBHelper extends SQLiteOpenHelper {

    Context context;

    private static String DB_NAME = "photo.db";
    private static int DB_VERSION = 1;

    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInByte;


    private static String createTableQuery = "Create table UserPhoto(image BLOB)";

    public PhotoDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void storeData(PhotoModel photoModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap imageToStoreBitmap = photoModel.getPhoto();

        byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        imageInByte = byteArrayOutputStream.toByteArray();

        ContentValues contentValues = new ContentValues();
        contentValues.put("photo", imageInByte);

    }
}
