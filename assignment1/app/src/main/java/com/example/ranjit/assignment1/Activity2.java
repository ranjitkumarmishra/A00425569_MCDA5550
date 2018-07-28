package com.example.ranjit.assignment1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity2 extends AppCompatActivity {
    String mUserEmail = null;
    public static final String BMI_TABLE_NAME = "BMI"; // name of the Table
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mUserEmail = bundle.getString("email");
        }
    }

    public void calculateBMI(View view) {
        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        //gets the height
        EditText height = (EditText) findViewById(R.id.height);
        String hValue = height.getText().toString();
        Double heightVal = Double.parseDouble(hValue);
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues personsValues = new ContentValues();
        personsValues.put("EMAIL", mUserEmail);
        personsValues.put("HEIGHT", hValue.toString());
        personsValues.put("WEIGHT", wVal.toString());
        personsValues.put("BMI", calc.toString());
        personsValues.put("DATE", dateFormat.format(new Date()));
        db.insert(BMI_TABLE_NAME,null, personsValues);
    }

    public void showHistory(View view) {
        //Start the first screen again for login
        Intent intent = new Intent(this,BMIListActivity.class);
        Bundle b = new Bundle();
        b.putString("email",mUserEmail);
        intent.putExtras(b);
        startActivity(intent);

    }
}
