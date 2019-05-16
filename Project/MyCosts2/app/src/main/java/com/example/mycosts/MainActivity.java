package com.example.mycosts;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private FragmentManager fragmentManager;
    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.all_expense:

                    return true;
                case R.id.category:
                    fragmentTransaction.
                            replace(R.id.fragmentPlace, new AllCategoriesFragment())
                            .commit();
                    return true;
                case R.id.report:
                    fragmentTransaction
                            .replace(R.id.fragmentPlace, new ReportFragment())
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
//        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(".NewExpenseActivity");
//                startActivity(intent);
//            }
//        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(listener);

    }




//    public void reportClick(View v){
//        Intent intent = new Intent(".ReportFragment");
//        startActivity(intent);
//    }
//
//    public void allCategoriesClick(View v){
//        Intent intent = new Intent(".allCategoriesActivity");
//        startActivity(intent);
//    }

}
