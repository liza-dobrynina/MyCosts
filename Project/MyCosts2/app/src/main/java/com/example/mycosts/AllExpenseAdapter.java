package com.example.mycosts;

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
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.List;

public class AllExpenseAdapter extends RecyclerView.Adapter<AllExpenseAdapter.ViewHolder> {

    private List<Expense> expenses;
    private LayoutInflater inflater;
    private Context context;

    public AllExpenseAdapter(Context context, List<Expense> expenses) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.expenses = expenses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.expanse_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Expense expense = expenses.get(i);
        viewHolder.main.setText(String.format("%s %s %s",
                DateUtils.convertDateToString(expense.getDate()),
                expense.getCategory().getName(),
                expense.getSum()));
        viewHolder.comment.setText(expense.getName());

        viewHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editExpense(expense, viewHolder.getLayoutPosition());
            }
        });

        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteExpense(viewHolder.getLayoutPosition());
            }
        });
    }

    private void deleteExpense(int position) {
        Expense expense = expenses.get(position);
        expenses.remove(expense);
        expense.getCategory().getExpenses().remove(expense);
        notifyItemRemoved(position);
    }

    private void editExpense(final Expense expense, final int layoutPosition) {
        final Dialog dialog = new Dialog(context, R.style.Dialog);
        dialog.setTitle("Изменить расход");
        dialog.setContentView(R.layout.dialog_expense);
        final Spinner categorySpinner = dialog.findViewById(R.id.category);
        final EditText expenseSum = dialog.findViewById(R.id.expenseSum);
        final EditText expenseDate = dialog.findViewById(R.id.expenseDate);
        final EditText expenseName = dialog.findViewById(R.id.expenseName);
        Button saveButton = dialog.findViewById(R.id.saveButton);
        UIUtils.fillCategorySpinner(context, categorySpinner);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Expense changedExpense = ModelUtils.findExpenseById(expense.getId(), expense.getCategory());
                Object selectedItem = categorySpinner.getSelectedItem();
                Category category = ModelUtils.findCategoryByName(selectedItem.toString());
                if (!expenseSum.getText().toString().isEmpty() && category != null) {
                    changedExpense.setCategory(category);
                    changedExpense.setSum(Integer.parseInt(expenseSum.getText().toString()));
                    changedExpense.setName(expenseName.getText().toString());
                    if (expense.getDate().equals(DateUtils.convertStringToDate(expenseDate.getText().toString()))) {
                        notifyItemChanged(layoutPosition);
                    } else {
                        changedExpense.setDate(DateUtils.convertStringToDate(expenseDate.getText().toString()));
                        expenses = ModelUtils.getAllExpenses(App.getCategories());
                        notifyDataSetChanged();
                    }
                }
                dialog.cancel();
            }
        });

        Integer categoryPosition = getCategoryIndex(categorySpinner, expense.getCategory().getName());
        if (categoryPosition != null)
            categorySpinner.setSelection(categoryPosition);
        expenseDate.setText(DateUtils.convertDateToString(expense.getDate()));
        expenseSum.setText(String.valueOf(expense.getSum()));
        expenseName.setText(expense.getName());
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    private Integer getCategoryIndex(Spinner spinner, String name) {
        SpinnerAdapter adapter = spinner.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            Object item = adapter.getItem(i);
            if (item.equals(name))
                return i;
        }
        return null;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView main;
        private TextView comment;
        private ImageButton editButton;
        private ImageButton deleteButton;

        public ViewHolder(View view) {
            super(view);
            main = view.findViewById(R.id.main);
            comment = view.findViewById(R.id.comment);
            editButton = view.findViewById(R.id.editButton);
            deleteButton = view.findViewById(R.id.deleteButton);
        }
    }
}
