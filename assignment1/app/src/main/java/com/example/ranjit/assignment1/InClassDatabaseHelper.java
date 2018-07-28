package com.example.ranjit.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class InClassDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "inclass"; // name of the DB
    private static final int DB_VERSION = 1; // version of the DB
    public static final String TABLE_NAME = "PERSON"; // name of the Table
    public static final String BMI_LIST_TABLE_NAME = "BMI"; // name of the Table
    public InClassDatabaseHelper(Context context){
        super(context,DB_NAME,null, DB_VERSION); // null is for cursors
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("onCreate InClassDatabaseHelper");
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "EMAIL TEXT,"
                + "NAME TEXT, "
                + "PASSWORD TEXT, " // Never store passwords in clear text in real apps
                + "HEALTH_CARD_NUMB TEXT, "
                + "DATE INTEGER);");
        Date today = new Date(); // we want to start with some initial data
        ContentValues personValues = new ContentValues();
        personValues.put("EMAIL", "ranjit@test.com");
        personValues.put("NAME", "Ranjit Mishra");
        personValues.put("PASSWORD", "SuperSecret");
        personValues.put("HEALTH_CARD_NUMB", "123456789101");
        personValues.put("DATE", today.getTime());
        db.insert(TABLE_NAME,null, personValues);

        db.execSQL("CREATE TABLE "+BMI_LIST_TABLE_NAME+" ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "EMAIL TEXT,"
                + "HEIGHT TEXT,"
                + "WEIGHT TEXT,"
                + "BMI TEXT,"
                + "DATE DATE);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
