package com.example.mycosts.ui.expenses;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mycosts.R;
import com.example.mycosts.db.entities.Category;
import com.example.mycosts.db.entities.CategorySum;
import com.example.mycosts.db.entities.Expense;
import com.example.mycosts.db.entities.ExpenseWithCategory;
import com.example.mycosts.utils.DateUtils;
import com.example.mycosts.utils.UIUtils;

import java.util.Date;
import java.util.List;

public class AllExpensesFragment extends Fragment implements AllExpenseContract {

    private RecyclerView recyclerView;
    private FloatingActionButton addExpense;

    private AllExpensePresenter presenter;
    private AllExpenseAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_expanses, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        addExpense = view.findViewById(R.id.addExpense);
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewExpenseClick(v);
            }
        });
        AllExpenseModel model = new AllExpenseModel();
        presenter = new AllExpensePresenter(model);
        presenter.attachView(this);
        presenter.viewIsReady();
        return view;
    }

    @Override
    public void setAdapter(List<ExpenseWithCategory> expenses) {
        adapter = new AllExpenseAdapter(getContext(), presenter, expenses);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void addNewExpenseClick(View v) {
        if (getActivity() != null) {
            final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
            dialog.setTitle("Добавить расход");
            dialog.setContentView(R.layout.dialog_expense);
            final Spinner categorySpinner = dialog.findViewById(R.id.category);
            final EditText expenseSum = dialog.findViewById(R.id.expenseSum);
            final EditText expenseDate = dialog.findViewById(R.id.expenseDate);
            final EditText expenseName = dialog.findViewById(R.id.expenseName);
            Button saveButton = dialog.findViewById(R.id.saveButton);
            expenseDate.setText(DateUtils.convertDateToString(new Date()));
            presenter.fillCategories(categorySpinner);

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Object selectedItem = categorySpinner.getSelectedItem();
                    Category category = (Category) selectedItem;
                    if (!expenseSum.getText().toString().isEmpty()) {
                        Integer sum = Integer.parseInt(expenseSum.getText().toString());
                        Date date = DateUtils.convertStringToDate(expenseDate.getText().toString());
                        String name = expenseName.getText().toString();
//                        category.getExpenses().add(new Expense(name, date, sum, category));
                        Expense expense = new Expense(name, date, sum, category.getId());
                        presenter.addExpense(expense);
                    }
                    dialog.cancel();
                }
            });

            dialog.show();
        }
    }

    @Override
    public void fillCategories(Spinner spinner, List<Category> categories) {
        UIUtils.fillCategorySpinner(getContext(), spinner, categories);
    }

    @Override
    public void setSelectedCategory(Spinner spinner,Category category) {
        Integer categoryPosition = UIUtils.getCategoryIndex(spinner, category.getId());
        if (categoryPosition != null)
            spinner.setSelection(categoryPosition);
    }

    @Override
    public void notifyItemRemoved(int position) {
        if (adapter != null) {
            adapter.notifyItemRemoved(position);
        }
    }

    @Override
    public void notifyItemChanged(int position) {
        if (adapter != null) {
            adapter.notifyItemChanged(position);
        }
    }

    @Override
    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void changeData(List<ExpenseWithCategory> expenseWithCategories) {
        adapter.setExpenses(expenseWithCategories);
    }

    @Override
    public void showWarning(CategorySum categorySum) {
        new AlertDialog.Builder(getContext())
                .setTitle("Превышен порог")
                .setMessage(String.format("Превышен порог по категории!\n Сумма расходов: %s\n Порог категории: %s", categorySum.getActualSum(), categorySum.getMaxSum()))
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
