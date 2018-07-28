package com.example.ranjit.assignment1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db = null;
    public static final String PERSON_TABLE_NAME = "PERSON"; // name of the Table
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
        db = helper.getWritableDatabase();
        // run a query
        Cursor cursor = db.query(InClassDatabaseHelper.TABLE_NAME,new String[]
                        {"NAME","PASSWORD","DATE"},
                null,null,null,null,null); //
        if (cursor.moveToFirst()){
           /* String name = cursor.getString(0);
            EditText results = (EditText) findViewById(R.id.personName);
            results.setText(name);*/
        }
        cursor.close(); // cleanup

//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            mUserEmail = bundle.getString("email");
//        }
        dateView = (TextView) findViewById(R.id.dateOfBirth);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void onClickEnter(View view) {

        EditText personName = (EditText) findViewById(R.id.personName);
        if( personName.getText().toString().isEmpty()){
            personName.setHint("Enter Name !!!");
            return;
        }
        EditText dob = (EditText) findViewById(R.id.dateOfBirth);
        if( dob.getText().toString().isEmpty()){
            dob.setHint("Enter Date Of Birth !!!");
            return;
        }
        EditText cardNumber = (EditText) findViewById(R.id.cardNumber);
        if( cardNumber.getText().toString().isEmpty()){
            cardNumber.setHint("Enter Health Card Number !!!");
            return;
        }
        EditText email = (EditText) findViewById(R.id.user_email);
        if( email.getText().toString().isEmpty()){
            email.setHint("Enter Email address !!!");
            return;
        }
        EditText pass = (EditText) findViewById(R.id.password);
        if( pass.getText().toString().isEmpty()){
            pass.setHint("Enter Password !!!");
            return;
        }

        //Save data to database
        //TODO: save data to database
        Date today = new Date();
        ContentValues personsValues = new ContentValues();
        personsValues.put("EMAIL", email.getText().toString());
        personsValues.put("NAME", personName.getText().toString());
        personsValues.put("PASSWORD", pass.getText().toString());
        personsValues.put("HEALTH_CARD_NUMB", cardNumber.getText().toString());
        personsValues.put("DATE", today.getTime());
        db.insert(PERSON_TABLE_NAME,null, personsValues);

        //Clean all the input parameters
        personName.setText("");
        cardNumber.setText("");
        dob.setText("");
        email.setText("");
        pass.setText("");

        //Start the first screen again for login
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

}
