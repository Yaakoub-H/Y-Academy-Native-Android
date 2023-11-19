package com.example.quizapplication.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapplication.R;
import com.example.quizapplication.adapter.AllPopularCoursesAdapter;
import com.example.quizapplication.model.AllPopularCoursesItem;
import com.example.quizapplication.model.Course;
import com.example.quizapplication.model.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class CoursesFragment extends Fragment {

    private List<Course> allCourses;
    private List<AllPopularCoursesItem> displayedCourses;
    private DatabaseHelper dbHelper;
    private AllPopularCoursesAdapter adapter;
    private EditText searchEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        dbHelper = new DatabaseHelper(requireContext());

        allCourses = new ArrayList<>();
        displayedCourses = new ArrayList<>();

        allCourses = dbHelper.getAllCourses();
        displayedCourses.addAll(mapCoursesToItems(allCourses));

        RecyclerView recyclerView = view.findViewById(R.id.CoursesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new AllPopularCoursesAdapter(requireContext(), displayedCourses);
        recyclerView.setAdapter(adapter);

        LinearLayout coursesLayout = view.findViewById(R.id.CoursesCategoryLayout);
        for (int i = 0; i < coursesLayout.getChildCount(); i++) {
            View childView = coursesLayout.getChildAt(i);
            if (childView instanceof TextView) {
                final TextView categoryTextView = (TextView) childView;
                categoryTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int j = 0; j < coursesLayout.getChildCount(); j++) {
                            View otherView = coursesLayout.getChildAt(j);
                            if (otherView instanceof TextView) {
                                TextView otherTextView = (TextView) otherView;
                                otherTextView.setBackgroundColor(Color.TRANSPARENT);
                                otherTextView.setTextColor(0xFFA0A4AB);
                            }
                        }
                        categoryTextView.setBackgroundResource(R.drawable.round_button_background);
                        categoryTextView.setTextColor(Color.WHITE);

                        displayedCourses.clear();
                        String selectedCategory = categoryTextView.getText().toString();
                        List<Course> courses = dbHelper.getCoursesByCategory(selectedCategory);

                        displayedCourses.addAll(mapCoursesToItems(courses));

                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }

        searchEditText = view.findViewById(R.id.searchEditText);
        setupSearchEditText();
        return view;
    }

    private void setupSearchEditText() {
        // Set a listener for the search EditText
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchCourses(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void searchCourses(String query) {
        // Clear existing courses and fetch courses based on the search query
        displayedCourses.clear();

        // If the search query is empty, show all courses
        if (query.isEmpty()) {
            displayedCourses.addAll(mapCoursesToItems(allCourses));
        } else {
            // Fetch courses from the database based on the search query
            List<Course> searchResults = dbHelper.searchCoursesByTitle(query);
            displayedCourses.addAll(mapCoursesToItems(searchResults));
        }

        // Notify the adapter that the dataset has changed
        adapter.notifyDataSetChanged();
    }


    private List<AllPopularCoursesItem> mapCoursesToItems(List<Course> courses) {
        List<AllPopularCoursesItem> mappedList = new ArrayList<>();
        for (Course course : courses) {
            mappedList.add(new AllPopularCoursesItem(
                    course.getCategory(),
                    R.drawable.ic_save,
                    course.getCourseName(),
                    String.valueOf(course.getPrice()),
                    String.format("%.1f | %d Std", course.getRate(), course.getStudentCount())
            ));
        }
        return mappedList;
    }
}
