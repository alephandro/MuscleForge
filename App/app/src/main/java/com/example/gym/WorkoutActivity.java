package com.example.gym;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gym.utils.PerformedExercise;
import com.example.gym.utils.Workout;

import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity {

    private Workout workout;
    private LinearLayout performedExercisesLayout;
    private int performedExercisesCounter = 1;
    private ArrayList<PerformedExercise> performedExercisesList;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        performedExercisesLayout = findViewById(R.id.performedExercisesLayout);
        workout = (Workout) getIntent().getSerializableExtra("workout");
        performedExercisesList = workout.getPerformedExercises();

        TextView textViewName = findViewById(R.id.textViewName);
        TextView textViewDate = findViewById(R.id.textViewDate);

        if (workout != null) {
            textViewName.setText(workout.getName());
            textViewDate.setText(workout.getDate().toString());
        }

        for (PerformedExercise pe : performedExercisesList) {
            TextView textViewPerformedExercise = new TextView(this);
            textViewPerformedExercise.setText(pe.toString());
            performedExercisesLayout.addView(textViewPerformedExercise);
        }

        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this,
                        ViewTrainingsActivity.class);
                intent.putExtra("workout", workout);
                startActivity(intent);
                finish();
            }
        });

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this,
                        ConsultHistoryActivity.class);
                intent.putExtra("workout", workout);
                startActivity(intent);
                finish();
            }
        });
    }
}
