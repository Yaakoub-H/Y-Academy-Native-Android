// AllPopularCoursesAdapter.java
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
import com.example.quizapplication.model.AllPopularCoursesItem;
import com.example.quizapplication.view.fragment.OnItemClickListener;

import java.util.List;

public class AllPopularCoursesAdapter extends RecyclerView.Adapter<AllPopularCoursesAdapter.AllPopularCoursesViewHolder> {

    private Context context;
    private List<AllPopularCoursesItem> allPopularCoursesList;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public AllPopularCoursesAdapter(Context context, List<AllPopularCoursesItem> allPopularCoursesList) {
        this.context = context;
        this.allPopularCoursesList = allPopularCoursesList;
    }


    @NonNull
    @Override
    public AllPopularCoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_popular_items, parent, false);
        return new AllPopularCoursesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllPopularCoursesViewHolder holder, int position) {
        AllPopularCoursesItem item = allPopularCoursesList.get(position);

        // Set data to views
        holder.categoryTitle.setText(item.getCategoryTitle());
        holder.icon.setImageResource(item.getIconResource());
        holder.textDescription.setText(item.getTextDescription());
        holder.priceValue.setText(item.getPriceValue());
        holder.textPrice.setText(item.getTextPrice());
    }

    @Override
    public int getItemCount() {
        return allPopularCoursesList.size();
    }

    public static class AllPopularCoursesViewHolder extends RecyclerView.ViewHolder {
        TextView categoryTitle, textDescription, priceValue, textPrice;
        ImageView icon;

        public AllPopularCoursesViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.categoryTitleId);
            icon = itemView.findViewById(R.id.icon);
            textDescription = itemView.findViewById(R.id.textDescription);
            priceValue = itemView.findViewById(R.id.priceValue);
            textPrice = itemView.findViewById(R.id.textPrice);
        }
    }
}
