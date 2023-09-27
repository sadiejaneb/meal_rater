package com.example.mealrater;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextDish;
    private EditText editTextRestaurant;
    private RatingBar mainRatingBar;
    MealRaterDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDish = findViewById(R.id.editTextDish);
        editTextRestaurant = findViewById(R.id.editTextRestaurant);
        mainRatingBar = findViewById(R.id.ratingBar);

        Button rateButton = findViewById(R.id.rateButton);
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRatingDialog();
            }
        });

        dataSource = new MealRaterDataSource(this);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String restaurant = editTextRestaurant.getText().toString();
                String dish = editTextDish.getText().toString();
                float rating = mainRatingBar.getRating();

                // Assuming createMeal returns true if the operation was successful.
                boolean isSaved = dataSource.createMeal(restaurant, dish, rating);

                if (isSaved) {
                    // Show a Toast message for successful saving
                    Toast.makeText(MainActivity.this, "Data stored successfully!", Toast.LENGTH_SHORT).show();

                    // Clear the EditText fields and reset the rating
                    editTextRestaurant.setText("");
                    editTextDish.setText("");
                    mainRatingBar.setRating(0);
                } else {
                    Toast.makeText(MainActivity.this, "Error saving data!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void openRatingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.rating_dialog, null);
        builder.setView(dialogView);

        final RatingBar dialogRatingBar = dialogView.findViewById(R.id.dialogRatingBar);
        Button saveButton = dialogView.findViewById(R.id.saveButton);

        final AlertDialog alertDialog = builder.create();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainRatingBar.setRating(dialogRatingBar.getRating());
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        dataSource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }

}
