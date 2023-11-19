package com.example.quizapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapplication.R;
import com.example.quizapplication.model.PopularItem;
import com.example.quizapplication.view.fragment.OnItemClickListener;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {

    private List<PopularItem> popularList;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    // Constructor

    public PopularAdapter(List<PopularItem> popularList) {
        this.popularList = popularList;
    }

    // Other required methods

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item, parent, false);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        PopularItem currentItem = popularList.get(position);

        // Set data to views
        holder.textViewTitlePopular.setText(currentItem.getTitle());
        holder.iconPopular.setImageResource(currentItem.getIconResource());
        holder.textViewDescriptionPopular.setText(currentItem.getDescription());
        holder.textViewPricePopular.setText(String.valueOf(currentItem.getPrice()));

        // Set data for the new fields
        holder.textRating.setText(String.valueOf(currentItem.getRating()));
        holder.textStudentCount.setText(String.valueOf(currentItem.getStudentCount()));

        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return popularList.size();
    }

    static class PopularViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPopular;
        TextView textViewTitlePopular;
        ImageView iconPopular;
        TextView textViewDescriptionPopular;
        TextView textViewPricePopular;
        TextView textRating;
        TextView textStudentCount;

        PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPopular = itemView.findViewById(R.id.imageCategory);
            textViewTitlePopular = itemView.findViewById(R.id.categoryTitleId);
            iconPopular = itemView.findViewById(R.id.icon);
            textViewDescriptionPopular = itemView.findViewById(R.id.textDescription);
            textViewPricePopular = itemView.findViewById(R.id.textPrice);
            textRating = itemView.findViewById(R.id.textRating);
            textStudentCount = itemView.findViewById(R.id.textStudentCount);
        }
    }


}
