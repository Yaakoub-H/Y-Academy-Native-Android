package com.example.quizapplication.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.quizapplication.R;
import com.example.quizapplication.model.Course;
import com.example.quizapplication.model.DatabaseHelper;
import com.example.quizapplication.model.ExtendedCourse;
import com.example.quizapplication.view.fragment.CoursesFragment;
import com.example.quizapplication.view.fragment.HomeFragment;
import com.example.quizapplication.view.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class NavBarActivity extends AppCompatActivity {

    private static final int SESSION_CHECK_INTERVAL = 5000; // 5 seconds, adjust as needed
    private Handler sessionCheckHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);
        checkSessionState();


//        List<Course> courseList = new ArrayList<>();
//        courseList.add(new Course(0, "Mastering 3D Design Basics", "3D Design", 19.99, 4.5, 12000, true));
//        courseList.add(new Course(1, "Pro Tips for Graphic Designers", "Graphic Design", 24.99, 4.2, 10000, false));
//        courseList.add(new Course(2,  "Web Development Essentials", "Web Development", 21.99, 4.8, 15000, true));
//        courseList.add(new Course(3, "SEO & Marketing Strategies", "SEO & Marketing", 18.99, 4.7, 13000, true));
//        courseList.add(new Course(4, "Finance & Accounting Principles", "Finance & Accounting", 23.99, 4.6, 14000, false));
//
//        courseList.add(new Course(5,  "Personal Development Journey", "Personal Development", 19.99, 4.5, 12000, true));
//        courseList.add(new Course(6,  "Office Productivity Hacks", "Office Productivity", 24.99, 4.2, 10000, false));
//        courseList.add(new Course(7,  "Strategic HR Management", "HR Management", 21.99, 4.8, 15000, true));
//        courseList.add(new Course(8,  "3D Design for Beginners", "3D Design", 18.99, 4.7, 13000, true));
//        courseList.add(new Course(9, "Digital Marketing Mastery", "SEO & Marketing", 23.99, 4.6, 14000, false));
//
//        courseList.add(new Course(10, "Advanced Finance Strategies", "Finance & Accounting", 19.99, 4.5, 12000, true));
//        courseList.add(new Course(11,  "Personal Growth Techniques", "Personal Development", 24.99, 4.2, 10000, false));
//        courseList.add(new Course(12,  "Effective Office Communication", "Office Productivity", 21.99, 4.8, 15000, true));
//        courseList.add(new Course(13,  "Modern HR Practices", "HR Management", 18.99, 4.7, 13000, true));
//        courseList.add(new Course(14,  "3D Design Masterclass", "3D Design", 23.99, 4.6, 14000, false));
//
//        courseList.add(new Course(15,  "Graphic Design Fundamentals", "Graphic Design", 19.99, 4.5, 12000, true));
//        courseList.add(new Course(16,  "Web Development for Beginners", "Web Development", 24.99, 4.2, 10000, false));
//        courseList.add(new Course(17, "SEO Secrets Revealed", "SEO & Marketing", 21.99, 4.8, 15000, true));
//        courseList.add(new Course(18,  "Accounting Basics", "Finance & Accounting", 18.99, 4.7, 13000, true));
//        courseList.add(new Course(19,  "Personal Effectiveness Workshop", "Personal Development", 23.99, 4.6, 14000, false));
//
//        courseList.add(new Course(20,  "Office Productivity Bootcamp", "Office Productivity", 19.99, 4.5, 12000, true));
//        courseList.add(new Course(21,  "Strategic HR Leadership", "HR Management", 24.99, 4.2, 10000, false));
//        courseList.add(new Course(22, "Advanced 3D Design Techniques", "3D Design", 21.99, 4.8, 15000, true));
//        courseList.add(new Course(23, "Digital Marketing Trends", "SEO & Marketing", 18.99, 4.7, 13000, true));
//        courseList.add(new Course(24,  "Finance & Accounting Mastery", "Finance & Accounting", 23.99, 4.6, 14000, false));
//
//        courseList.add(new Course(25, "Personal Development Strategies", "Personal Development", 19.99, 4.5, 12000, true));
//        courseList.add(new Course(26, "Effective Office Collaboration", "Office Productivity", 24.99, 4.2, 10000, false));
//        courseList.add(new Course(27, "HR Management Essentials", "HR Management", 21.99, 4.8, 15000, true));
//        courseList.add(new Course(28,  "3D Design Principles", "3D Design", 18.99, 4.7, 13000, true));
//        courseList.add(new Course(29,  "Advanced SEO Strategies", "SEO & Marketing", 23.99, 4.6, 14000, false));
//
//        courseList.add(new Course(30, "Finance & Accounting Fundamentals", "Finance & Accounting", 19.99, 4.5, 12000, true));
//        courseList.add(new Course(31,  "Personal Development Workshop", "Personal Development", 24.99, 4.2, 10000, false));
//        courseList.add(new Course(32,  "Office Productivity Essentials", "Office Productivity", 21.99, 4.8, 15000, true));
//        courseList.add(new Course(33,  "Modern HR Strategies", "HR Management", 18.99, 4.7, 13000, true));
//        courseList.add(new Course(34, "Graphic Design Principles", "Graphic Design", 23.99, 4.6, 14000, false));
//        courseList.add(new Course(35, "Web Development Masterclass", "Web Development", 19.99, 4.5, 12000, true));
//        courseList.add(new Course(36, "Advanced SEO Techniques", "SEO & Marketing", 24.99, 4.2, 10000, false));
//        courseList.add(new Course(37, "Finance & Accounting for Professionals", "Finance & Accounting", 21.99, 4.8, 15000, true));
//        courseList.add(new Course(38, "Personal Development for Success", "Personal Development", 18.99, 4.7, 13000, true));
//        courseList.add(new Course(39, "Office Productivity Mastery", "Office Productivity", 23.99, 4.6, 14000, false));
//
//        courseList.add(new Course(40, "Strategic HR Planning", "HR Management", 19.99, 4.5, 12000, true));
//        courseList.add(new Course(41, "Digital Marketing Essentials", "SEO & Marketing", 24.99, 4.2, 10000, false));
//        courseList.add(new Course(42, "Graphic Design Techniques", "Graphic Design", 21.99, 4.8, 15000, true));
//        courseList.add(new Course(43, "Advanced Web Development", "Web Development", 18.99, 4.7, 13000, true));
//        courseList.add(new Course(44, "Effective SEO Strategies", "SEO & Marketing", 23.99, 4.6, 14000, false));
//
//        courseList.add(new Course(45, "Finance & Accounting Principles II", "Finance & Accounting", 19.99, 4.5, 12000, true));
//        courseList.add(new Course(46, "Personal Development Masterclass", "Personal Development", 24.99, 4.2, 10000, false));
//        courseList.add(new Course(47, "Office Productivity Techniques", "Office Productivity", 21.99, 4.8, 15000, true));
//        courseList.add(new Course(48, "Modern HR Leadership", "HR Management", 18.99, 4.7, 13000, true));
//        courseList.add(new Course(49, "Advanced Graphic Design Principles", "Graphic Design", 23.99, 4.6, 14000, false));
//        courseList.add(new Course(50, "Web Development Fundamentals", "Web Development", 19.99, 4.5, 12000, true));
//        DatabaseHelper dbHelper = new DatabaseHelper(this);
//        dbHelper.addCourses(courseList);

