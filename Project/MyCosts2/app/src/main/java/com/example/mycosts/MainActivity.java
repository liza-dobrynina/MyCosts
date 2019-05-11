package com.example.mycosts;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;
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

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.category:
                            Intent intent = new Intent(".allCategoriesActivity");
                            startActivity(intent);
                            break;
                        case R.id.report:
                            Intent intent2 = new Intent(".reportActivity");
                            startActivity(intent2);
                            break;
                    }
                    return false;
                }
            });

    }



//    public void reportClick(View v){
//        Intent intent = new Intent(".reportActivity");
//        startActivity(intent);
//    }
//
//    public void allCategoriesClick(View v){
//        Intent intent = new Intent(".allCategoriesActivity");
//        startActivity(intent);
//    }

}
