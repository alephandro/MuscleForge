package com.example.gym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button viewWorkoutsButton = findViewById(R.id.viewWorkoutsButton);
        Button viewExercisesButton = findViewById(R.id.viewExercisesButton);

        /*buttonAddTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, NewExerciseActivity.class));
            }
        });*/

        viewWorkoutsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, ConsultHistoryActivity.class));
            }
        });

        viewExercisesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, ViewExercisesActivity.class));
            }
        });

        /*buttonDeleteTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, ViewYourTrainingsActivity.class));
            }
        });*/
    }
}
