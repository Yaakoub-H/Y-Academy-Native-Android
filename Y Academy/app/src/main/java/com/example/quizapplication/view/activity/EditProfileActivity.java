package com.example.quizapplication.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.quizapplication.R;
import com.example.quizapplication.model.DatabaseHelper;

public class EditProfileActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText genderEditText;
    private EditText emailEditText;
    private EditText nickNameEditText;
    private RelativeLayout updateProfileButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        databaseHelper = new DatabaseHelper(this);

        usernameEditText = findViewById(R.id.editTextUsername);
        genderEditText = findViewById(R.id.genderTextId);
        emailEditText = findViewById(R.id.EmailTextID);
        nickNameEditText = findViewById(R.id.nickNameTextId);
        updateProfileButton = findViewById(R.id.updateProfileIdButton);

        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
    }

    private void updateProfile() {
        String username = usernameEditText.getText().toString();
        String gender = genderEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String nickName = nickNameEditText.getText().toString();

        boolean isUpdated = databaseHelper.updateUserData(getLoggedInUserId(), username, gender, email, nickName);

        if (isUpdated) {
            Toast.makeText(EditProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(EditProfileActivity.this, "Error updating profile. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private long getLoggedInUserId() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        return preferences.getLong("loggedInUserId", -1);
    }
}