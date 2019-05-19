package com.example.mycosts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {


    private FragmentManager fragmentManager;
    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.all_expense:
                    fragmentTransaction.
                            replace(R.id.fragmentPlace, new AllExpensesFragment())
                            .commit();
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
        bottomNavigationView.setSelectedItemId(R.id.all_expense);

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
