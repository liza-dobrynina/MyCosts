package com.example.mycosts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangeCategoryActivity extends AppCompatActivity {

    private EditText categoryName;
    private EditText categoryMaxSum;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_category);
        categoryMaxSum = findViewById(R.id.categoryMaxSum);
        categoryName = findViewById(R.id.categoryName);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category = new Category(categoryName.getText().toString(), Integer.parseInt(categoryMaxSum.getText().toString()));
                App.getCategories().add(category);
                finish();
            }
        });
    }
}
