package com.example.ranjit.assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
    }

    public void calculateBMI(View view) {
        //gets the height
        EditText height = (EditText) findViewById(R.id.height);
        String value = height.getText().toString();
        Double heightVal = Double.parseDouble(value);
        System.out.println("Here is the height" + heightVal);

        //gets the weight
        EditText weight = (EditText) findViewById(R.id.weight);
        String wVal = weight.getText().toString();
        Double weightVal = Double.parseDouble(wVal);
        System.out.println("Here is the weight" + weightVal);

        Double calc = (weightVal /(heightVal * heightVal));
        EditText result = (EditText) findViewById(R.id.resultBMI);

        // use DecimalFormat("0.##") to limit to 2 decimal places
        result.setText(calc.toString());
        System.out.println("Here is the BMI" + calc.toString());
    }
}
