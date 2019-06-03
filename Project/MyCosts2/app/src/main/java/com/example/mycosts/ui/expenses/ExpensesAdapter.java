package com.example.mycosts.ui.expenses;

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
import android.widget.TextView;

import com.example.mycosts.R;
import com.example.mycosts.api.model.Category;
import com.example.mycosts.api.model.Expense;
import com.example.mycosts.api.model.ExpenseWithCategory;
import com.example.mycosts.utils.DateUtils;

import java.util.List;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ViewHolder> {

    private List<ExpenseWithCategory> expenses;
    private LayoutInflater inflater;
    private Context context;
    private ExpensesPresenter presenter;

    public ExpensesAdapter(Context context, ExpensesPresenter presenter, List<ExpenseWithCategory> expenses) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.presenter = presenter;
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
        final ExpenseWithCategory expenseWithCategory = expenses.get(i);
        viewHolder.main.setText(String.format("%s %s %s",
                DateUtils.convertDateToString(expenseWithCategory.getDate()),
                expenseWithCategory.getCategory().getName(),
                expenseWithCategory.getSum() + " рублей"));
        viewHolder.comment.setText(expenseWithCategory.getName());

        viewHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editExpense(expenseWithCategory, viewHolder.getLayoutPosition());
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
        presenter.deleteExpense(expenses.get(position), position);
    }

    private void editExpense(final ExpenseWithCategory expense, final int layoutPosition) {
        final Dialog dialog = new Dialog(context, R.style.Dialog);
        dialog.setTitle("Изменить расход");
        dialog.setContentView(R.layout.dialog_expense);
        final Spinner categorySpinner = dialog.findViewById(R.id.category);
        final EditText expenseSum = dialog.findViewById(R.id.expenseSum);
        final EditText expenseDate = dialog.findViewById(R.id.expenseDate);
        final EditText expenseName = dialog.findViewById(R.id.expenseName);
        Button saveButton = dialog.findViewById(R.id.saveButton);
        presenter.fillAndSetSelected(categorySpinner, expense.getCategory());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Expense changedExpense = Expense.fromExpenseWithCategory(expenses.get(layoutPosition));
                Object selectedItem = categorySpinner.getSelectedItem();
                Category category = (Category) selectedItem;
                if (!expenseSum.getText().toString().isEmpty() && category != null) {
                    changedExpense.setCategoryId(category.getId());
                    changedExpense.setSum(Integer.parseInt(expenseSum.getText().toString()));
                    changedExpense.setName(expenseName.getText().toString());
                    if (changedExpense.getDate().equals(DateUtils.convertStringToDate(expenseDate.getText().toString()))) {
                        presenter.updateExpense(changedExpense, layoutPosition, false);
                    } else {
                        changedExpense.setDate(DateUtils.convertStringToDate(expenseDate.getText().toString()));
                        presenter.updateExpense(changedExpense, layoutPosition, true);
                    }
                }
                dialog.cancel();
            }
        });
        expenseDate.setText(DateUtils.convertDateToString(expense.getDate()));
        expenseSum.setText(String.valueOf(expense.getSum()));
        expenseName.setText(expense.getName());
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public void setExpenses(List<ExpenseWithCategory> expenses) {
        this.expenses = expenses;
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
