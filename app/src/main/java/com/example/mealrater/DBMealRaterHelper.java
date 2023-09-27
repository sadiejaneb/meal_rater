package com.example.mealrater;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBMealRaterHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mealRater.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_MEALS = "meals";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_RESTAURANT = "restaurant";
    public static final String COLUMN_DISH = "dish";
    public static final String COLUMN_RATING = "rating";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_MEALS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_RESTAURANT + " TEXT, " +
                    COLUMN_DISH + " TEXT, " +
                    COLUMN_RATING + " FLOAT);";

    public DBMealRaterHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEALS);
        onCreate(db);
    }
}
