package com.example.quizapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapplication.R;

public class LevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_activity);

        ImageButton backButton = findViewById(R.id.backButton);
        Button easyButton = findViewById(R.id.easy_btn);
        Button normalButton = findViewById(R.id.normal_btn);
        Button hardButton = findViewById(R.id.hard_btn);
        backButton.setOnClickListener(view -> finish());

        easyButton.setOnClickListener(view -> startQuiz("easy"));
        normalButton.setOnClickListener(view -> startQuiz("normal"));
        hardButton.setOnClickListener(view -> startQuiz("hard"));
    }

    private void startQuiz(String level) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("LEVEL", level);
        startActivity(intent);
    }
}
