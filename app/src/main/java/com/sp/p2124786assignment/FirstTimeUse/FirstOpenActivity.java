package com.sp.p2124786assignment.FirstTimeUse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.sp.p2124786assignment.Database.UserDatabaseHelper;
import com.sp.p2124786assignment.MainActivity;
import com.sp.p2124786assignment.R;

public class FirstOpenActivity extends AppCompatActivity {

    public static String PREFS_NAME = "MyPrefsFile";

    private MaterialButton saveName;
    private RadioGroup relationshipType;
    private UserDatabaseHelper userHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask_name);
        getSupportActionBar().hide();

        final EditText Name = (EditText) findViewById(R.id.Name);
        // final EditText PIN = (EditText) findViewById(R.id.PIN);
        // relationshipType = findViewById(R.id.Relationship_RadioGroup);
        //userHelper = new UserDatabaseHelper(this);

        saveName = findViewById(R.id.saveNewRewards);
        saveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString().trim();
                /*
                String stringPIN = PIN.getText().toString();
                int pin = Integer.parseInt(stringPIN);
                String relationship = " ";
                switch (relationshipType.getCheckedRadioButtonId()) {
                    case R.id.mother:
                        relationship = "mum";
                        break;
                    case R.id.father:
                        relationship = "dad";
                        break;
                    case R.id.sibling:
                        relationship = "sibling";
                        break;
                    case R.id.guardian:
                        relationship = "guardian";
                        break;
                }

                if(name.isEmpty() || stringPIN.length() < 4 || relationship == " ")

                 */

                // If user has not entered a name in the field,
                // it should show a Toast Message asking it to enter a name
                if(name.isEmpty()) {
                    Toast.makeText(FirstOpenActivity.this, "Please enter your name.", Toast.LENGTH_LONG).show();
                } else {
                    // Indicate and remember that user has entered their name before
                    // No need to re-enter their name
                    SharedPreferences sharedPreferences = getSharedPreferences(FirstOpenActivity.PREFS_NAME, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("hasLoggedBefore", true);
                    editor.commit();

                    // Store User's Name to SQL DB -> UserDatabaseHelper
                    UserDatabaseHelper userDB = new UserDatabaseHelper(FirstOpenActivity.this);
                    userDB.insertUserDetails(name);

                    // Go to Main Activity
                    Intent intent = new Intent(FirstOpenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        });

    }
}