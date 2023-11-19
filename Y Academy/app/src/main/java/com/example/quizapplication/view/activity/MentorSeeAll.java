package com.example.quizapplication.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.quizapplication.R;
import com.example.quizapplication.adapter.MentorAdapter;
import com.example.quizapplication.adapter.MentorAdapterSeeAll;
import com.example.quizapplication.model.MentorItem;

import java.util.ArrayList;
import java.util.List;

public class MentorSeeAll extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_see_all);
        List<MentorItem> mentorList = generateMentorItems();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewMentorSeeAll);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        MentorAdapterSeeAll mentorAdapter = new MentorAdapterSeeAll(this,mentorList);
        recyclerView.setAdapter(mentorAdapter);
    }

    private List<MentorItem> generateMentorItems() {
        List<MentorItem> items = new ArrayList<>();
        // Add your MentorItem objects to the list
        // Example:
        items.add(new MentorItem("John Doe", "Developer"));
        items.add(new MentorItem("Jane Smith", "Designer"));
        items.add(new MentorItem("Robert Johnson", "Product Manager"));
        items.add(new MentorItem("Emily Davis", "Software Engineer"));
        items.add(new MentorItem("Michael Brown", "UX/UI Designer"));
        items.add(new MentorItem("Amanda White", "Data Scientist"));
        items.add(new MentorItem("Daniel Miller", "Frontend Developer"));
        items.add(new MentorItem("Sophia Wilson", "Mobile App Developer"));
        items.add(new MentorItem("David Thompson", "Network Engineer"));
        items.add(new MentorItem("Olivia Lee", "Graphic Designer"));
        items.add(new MentorItem("Matthew Anderson", "Full Stack Developer"));
        items.add(new MentorItem("Emma Martinez", "AI/ML Engineer"));
        items.add(new MentorItem("Christopher Taylor", "Cybersecurity Analyst"));
        items.add(new MentorItem("Grace Harris", "DevOps Engineer"));

        // Add more items as needed
        return items;
    }
}