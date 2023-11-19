package com.example.quizapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapplication.R;

public class GetStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_start);


        RelativeLayout getStartedBtn = findViewById(R.id.getStartedBtnId);

        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start  new activity here
                Intent intent = new Intent(GetStartActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}