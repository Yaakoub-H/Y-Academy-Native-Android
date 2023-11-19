package com.example.quizapplication.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapplication.R;
import com.example.quizapplication.model.MentorItem;

import java.util.List;


public class MentorAdapterSeeAll extends RecyclerView.Adapter<MentorAdapterSeeAll.MentorViewHolder> {

    private Context context;
    private List<MentorItem> mentorList;

    public MentorAdapterSeeAll(Context context, List<MentorItem> mentorList) {
        this.context = context;
        this.mentorList = mentorList;
    }

    @NonNull
    @Override
    public MentorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mentor_item_recyler_view, parent, false);
        return new MentorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MentorViewHolder holder, int position) {
        MentorItem mentorItem = mentorList.get(position);

        holder.fullNameTextView.setText(mentorItem.getName());
        holder.categoryTypeTextView.setText(mentorItem.getCategoryType());
    }

    @Override
    public int getItemCount() {
        return mentorList.size();
    }

    public static class MentorViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView fullNameTextView;
        TextView categoryTypeTextView;

        public MentorViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageCategory);
            fullNameTextView = itemView.findViewById(R.id.textViewFullName);
            categoryTypeTextView = itemView.findViewById(R.id.textViewCategoryType);
        }
    }
}

