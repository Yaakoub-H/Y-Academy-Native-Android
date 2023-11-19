package com.example.quizapplication.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapplication.R;
import com.example.quizapplication.model.DatabaseHelper;
import com.example.quizapplication.model.User;

public class SignInActivity extends AppCompatActivity {

        private EditText usernameEditText;
        private EditText passwordEditText;
        private RelativeLayout signUpButton;

        private DatabaseHelper databaseHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign_in);

            databaseHelper = new DatabaseHelper(this);

            usernameEditText = findViewById(R.id.usernameTextView);
            passwordEditText = findViewById(R.id.passwordId);
            signUpButton = findViewById(R.id.getStartedSignUpBtnId);
            TextView logInText = findViewById(R.id.logInTextID);

            signUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signUp();
                }
            });
            logInText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SignInActivity.this, LoginActivity.class));
                }
            });
        }

    private void signUp() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (!username.isEmpty() && !password.isEmpty()) {
            if (databaseHelper.checkUserExists(username)) {
                // User already exists
                Toast.makeText(SignInActivity.this, "Username already exists. Please choose a different one.", Toast.LENGTH_SHORT).show();
            } else {
                // Create a new user object
                User newUser = new User(0, username, password);
                long userId = databaseHelper.addUser(newUser);

                // Add a log statement to print the user ID
                Log.d("UserInfo", "User ID for new user " + username + ": " + userId);

                if (userId != -1) {
                    saveSessionState(userId);
                    startActivity(new Intent(SignInActivity.this, NavBarActivity.class));
                    finish();
                } else {
                    Toast.makeText(SignInActivity.this, "Error creating user. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(SignInActivity.this, "Username and Passwords are required!", Toast.LENGTH_SHORT).show();
        }
    }


    private void saveSessionState(long userId) {
        SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Set isLoggedIn to true
        editor.putBoolean("isLoggedIn", true);

        // Save the current timestamp
        long timestamp = System.currentTimeMillis();
        editor.putLong("timestamp", timestamp);

        // Save the user ID
        editor.putLong("loggedInUserId", userId);

        // Apply changes
        editor.apply();
    }

}