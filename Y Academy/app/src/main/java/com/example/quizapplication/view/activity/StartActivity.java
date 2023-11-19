package com.example.quizapplication.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapplication.R;

import java.util.Locale;

public class StartActivity extends AppCompatActivity {
    Spinner spinner;
    public static final String[] languages = {"Select Language", "English", "Arabic"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        spinner = findViewById(R.id.select_lang);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectLang = parentView.getItemAtPosition(position).toString();
                if (selectLang.equals("English")) {
                    setLocal(StartActivity.this, "en");
                    finish();
                    startActivity(getIntent());
                } else if (selectLang.equals("Arabic")) {
                    setLocal(StartActivity.this, "ar");
                    Intent intent = new Intent(StartActivity.this, StartActivity.class);
                    intent.putExtra("LANGUAGE", "ar");
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        Button playButton = findViewById(R.id.play_btn);
        Button startButton = findViewById(R.id.startBtn);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LevelActivity.class);
                startActivity(intent);
            }
        });
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, GetStartActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setLocal(Activity activity, String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}
