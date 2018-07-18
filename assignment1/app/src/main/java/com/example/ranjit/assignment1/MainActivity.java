package com.example.ranjit.assignment1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        // run a query
        Cursor cursor = db.query(InClassDatabaseHelper.TABLE_NAME,new String[]
                        {"NAME","PASSWORD","DATE"},
                null,null,null,null,null); //
        if (cursor.moveToFirst()){
            String name = cursor.getString(0);
            EditText results = (EditText) findViewById(R.id.personName);
            results.setText(name);
        }
        cursor.close(); // cleanup
        db.close(); // cleanup
    }

    public void onClickEnter(View view) {
        Intent intent = new Intent(this,Activity2.class);
        startActivity(intent);
    }

}
