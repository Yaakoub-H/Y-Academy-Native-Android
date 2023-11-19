package com.example.quizapplication.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;

    int score = 0;
    int totalQuestion = 5;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    String[] questions;
    String[][] choices;
    String[] correctAnswers;
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backButton = findViewById(R.id.backButton);

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);

        backButton.setOnClickListener(view -> finish());
        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);

        totalQuestionsTextView.setText(getString(R.string.total_question, totalQuestion));
        String selectedLevel = getIntent().getStringExtra("LEVEL");
        if ("easy".equals(selectedLevel)) {
            questions = getResources().getStringArray(R.array.easy_questions);
            choices = new String[][]{
                    getResources().getStringArray(R.array.easy_choices_1),
                    getResources().getStringArray(R.array.easy_choices_2),
                    getResources().getStringArray(R.array.easy_choices_3),
                    getResources().getStringArray(R.array.easy_choices_4),
                    getResources().getStringArray(R.array.easy_choices_5)
            };
            correctAnswers = getResources().getStringArray(R.array.easy_correct_answers);
        } else if ("normal".equals(selectedLevel)) {
            questions = getResources().getStringArray(R.array.normal_questions);
            choices = new String[][]{
                    getResources().getStringArray(R.array.normal_choices_1),
                    getResources().getStringArray(R.array.normal_choices_2),
                    getResources().getStringArray(R.array.normal_choices_3),
                    getResources().getStringArray(R.array.normal_choices_4),
                    getResources().getStringArray(R.array.normal_choices_5)
            };
            correctAnswers = getResources().getStringArray(R.array.normal_correct_answers);
        } else if ("hard".equals(selectedLevel)) {
            questions = getResources().getStringArray(R.array.hard_questions);
            choices = new String[][]{
                    getResources().getStringArray(R.array.hard_choices_1),
                    getResources().getStringArray(R.array.hard_choices_2),
                    getResources().getStringArray(R.array.hard_choices_3),
                    getResources().getStringArray(R.array.hard_choices_4),
                    getResources().getStringArray(R.array.hard_choices_5)
            };
            correctAnswers = getResources().getStringArray(R.array.hard_correct_answers);
        }
        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        resetAnswerButtonBackgrounds();

        Button clickedButton = (Button) view;

        if (clickedButton.getId() != 0) {
            selectedAnswer = clickedButton.getText().toString();

            if (selectedAnswer.equals(correctAnswers[currentQuestionIndex])) {
                clickedButton.setBackgroundColor(Color.GREEN);
                score++;
            } else {
                clickedButton.setBackgroundColor(Color.RED);
            }

            disableAnswerButtons();
            view.postDelayed(() -> {
                currentQuestionIndex++;
                loadNewQuestion();
            }, 500);
        }
    }

    private void resetAnswerButtonBackgrounds() {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);
    }

    private void disableAnswerButtons() {
        ansA.setEnabled(false);
        ansB.setEnabled(false);
        ansC.setEnabled(false);
        ansD.setEnabled(false);
    }

    void loadNewQuestion() {
        if (currentQuestionIndex == totalQuestion) {
            finishQuiz();
            return;
        }
        selectedAnswer = "";
        resetAnswerButtonBackgrounds();
        ansA.setEnabled(true);
        ansB.setEnabled(true);
        ansC.setEnabled(true);
        ansD.setEnabled(true);
        questionTextView.setText(questions[currentQuestionIndex]);
        ansA.setText(choices[currentQuestionIndex][0]);
        ansB.setText(choices[currentQuestionIndex][1]);
        ansC.setText(choices[currentQuestionIndex][2]);
        ansD.setText(choices[currentQuestionIndex][3]);
    }

    void finishQuiz() {
        String passStatus = (score > totalQuestion * 0.60) ? getString(R.string.passed) : getString(R.string.failed);

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage(getString(R.string.score_message, score, totalQuestion))
                .setPositiveButton(getString(R.string.restart), (dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }

    void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }
}
