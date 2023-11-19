    package com.example.quizapplication.view.fragment;

    import static android.content.Context.MODE_PRIVATE;

    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.graphics.Color;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.LinearLayout;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
    import androidx.fragment.app.Fragment;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.example.quizapplication.CourseActivity;
    import com.example.quizapplication.R;
    import com.example.quizapplication.adapter.AllPopularCoursesAdapter;
    import com.example.quizapplication.adapter.MentorAdapter;
    import com.example.quizapplication.adapter.PopularAdapter;
    import com.example.quizapplication.model.AllPopularCoursesItem;
    import com.example.quizapplication.model.Course;
    import com.example.quizapplication.model.DatabaseHelper;
    import com.example.quizapplication.model.ExtendedCourse;
    import com.example.quizapplication.model.MentorItem;
    import com.example.quizapplication.model.PopularItem;
    import com.example.quizapplication.model.User;
    import com.example.quizapplication.view.activity.AllCategory;
    import com.example.quizapplication.view.activity.LoginActivity;
    import com.example.quizapplication.view.activity.MentorSeeAll;
    import com.example.quizapplication.view.activity.NavBarActivity;
    import com.example.quizapplication.view.activity.PopularCourses;

    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.List;
    import java.util.Random;

    public class HomeFragment extends Fragment {
        private List<PopularItem> displayedCourses;
        private List<ExtendedCourse> extendedCourseList;

        private PopularAdapter adapter;
        private OnItemClickListener onItemClickListener;

        private DatabaseHelper databaseHelper;

        private TextView usernameTextView;



        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_home, container, false);
            displayedCourses = new ArrayList<>();

            adapter = new PopularAdapter(displayedCourses);
            databaseHelper = new DatabaseHelper(requireContext());

            ImageView logoutIcon = view.findViewById(R.id.logoutIcon);

            // set username
            usernameTextView = view.findViewById(R.id.usernameHomeID);
            long loggedInUserId = getLoggedInUserId();

            // Use the user ID to fetch the user from the database
            User loggedInUser = databaseHelper.getUser(loggedInUserId);
            Log.d("UserInfo", "Logged-in User: " + loggedInUser);

            // Set the username in the TextView
            if (loggedInUser != null) {
                String fullName = loggedInUser.getFullName();
                usernameTextView.setText("Hi, " + fullName);
            } else {
                usernameTextView.setText("Hi, User"); // Default message if user not found
            }

            logoutIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    logout(v);
                }
            });
            // Call categories items.
            LinearLayout categoriesLayout = view.findViewById(R.id.categoriesLayout);
            for (int i = 0; i < categoriesLayout.getChildCount(); i++) {
                View categoryView = categoriesLayout.getChildAt(i);
                if (categoryView instanceof TextView) {
                    final TextView categoryTextView = (TextView) categoryView;

                    categoryTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int j = 0; j < categoriesLayout.getChildCount(); j++) {
                                View otherView = categoriesLayout.getChildAt(j);
                                if (otherView instanceof TextView) {
                                    TextView otherTextView = (TextView) otherView;
                                    otherTextView.setBackgroundColor(Color.TRANSPARENT);
                                    otherTextView.setTextColor(0xFFA0A4AB); // Reset text color
                                }
                            }
                            categoryTextView.setTextColor(0xFF0961F5);
                        }
                    });
                }
            }
            // Call popular items.
            LinearLayout popularLayout = view.findViewById(R.id.PopularLayout);
            for (int i = 0; i < popularLayout.getChildCount(); i++) {
                View popularView = popularLayout.getChildAt(i);
                if (popularView instanceof TextView) {
                    final TextView popularTextView = (TextView) popularView;
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

                            // Fetch and display courses for the selected category
                            String selectedCategory = popularTextView.getText().toString();
                            List<Course> courses = databaseHelper.getPopularCoursesByCategory(selectedCategory);

                            displayedCourses.addAll(mapCoursesToPopularItems(courses));
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            }

            // Set up RecyclerView for popular courses
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);

            adapter = new PopularAdapter(displayedCourses);
            recyclerView.setAdapter(adapter);

            // Fetch and display initial popular courses
            displayedCourses.clear();
            displayedCourses.addAll(mapCoursesToPopularItems(databaseHelper.getPopularCourses()));
            adapter.notifyDataSetChanged();

            extendedCourseList = databaseHelper.getAllExtendedCourses();
            adapter.setOnItemClickListener(position -> {
                if (position >= 0 && position < extendedCourseList.size()) {
                    ExtendedCourse extendedCourse = extendedCourseList.get(position);

                    int courseId = extendedCourse.getCourseId();
                    if (courseId >= 0) {
                        Course course = databaseHelper.getCourseById(courseId);

                        if (course != null) {
                            // Log the retrieved data for debugging
                            Log.d("ItemClick", "ExtendedCourse: " + extendedCourse.toString());
                            Log.d("ItemClick", "Course: " + course.toString());

                            // Proceed with your logic to handle the clicked item
                            Intent intent = new Intent(requireContext(), CourseActivity.class);
                            intent.putExtra("courseId", courseId);
                            intent.putExtra("numberOfClasses", extendedCourse.getNumberOfClasses());
                            intent.putExtra("numberOfHours", extendedCourse.getNumberOfHours());
                            intent.putExtra("about", extendedCourse.getAbout());
                            intent.putExtra("instructorName", extendedCourse.getInstructorName());
                            intent.putExtra("difficultyLevel", extendedCourse.getDifficultyLevel());
                            intent.putExtra("numberOfQuizzes", extendedCourse.getNumberOfQuizzes());

                            intent.putExtra("courseName", course.getCourseName());
                            intent.putExtra("category", course.getCategory());
                            intent.putExtra("price", course.getPrice());
                            intent.putExtra("rate", course.getRate());

                            startActivity(intent);
                        } else {
                            Log.e("ItemClick", "Course not found for courseId: " + courseId);
                        }
                    } else {
                        Log.e("ItemClick", "Invalid courseId: " + courseId);
                    }
                } else {
                    Log.e("ItemClick", "Invalid position: " + position);
                }
            });



            recyclerView.setAdapter(adapter);


            // call mentor items
            RecyclerView recyclerViewMentor = view.findViewById(R.id.recyclerViewMentor);
            LinearLayoutManager layoutManagerMentor = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerViewMentor.setLayoutManager(layoutManagerMentor);

            List<MentorItem> mentorList = generateStaticMentorItems();
            MentorAdapter mentorAdapter = new MentorAdapter(requireContext(), mentorList);
            recyclerViewMentor.setAdapter(mentorAdapter);

            TextView seeAllText = view.findViewById(R.id.seeAllText);
            TextView seeAllTextPopular = view.findViewById(R.id.seeAllPopularText);
            seeAllText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(requireContext(), AllCategory.class);
                    startActivity(intent);
                }
            });
            seeAllTextPopular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(requireContext(), PopularCourses.class);
                    startActivity(intent);
                }
            });

            TextView seeAllMentorText = view.findViewById(R.id.seeAllMentorText);
            seeAllMentorText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(requireContext(), MentorSeeAll.class);
                    startActivity(intent);
                }
            });

            return view;
        }


        private long getLoggedInUserId() {
            SharedPreferences preferences = requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
            return preferences.getLong("loggedInUserId", -1);
        }


        public void logout(View view) {
            SharedPreferences preferences = requireActivity().getSharedPreferences("MyPreferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            // Redirect to LoginActivity
            startActivity(new Intent(requireContext(), LoginActivity.class));
        }

        private void displayCoursesByCategory(String category, RecyclerView recyclerView) {
            // Fetch courses by category from the database
            DatabaseHelper databaseHelper = new DatabaseHelper(requireContext());
            List<Course> courses = databaseHelper.getCoursesByCategory(category);

            // Create and set the adapter for the RecyclerView

            PopularAdapter coursesAdapter = new PopularAdapter(mapCoursesToPopularItems(courses));
            recyclerView.setAdapter(coursesAdapter);

        }

        private List<PopularItem> mapCoursesToPopularItems(List<Course> courses) {
            List<PopularItem> mappedList = new ArrayList<>();

            for (Course course : courses) {
                mappedList.add(new PopularItem(
                        course.getCategory(),
                        course.getCourseName(),
                        "$ "+ course.getPrice(),
                        getRandomIconResource(),
                        course.getRate(),
                        course.getStudentCount() + " Std"
                ));
            }

            return mappedList;
        }


        private List<MentorItem> generateStaticMentorItems() {
            List<MentorItem> items = new ArrayList<>();
            items.add(new MentorItem(R.drawable.rounded_special_offer_background, "Yaakoub"));
            items.add(new MentorItem(R.drawable.rounded_special_offer_background, "Reem"));
            items.add(new MentorItem(R.drawable.rounded_special_offer_background, "Bilal"));
            items.add(new MentorItem(R.drawable.rounded_special_offer_background, "Yaakoub"));
            items.add(new MentorItem(R.drawable.rounded_special_offer_background, "Reem"));
            items.add(new MentorItem(R.drawable.rounded_special_offer_background, "Bilal"));
            return items;
        }

        private int getRandomImageResource() {
            int[] imageResources = {
                    R.drawable.quiz_icon,
                    R.drawable.question,
                    // Add more image resources as needed
            };

            Random random = new Random();
            int randomIndex = random.nextInt(imageResources.length);

            return imageResources[randomIndex];
        }

        private int getRandomIconResource() {
            int[] iconResources = {
                    R.drawable.ic_search,
                    R.drawable.ic_password,
                    // Add more icon resources as needed
            };

            Random random = new Random();
            int randomIndex = random.nextInt(iconResources.length);

            return iconResources[randomIndex];
        }
    }
