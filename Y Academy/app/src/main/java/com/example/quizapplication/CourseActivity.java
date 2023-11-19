package com.example.quizapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapplication.R;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        // Retrieve data from intent
        int numberOfClasses = getIntent().getIntExtra("numberOfClasses", -1);
        int numberOfHours = getIntent().getIntExtra("numberOfHours", -1);
        String about = getIntent().getStringExtra("about");
        String instructorName = getIntent().getStringExtra("instructorName");
        String difficultyLevel = getIntent().getStringExtra("difficultyLevel");
        int numberOfQuizzes = getIntent().getIntExtra("numberOfQuizzes", -1);

        String courseName = getIntent().getStringExtra("courseName");
        String category = getIntent().getStringExtra("category");
        double rate = getIntent().getDoubleExtra("rate", -1);
        double price = getIntent().getDoubleExtra("price", -1);


        TextView numberOfClassesTextView = findViewById(R.id.numberOfClass);
        TextView numberOfHoursTextView = findViewById(R.id.numberOfHours);
        TextView aboutTextView = findViewById(R.id.textViewDescription);
        TextView instructorNameTextView = findViewById(R.id.instructorNameId);
        TextView difficultyLevelTextView = findViewById(R.id.difficulityLevelId);
        TextView numberOfQuizzesTextView = findViewById(R.id.nbOfQuizezId);
        TextView courseNameTextView = findViewById(R.id.courseNameTextView);
        TextView categoryTextView = findViewById(R.id.textViewCategory);
        TextView rateTextView = findViewById(R.id.ratingTextView);
        TextView priceTextView = findViewById(R.id.priceTextView);
        TextView loginTextId = findViewById(R.id.loginTextId);


        numberOfClassesTextView.setText(String.valueOf(numberOfClasses));
        numberOfQuizzesTextView.setText(String.valueOf(numberOfQuizzes + "Quizes"));
        aboutTextView.setText(about);
        instructorNameTextView.setText(instructorName);
        difficultyLevelTextView.setText(difficultyLevel);
        courseNameTextView.setText(courseName);
        categoryTextView.setText(category);
        rateTextView.setText(String.valueOf(rate));
        priceTextView.setText(String.format("$ %.2f", price));
        int roundedPrice = (int) price;
        loginTextId.setText("Enroll Course - $" + roundedPrice);


        ImageView iconImageView = findViewById(R.id.iconImageView);
    }
}
