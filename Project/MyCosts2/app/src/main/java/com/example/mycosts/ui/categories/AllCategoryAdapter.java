package com.example.mycosts.ui.categories;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mycosts.R;
import com.example.mycosts.db.entities.Category;

import java.util.List;

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView maxSum;
        private ImageButton editButton;
        private ImageButton deleteButton;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            maxSum = view.findViewById(R.id.maxSum);
            editButton = view.findViewById(R.id.editButton);
            deleteButton = view.findViewById(R.id.deleteButton);
        }
    }

    private List<Category> categories;
    private LayoutInflater inflater;
    private Context context;
    private AllCategoriesPresenter presenter;

    public AllCategoryAdapter(Context context, AllCategoriesPresenter presenter, List<Category> categories) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.categories = categories;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.category_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Category category = categories.get(i);
        viewHolder.name.setText(category.getName());
        viewHolder.maxSum.setText(category.getMaxSum().toString());
        viewHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCategory(category, viewHolder.getLayoutPosition());
            }
        });

        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCategory(viewHolder.getLayoutPosition());
            }
        });
    }

    private void deleteCategory(int layoutPosition) {
        presenter.deleteCategory(categories.get(layoutPosition), layoutPosition);
//        categories.remove(layoutPosition);
//        notifyItemRemoved(layoutPosition);
    }

    private void editCategory(final Category category, final int layoutPosition) {
        final Dialog dialog = new Dialog(context, R.style.Dialog);
        dialog.setTitle("Изменить категорию");
        dialog.setContentView(R.layout.dialog_category);

        final EditText categoryName = dialog.findViewById(R.id.categoryName);
        final EditText categoryMaxSum = dialog.findViewById(R.id.categoryMaxSum);
        Button saveButton = dialog.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!categoryName.getText().toString().isEmpty())
                    category.setName(categoryName.getText().toString());
                if (!categoryMaxSum.getText().toString().isEmpty())
                    category.setMaxSum(Integer.parseInt(categoryMaxSum.getText().toString()));
                presenter.updateCategory(category, layoutPosition);
                dialog.cancel();
            }
        });

        categoryName.setText(category.getName());
        categoryMaxSum.setText(category.getMaxSum().toString());
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
