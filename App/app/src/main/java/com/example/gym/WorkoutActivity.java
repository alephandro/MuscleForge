package com.example.gym;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gym.utils.PerformedExercise;
import com.example.gym.utils.Workout;

import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity {

    private Workout workout;
    private LinearLayout performedExercisesLayout;
    private int performedExercisesCounter = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        performedExercisesLayout = findViewById(R.id.performedExercisesLayout);
        workout = (Workout) getIntent().getSerializableExtra("workout");

        TextView textViewName = findViewById(R.id.textViewName);
        TextView textViewDate = findViewById(R.id.textViewDate);

        if (workout != null) {
            textViewName.setText(workout.getName());
            textViewDate.setText(workout.getDate().toString());
        }

        //Every exercise
        for (PerformedExercise pe : workout.getPerformedExercises()) {

            //Linear layout
            LinearLayout newExerciseLayout = new LinearLayout(this);
            newExerciseLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            newExerciseLayout.setOrientation(LinearLayout.HORIZONTAL);
            newExerciseLayout.setGravity(Gravity.CENTER_VERTICAL);
            Drawable border = getResources().getDrawable(R.drawable.border);

            newExerciseLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(WorkoutActivity.this,
                            DetailedExerciseActivity.class);
                    workout.removePerformedExercise(pe);
                    intent.putExtra("performedExercise", pe);
                    intent.putExtra("workout", workout);
                    startActivity(intent);
                    finish();
                }
            });

            //Performed exercise counter
            TextView textViewCounter = new TextView(this);
            textViewCounter.setText(String.valueOf(performedExercisesCounter));
            textViewCounter.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
            textViewCounter.setGravity(Gravity.CENTER);

            //Exercise
            TextView textViewPerformedExercise = new TextView(this);
            textViewPerformedExercise.setText(pe.toString());

            //Delete button
            Button deleteButton = new Button(this);
            deleteButton.setText("X");
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    performedExercisesLayout.removeView(newExerciseLayout);
                    String posS = ((TextView)newExerciseLayout.getChildAt(0)).getText().toString();
                    int pos = Integer.parseInt(posS);

                    int aux = 1;
                    for(int i = 0; i < performedExercisesLayout.getChildCount(); i++) {
                        LinearLayout l = (LinearLayout)performedExercisesLayout.getChildAt(i);
                        ((TextView)l.getChildAt(0)).setText(String.valueOf(aux++));
                    }
                    workout.removePerformedExercise(pos - 1);
                }
            });

            newExerciseLayout.addView(textViewCounter);
            newExerciseLayout.addView(textViewPerformedExercise);
            newExerciseLayout.addView(deleteButton);
            performedExercisesLayout.addView(newExerciseLayout);

            performedExercisesCounter++;
        }

        //Add button
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

        Button buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this,
                        ConsultHistoryActivity.class);
                intent.putExtra("workout", workout);
                intent.putExtra("delete", "delete");
                startActivity(intent);
                finish();
            }
        });
    }
}
