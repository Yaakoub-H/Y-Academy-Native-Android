package com.example.quizapplication.view.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quizapplication.R;
import com.example.quizapplication.model.DatabaseHelper;
import com.example.quizapplication.model.User;
import com.example.quizapplication.view.activity.EditProfileActivity;
import com.example.quizapplication.view.activity.LoginActivity;
import com.example.quizapplication.view.activity.TermsAndConditionsAcitivity;

public class ProfileFragment extends Fragment {

    private DatabaseHelper databaseHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        LinearLayout logOutLayout = view.findViewById(R.id.logOutID);
        TextView nameTextView = view.findViewById(R.id.textViewName);
        databaseHelper = new DatabaseHelper(requireContext());

        logOutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(v);
            }
        });

        long loggedInUserId = getLoggedInUserId();
        User loggedInUser = databaseHelper.getUser(loggedInUserId);

        if (loggedInUser != null) {
            String username = loggedInUser.getFullName();
            nameTextView.setText(username);
        }
        else {
            Log.e("UserInfo", "User not found for ID: " + loggedInUserId);
        }


        LinearLayout editProfileLayout = view.findViewById(R.id.EditProfileLayoutId);
        editProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open EditProfileActivity
                Intent intent = new Intent(requireContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void onTermsConditionsClick(View view) {
        Intent intent = new Intent(requireContext(), TermsAndConditionsAcitivity.class);
        startActivity(intent);
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

        startActivity(new Intent(requireContext(), LoginActivity.class));
    }
}