package com.example.mycosts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Category> categories = new ArrayList<>();
    ArrayList<Expense> expenses = new ArrayList<>();
    System system = new System();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInitialData();

//        RecyclerView recyclerView = findViewById(R.id.categories);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        DataAdapter adapter = new DataAdapter(this, categories);

//        recyclerView.setAdapter(adapter);
    }

    public void newExpenseClick(View v){
        Intent intent = new Intent(".NewExpenseActivity");
        startActivity(intent);
}

    public void reportClick(View v){
        Intent intent = new Intent(".reportActivity");
        startActivity(intent);
    }

    public void allCategoriesClick(View v){
        Intent intent = new Intent(".allCategoriesActivity");
        startActivity(intent);
    }
    public void allExpensesClick(View v){
        Intent intent = new Intent(".allExpensesActivity");
        startActivity(intent);
    }

    private void setInitialData(){
        categories.add(new Category("Супермаркеты", 5000));
        categories.add(new Category("Красота и здоровье", 2000));
        categories.add(new Category("Рестораны и кафе", 3000));
        categories.add(new Category("Одежда и аксессуары", 10000));
        categories.add(new Category("Коммунальные платежи", 5000));
        system.addExpense(expenses, categories.get(0), "Пятерочка", "5.12.2019 15:40", 100);
    }
}
