package com.example.mycosts;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AllExpensesFragment extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton addExpense;
    private List<Expense> expenses = new ArrayList<>();

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
        loadExpenses();
        return view;
    }

    private void loadExpenses() {
        AllExpenseAdapter adapter = new AllExpenseAdapter(getContext(), ModelUtils.getAllExpenses(App.getCategories()));
        recyclerView.setAdapter(adapter);
    }

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
            UIUtils.fillCategorySpinner(getContext(), categorySpinner);
            expenseDate.setText(DateUtils.convertDateToString(new Date()));

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Object selectedItem = categorySpinner.getSelectedItem();
                    Category category = ModelUtils.findCategoryByName(selectedItem.toString());
                    if (!expenseSum.getText().toString().isEmpty() && category != null) {
                        Integer sum = Integer.parseInt(expenseSum.getText().toString());
                        Date date = DateUtils.convertStringToDate(expenseDate.getText().toString());
                        String name = expenseName.getText().toString();
                        category.getExpenses().add(new Expense(name, date, sum, category));
                        loadExpenses();
                    }
                    dialog.cancel();
                }
            });

            dialog.show();
        }
    }
}
