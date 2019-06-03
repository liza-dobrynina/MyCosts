package com.example.mycosts.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.mycosts.R;
import com.example.mycosts.ui.categories.CategoriesFragment;
import com.example.mycosts.ui.expenses.ExpensesFragment;
import com.example.mycosts.ui.report.ReportFragment;


public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.all_expense:
                    fragmentTransaction.
                            replace(R.id.fragmentPlace, new ExpensesFragment())
                            .commit();
                    return true;
                case R.id.category:
                    fragmentTransaction.
                            replace(R.id.fragmentPlace, new CategoriesFragment())
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
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
        bottomNavigationView.setSelectedItemId(R.id.all_expense);

    }

}
