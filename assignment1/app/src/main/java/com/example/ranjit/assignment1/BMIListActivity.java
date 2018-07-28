package com.example.ranjit.assignment1;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BMIListActivity extends ListActivity {

    //BMIResult[] results;
    List<BMIResult> results = new ArrayList<BMIResult>();
    String mUserEmail = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_bmilist);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mUserEmail = bundle.getString("email");
        }
        //Create dummy data until database is ready .. Add to activity
       // BMIResult[] results = {new BMIResult(5.5,100),new BMIResult(4.3,156)};
        InClassDatabaseHelper helper = new InClassDatabaseHelper(BMIListActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor resultSet = db.rawQuery("Select BMI,DATE from "+InClassDatabaseHelper.BMI_LIST_TABLE_NAME+" where EMAIL='" + mUserEmail +"'",null);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DecimalFormat df2 = new DecimalFormat(".##");
        while(resultSet.moveToNext()) {
            BMIResult bmi = new BMIResult();
            double bmiValue = Double.parseDouble(resultSet.getString(0));
            bmi.setBmi(Double.parseDouble(df2.format(bmiValue)));
            bmi.setDate(resultSet.getString(1));
            results.add(bmi);
        }


        //Add to onCreate to initialise the list
        ListView listBMIResults = getListView();
        ArrayAdapter<BMIResult> listAdapter = new ArrayAdapter<BMIResult>(this,android.R.layout.simple_list_item_1,results);
        listBMIResults.setAdapter(listAdapter);
    }

    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        //System.out.println("Clicked on" + results[position].toString());
    }
}