//        List<ExtendedCourse> extendedCourseList = new ArrayList<>();
//
//// Course 1
//        extendedCourseList.add(new ExtendedCourse(1, 12, 24, "Mastering 3D Design Basics", "John Doe", "Intermediate", 5));
//
//// Course 2
//        extendedCourseList.add(new ExtendedCourse(2, 10, 20, "Pro Tips for Graphic Designers", "Jane Smith", "Beginner", 3));
//
//// Course 3
//        extendedCourseList.add(new ExtendedCourse(3, 15, 30, "Web Development Essentials", "Michael Johnson", "Advanced", 8));
//
//// Course 4
//        extendedCourseList.add(new ExtendedCourse(4, 14, 28, "SEO & Marketing Strategies", "Emily Davis", "Intermediate", 7));
//
//// Course 5
//        extendedCourseList.add(new ExtendedCourse(5, 16, 32, "Finance & Accounting Principles", "Robert White", "Advanced", 6));
//
//// Course 6
//        extendedCourseList.add(new ExtendedCourse(6, 12, 24, "Personal Development Journey", "Sophia Wilson", "Beginner", 4));
//
//// Course 7
//        extendedCourseList.add(new ExtendedCourse(7, 10, 20, "Office Productivity Hacks", "David Brown", "Intermediate", 3));
//
//// Course 8
//        extendedCourseList.add(new ExtendedCourse(8, 15, 30, "Strategic HR Management", "Olivia Miller", "Advanced", 8));
//
//// Course 9
//        extendedCourseList.add(new ExtendedCourse(9, 14, 28, "3D Design for Beginners", "Daniel Taylor", "Beginner", 7));
//
//// Course 10
//        extendedCourseList.add(new ExtendedCourse(10, 16, 32, "Digital Marketing Mastery", "Emma Anderson", "Intermediate", 6));
//
//// Course 11
//        extendedCourseList.add(new ExtendedCourse(11, 12, 24, "Advanced Finance Strategies", "William Harris", "Advanced", 5));
//
//// Course 12
//        extendedCourseList.add(new ExtendedCourse(12, 10, 20, "Personal Growth Techniques", "Lily Martinez", "Beginner", 3));
//
//// Course 13
//        extendedCourseList.add(new ExtendedCourse(13, 15, 30, "Effective Office Communication", "Ethan Turner", "Intermediate", 8));
//
//// Course 14
//        extendedCourseList.add(new ExtendedCourse(14, 14, 28, "Modern HR Practices", "Ava King", "Advanced", 7));
//
//// Course 15
//        extendedCourseList.add(new ExtendedCourse(15, 16, 32, "3D Design Masterclass", "Noah Brooks", "Intermediate", 6));
//
//// Course 16
//        extendedCourseList.add(new ExtendedCourse(16, 12, 24, "Graphic Design Fundamentals", "Mia Collins", "Beginner", 4));
//
//// Course 17
//        extendedCourseList.add(new ExtendedCourse(17, 10, 20, "Web Development for Beginners", "James Lee", "Intermediate", 3));
//
//// Course 18
//        extendedCourseList.add(new ExtendedCourse(18, 15, 30, "SEO Secrets Revealed", "Sophie Hall", "Advanced", 8));
//
//// Course 19
//        extendedCourseList.add(new ExtendedCourse(19, 14, 28, "Accounting Basics", "Matthew White", "Intermediate", 7));
//
//// Course 20
//        extendedCourseList.add(new ExtendedCourse(20, 16, 32, "Personal Effectiveness Workshop", "Ella Turner", "Beginner", 6));
//
//// Course 21
//        extendedCourseList.add(new ExtendedCourse(21, 12, 24, "Office Productivity Bootcamp", "Jack Wilson", "Advanced", 5));
//
//// Course 22
//        extendedCourseList.add(new ExtendedCourse(22, 10, 20, "Strategic HR Leadership", "Nora Johnson", "Intermediate", 3));
//
//// Course 23
//        extendedCourseList.add(new ExtendedCourse(23, 15, 30, "Advanced 3D Design Techniques", "Lucas Brown", "Beginner", 8));
//
//// Course 24
//        extendedCourseList.add(new ExtendedCourse(24, 14, 28, "Digital Marketing Trends", "Chloe Davis", "Advanced", 7));
//
//// Course 25
//        extendedCourseList.add(new ExtendedCourse(25, 16, 32, "Finance & Accounting Mastery", "Logan Anderson", "Intermediate", 6));
//
//// Course 26
//        extendedCourseList.add(new ExtendedCourse(26, 12, 24, "Personal Development Strategies", "Grace Harris", "Beginner", 5));
//
//// Course 27
//        extendedCourseList.add(new ExtendedCourse(27, 10, 20, "Effective Office Collaboration", "Aiden Martinez", "Intermediate", 3));
//
//// Course 28
//        extendedCourseList.add(new ExtendedCourse(28, 15, 30, "HR Management Essentials", "Zoe King", "Advanced", 8));
//
//// Course 29
//        extendedCourseList.add(new ExtendedCourse(29, 14, 28, "3D Design Principles", "Leo Brooks", "Beginner", 7));
//
//// Course 30
//        extendedCourseList.add(new ExtendedCourse(30, 16, 32, "Advanced SEO Strategies", "Victoria Lee", "Advanced", 6));
//
//// Course 31
//        extendedCourseList.add(new ExtendedCourse(31, 12, 24, "Finance & Accounting Fundamentals", "Nathan White", "Intermediate", 5));
//
//// Course 32
//        extendedCourseList.add(new ExtendedCourse(32, 10, 20, "Personal Development Workshop", "Luna Turner", "Beginner", 3));
//
//// Course 33
//        extendedCourseList.add(new ExtendedCourse(33, 15, 30, "Office Productivity Essentials", "Owen Johnson", "Intermediate", 8));
//
//// Course 34
//        extendedCourseList.add(new ExtendedCourse(34, 14, 28, "Modern HR Strategies", "Aria Brown", "Advanced", 7));
//
//// Course 35
//        extendedCourseList.add(new ExtendedCourse(35, 16, 32, "Graphic Design Principles", "Max Davis", "Intermediate", 6));
//
//// Course 36
//        extendedCourseList.add(new ExtendedCourse(36, 12, 24, "Web Development Masterclass", "Emma Wilson", "Advanced", 5));
//
//// Course 37
//        extendedCourseList.add(new ExtendedCourse(37, 10, 20, "Advanced SEO Techniques", "Ethan Miller", "Intermediate", 3));
//
//// Course 38
//        extendedCourseList.add(new ExtendedCourse(38, 15, 30, "Finance & Accounting for Professionals", "Ava Turner", "Advanced", 8));
//
//// Course 39
//        extendedCourseList.add(new ExtendedCourse(39, 14, 28, "Personal Development for Success", "Oliver King", "Intermediate", 7));
//
//// Course 40
//        extendedCourseList.add(new ExtendedCourse(40, 16, 32, "Office Productivity Mastery", "Sophie Brown", "Beginner", 6));
//
//// Course 41
//        extendedCourseList.add(new ExtendedCourse(41, 12, 24, "Strategic HR Planning", "Liam Davis", "Advanced", 5));
//
//// Course 42
//        extendedCourseList.add(new ExtendedCourse(42, 10, 20, "Digital Marketing Essentials", "Nora Harris", "Intermediate", 3));
//
//// Course 43
//        extendedCourseList.add(new ExtendedCourse(43, 15, 30, "Graphic Design Techniques", "Leo Johnson", "Beginner", 8));
//
//// Course 44
//        extendedCourseList.add(new ExtendedCourse(44, 14, 28, "Advanced Web Development", "Chloe Brown", "Intermediate", 7));
//
//// Course 45
//        extendedCourseList.add(new ExtendedCourse(45, 16, 32, "Effective SEO Strategies", "Owen Miller", "Advanced", 6));
//
//// Course 46
//        extendedCourseList.add(new ExtendedCourse(46, 12, 24, "Finance & Accounting Principles II", "Luna Wilson", "Intermediate", 5));
//
//// Course 47
//        extendedCourseList.add(new ExtendedCourse(47, 10, 20, "Personal Development Masterclass", "Max Turner", "Beginner", 3));
//
//// Course 48
//        extendedCourseList.add(new ExtendedCourse(48, 15, 30, "Office Productivity Techniques", "Emma King", "Intermediate", 8));
//
//// Course 49
//        extendedCourseList.add(new ExtendedCourse(49, 14, 28, "Modern HR Leadership", "Ethan Brooks", "Advanced", 7));
//
//// Course 50
//        extendedCourseList.add(new ExtendedCourse(50, 16, 32, "Advanced Graphic Design Principles", "Aria Davis", "Intermediate", 6));
//
//// Course 51
//        extendedCourseList.add(new ExtendedCourse(51, 12, 24, "Web Development Fundamentals", "Nathan White", "Advanced", 5));
//
//        dbHelper.addExtendedCourses(extendedCourseList);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }
    }





    private void checkSessionState() {
        SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        long savedTimestamp = preferences.getLong("timestamp", 0);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);

        Log.d("NavBarActivity", "before if LoggedIN()");

        if (isLoggedIn) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - savedTimestamp;
            Log.d("NavBarActivity", "after if LoggedIN()");
            Log.d("elapsedTime", "elapsedTime : " + elapsedTime);
            Log.d("currentTime", "currentTime : " + currentTime);
            Log.d("savedTimestamp", "savedTimestamp : " + savedTimestamp);

            if (elapsedTime >= 1000*3600) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.apply();
                Log.d("NavBarActivity", "Session expired, redirecting to LoginActivity");
                startActivity(new Intent(NavBarActivity.this, LoginActivity.class));
                finish();
            }
        } else {
            Log.d("NavBarActivity", "User not logged in, redirecting to LoginActivity");
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        sessionCheckHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkSessionState();
            }
        }, SESSION_CHECK_INTERVAL);
    }
    @Override
    protected void onDestroy() {
        // Remove any remaining callbacks when the activity is destroyed
        sessionCheckHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }



    private final BottomNavigationView.OnItemSelectedListener navListener =
            new BottomNavigationView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    if (item.getItemId() == R.id.navigation_home) {
                        selectedFragment = new HomeFragment();
                    }
                    else if (item.getItemId() ==  R.id.navigation_courses) {
                        selectedFragment = new CoursesFragment();
                    }  else if (item.getItemId() ==  R.id.navigation_bookmark) {
                        selectedFragment = new HomeFragment();
                    }
                    else if (item.getItemId() ==  R.id.navigation_profile) {
                        selectedFragment = new ProfileFragment();
                    }

                    assert selectedFragment != null;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();

                    return true;
                }
            };

}