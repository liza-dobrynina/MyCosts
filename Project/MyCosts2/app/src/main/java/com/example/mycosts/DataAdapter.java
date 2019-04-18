package com.example.mycosts;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private List<Category> categories;

    DataAdapter(Context context, List<Category> categories){
        this.categories = categories;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.nameView.setText(category.getName());
        holder.expenseView.setText(category.getNowSum().toString()+"/"+category.getMaxSum().toString());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, expenseView;
        ViewHolder(View view){
            super(view);
            nameView = (TextView) view.findViewById(R.id.name);
            expenseView = (TextView) view.findViewById(R.id.expense);
        }
    }
}
