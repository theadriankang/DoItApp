package com.sp.p2124786assignment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.sp.p2124786assignment.Model.UsersNameDatabaseModel;

/*
Data Types in SQLite
NULL - null value
INTEGER = signed integer, stored in 1,2,3,4,5,6 or 8 bytes
REAL - a floating point value
TEXT - text string
 */


public class UserDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "UserDetails.db";
    private static final int SCHEMA_VERSION = 1;

    private static final String TABLE_NAME = "user_details";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_RELATIONSHIP = "relationship";
    private static final String COLUMN_PIN = "PIN";

    public UserDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void insertUserDetails(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        //cv.put(COLUMN_RELATIONSHIP, relationship);
        // cv.put(COLUMN_PIN, pin);

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1) {
            Toast.makeText(context, "Retry Again", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Welcome to Do It!", Toast.LENGTH_SHORT).show();
        }
    }

    public UsersNameDatabaseModel getUserName() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null, null);

        if(cursor.moveToFirst()) {
            // oop through the cursor (result set) and create new customer objects. Put them into the return list
            do {
                //int userID = cursor.getInt(0);
                String userName = cursor.getString(1);
                //String userRelationship = cursor.getString(2);
                //int userPin = cursor.getInt(3);

                UsersNameDatabaseModel newUser = new UsersNameDatabaseModel(userName);
                return(newUser);
            } while (cursor.moveToNext()); // Proceed through the database one at a time
        }
        return null;
    }

    /*
    public UsersNameDatabaseModel getUserPin() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null, null);

        if(cursor.moveToFirst()) {
            // oop through the cursor (result set) and create new customer objects. Put them into the return list
            do {
                int userID = cursor.getInt(0);
                String userName = cursor.getString(1);
                String userRelationship = cursor.getString(2);
                int userPin = cursor.getInt(3);

                UsersNameDatabaseModel newUser = new UsersNameDatabaseModel(userID, userName, userRelationship, userPin);
                return(newUser);
            } while (cursor.moveToNext()); // Proceed through the database one at a time
        }
        return null;
    }*/














    public Cursor readName (String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, "ID = ?", new String[]{id}, null, null, null, null);
    }

    public void retriveUserDetails (String name, String relationship, String PIN) {

        // Open the database for reading
        SQLiteDatabase db = this.getReadableDatabase();

        /*
        // Define a projection that specifies which columns from the database you will actually use after this query
        String[] projection = {
                UserDatabaseHelper.COLUMN_ID,
                UserDatabaseHelper.COLUMN_NAME,
                UserDatabaseHelper.COLUMN_RELATIONSHIP,
                UserDatabaseHelper.COLUMN_PIN,
        };

        // Define a selection that specifies the rows that you want to retrieve
        String selection = UserDatabaseHelper.COLUMN_ID + " = ?";

        // Define a selection argument that specifies the id of the row that you want to retrieve
        String[] selectionArgs = { "1" };

        // Issue a query to the database and get a cursor back
        Cursor cursor = db.query(
                UserDatabaseHelper.TABLE_NAME,   // The table to query
                projection,              // The columns to return
                selection,               // The columns for the WHERE clause
                selectionArgs,           // The values for the WHERE clause
                null,                    // Don't group the rows
                null,                    // Don't filter by row groups
                null                     // Don't sort the rows
        );

         */
        String queryString = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                String userName = cursor.getString(0);
                //String userRelationship = cursor.getString(1);
                //String userPIN = cursor.getString(2);
            } while (cursor.moveToNext());

            return;

            /*
            int idColumnIndex = cursor.getColumnIndex(UserDatabaseHelper.COLUMN_ID);
            int nameColumnIndex = cursor.getColumnIndex(UserDatabaseHelper.COLUMN_NAME);
            int relationshipColumnIndex = cursor.getColumnIndex(UserDatabaseHelper.COLUMN_RELATIONSHIP);
            int PINColumnIndex = cursor.getColumnIndex(UserDatabaseHelper.COLUMN_PIN);

            int id = cursor.getInt(idColumnIndex);
            name = cursor.getString(nameColumnIndex);
            relationship = cursor.getString(relationshipColumnIndex);
            PIN = cursor.getString(PINColumnIndex); */

            // Do something with the values here, for example, display them as text to the user
            // public final void setText (name, TextView.BufferType type)
        }

        // Close the cursor and the database when you are done with them
        cursor.close();
        db.close();
    }
}

