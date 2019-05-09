package com.example.mycosts;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;




public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".NewExpenseActivity");
                startActivity(intent);
            }
        });




    }

//    public void newExpenseClick(View v){
//        Intent intent = new Intent(".NewExpenseActivity");
//        startActivity(intent);
//}

    public void reportClick(View v){
        Intent intent = new Intent(".reportActivity");
        startActivity(intent);
    }

    public void allCategoriesClick(View v){
        Intent intent = new Intent(".allCategoriesActivity");
        startActivity(intent);
    }

}
