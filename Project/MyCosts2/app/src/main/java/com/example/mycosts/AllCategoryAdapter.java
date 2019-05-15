package com.example.mycosts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView maxSum;
        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            maxSum = view.findViewById(R.id.maxSum);
        }
    }

    private List<Category> categories;
    private LayoutInflater inflater;

    public AllCategoryAdapter(Context context, List<Category> categories) {
        inflater = LayoutInflater.from(context);
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.category_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Category category = categories.get(i);
        viewHolder.name.setText(category.getName());
        viewHolder.maxSum.setText(category.getMaxSum().toString());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
