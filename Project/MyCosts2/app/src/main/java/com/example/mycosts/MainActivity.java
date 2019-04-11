package com.example.mycosts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClickSum(View v){
        EditText obj1 = (EditText)findViewById(R.id.number1);
        EditText obj2 = (EditText)findViewById(R.id.number2);
        TextView objResult = (TextView)findViewById(R.id.result);
        int number1 = Integer.parseInt(obj1.getText().toString());
        int number2 = Integer.parseInt(obj2.getText().toString());
        int result = number1 + number2;
        objResult.setText(Integer.toString(result));
    }

    public void onButtonClickDiff(View v){
        EditText obj1 = (EditText)findViewById(R.id.number1);
        EditText obj2 = (EditText)findViewById(R.id.number2);
        TextView objResult = (TextView)findViewById(R.id.result);
        int number1 = Integer.parseInt(obj1.getText().toString());
        int number2 = Integer.parseInt(obj2.getText().toString());
        int result = number1 - number2;
        objResult.setText(Integer.toString(result));
    }
}
