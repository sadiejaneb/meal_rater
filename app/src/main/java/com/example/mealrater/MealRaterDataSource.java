package com.example.mealrater;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MealRaterDataSource {
    private SQLiteDatabase database;
    private DBMealRaterHelper dbHelper;

    public MealRaterDataSource(Context context) {
        dbHelper = new DBMealRaterHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean createMeal(String restaurant, String dish, float rating) {
        ContentValues values = new ContentValues();
        values.put(DBMealRaterHelper.COLUMN_RESTAURANT, restaurant);
        values.put(DBMealRaterHelper.COLUMN_DISH, dish);
        values.put(DBMealRaterHelper.COLUMN_RATING, rating);

        long newRowId = database.insert(DBMealRaterHelper.TABLE_MEALS, null, values);

        return newRowId != -1; // Will return true if insertion was successful, false otherwise
    }

}

