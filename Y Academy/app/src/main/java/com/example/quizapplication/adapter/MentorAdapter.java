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

public class MentorAdapter extends RecyclerView.Adapter<MentorAdapter.MentorViewHolder> {

    private Context context;
    private List<MentorItem> mentorList;

    public MentorAdapter(Context context, List<MentorItem> mentorList) {
        this.context = context;
        this.mentorList = mentorList;
    }

    @NonNull
    @Override
    public MentorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mentor_item, parent, false);
        return new MentorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MentorViewHolder holder, int position) {
        MentorItem mentorItem = mentorList.get(position);

        // Set the data to your views
        holder.imageView.setImageResource(mentorItem.getImageResource());
        holder.textView.setText(mentorItem.getName());
    }

    @Override
    public int getItemCount() {
        return mentorList.size();
    }

    public static class MentorViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MentorViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageCategory);
            textView = itemView.findViewById(R.id.textDescription);
        }
    }
}
