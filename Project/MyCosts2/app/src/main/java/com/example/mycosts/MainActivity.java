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


    System system = new System();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


}
