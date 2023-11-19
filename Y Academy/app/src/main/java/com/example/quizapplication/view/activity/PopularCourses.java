package com.example.quizapplication.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapplication.R;
import com.example.quizapplication.adapter.AllPopularCoursesAdapter;
import com.example.quizapplication.model.AllPopularCoursesItem;
import com.example.quizapplication.model.Course;
import com.example.quizapplication.model.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class PopularCourses extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText searchEditText;
    private List<AllPopularCoursesItem> displayedCourses;
    private List<Course> popularCourses;
    private AllPopularCoursesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_courses);

        databaseHelper = new DatabaseHelper(this);

        LinearLayout popularLayout = findViewById(R.id.PopularLayout);
        for (int i = 0; i < popularLayout.getChildCount(); i++) {
            View view = popularLayout.getChildAt(i);
            if (view instanceof TextView) {
                final TextView popularTextView = (TextView) view;
                popularTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int j = 0; j < popularLayout.getChildCount(); j++) {
                            View otherView = popularLayout.getChildAt(j);
                            if (otherView instanceof TextView) {
                                TextView otherTextView = (TextView) otherView;
                                otherTextView.setBackgroundColor(Color.TRANSPARENT);
                                otherTextView.setTextColor(0xFFA0A4AB);
                            }
                        }
                        popularTextView.setBackgroundResource(R.drawable.round_button_background);
                        popularTextView.setTextColor(Color.WHITE);
                        displayedCourses.clear();
                        String selectedCategory = popularTextView.getText().toString();
                        List<Course> courses = databaseHelper.getPopularCoursesByCategory(selectedCategory);

                        displayedCourses.addAll(mapCoursesToItems(courses));
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch all Popular courses initially
        popularCourses = databaseHelper.getPopularCourses();
        displayedCourses = mapCoursesToItems(popularCourses);

        if (adapter == null) {
            adapter = new AllPopularCoursesAdapter(this, displayedCourses);
            recyclerView.setAdapter(adapter);
        }

        searchEditText = findViewById(R.id.searchPopularText);
        setupSearchEditText();


        TextView popularCourseText = findViewById(R.id.PopularCourseText);
        popularCourseText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    private void setupSearchEditText() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not used in this case
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Handle the search query text change
                searchCourses(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void searchCourses(String query) {
        displayedCourses.clear();

        if (query.isEmpty()) {
            displayedCourses.addAll(mapCoursesToItems(popularCourses));
        } else {
            List<Course> searchResults = databaseHelper.searchPopularCoursesByTitle(query);
            displayedCourses.addAll(mapCoursesToItems(searchResults));
        }

        adapter.notifyDataSetChanged();
    }

    private List<AllPopularCoursesItem> mapCoursesToItems(List<Course> courses) {
        List<AllPopularCoursesItem> mappedList = new ArrayList<>();
        for (Course course : courses) {
            mappedList.add(new AllPopularCoursesItem(
                    course.getCategory(),
                    R.drawable.ic_save, // Assuming a constant image resource for now
                    course.getCourseName(),
                    String.valueOf(course.getPrice()),
                    String.format("%.1f | %d Std", course.getRate(), course.getStudentCount())
            ));
        }
        return mappedList;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}
