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

    public class LoginActivity extends AppCompatActivity {

        private EditText usernameEditText, passwordEditText;
        private RelativeLayout loginButton;

        private DatabaseHelper databaseHelper;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            TextView signInText = findViewById(R.id.signInText);

            usernameEditText = findViewById(R.id.editTextUsername);
            passwordEditText = findViewById(R.id.editTextPassword);
            loginButton = findViewById(R.id.getStartedLogInBtnId);

            databaseHelper = new DatabaseHelper(this);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   LogIn();
                }
            });

            signInText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(LoginActivity.this, SignInActivity.class);
                    startActivity(intent);
                }
            });


        }

        private void LogIn() {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Username and password are required", Toast.LENGTH_SHORT).show();
            } else {
                long userId = databaseHelper.getUserIdByUsername(username);
                Log.d("UserInfo", "User ID for username " + username + ": " + userId);

                if (userId >= 0) {
                    if (databaseHelper.verifyLoginCredentials(username, password)) {
                        saveSessionState(userId);
                        Intent intent = new Intent(LoginActivity.this, NavBarActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                }
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