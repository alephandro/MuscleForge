package com.example.gym;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym.utils.Exercise;
import com.example.gym.utils.ExerciseAdapter;
import com.example.gym.utils.ExerciseStorage;

import java.util.ArrayList;
import java.util.List;

public class ViewYourTrainingsActivity extends AppCompatActivity {

    ArrayList<Exercise> exercises = new ArrayList<>();
    private LinearLayout exercisesLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_your_trainings);
        exercisesLayout = findViewById(R.id.exercisesLayout);

        //Get your exercises
        exercises = ExerciseStorage.getExercises(ViewYourTrainingsActivity.this);
        if(exercises == null)
            exercises = new ArrayList<>();

        for(Exercise exercise : exercises) {
            //Linear layout
            LinearLayout thisExerciseLayout = new LinearLayout(this);
            thisExerciseLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            //Exercise name
            TextView exerciseName = new TextView(this);
            exerciseName.setText(exercise.getName() + ": ");

            //Exercise Muscle Group
            TextView exerciseGroup = new TextView(this);
            exerciseGroup.setText(exercise.getMuscleGroup());

            //Delete button
            Button deleteButton = new Button(this);
            deleteButton.setText("X");
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exercises.remove(exercise);
                    exercisesLayout.removeView(thisExerciseLayout);
                    ExerciseStorage.saveExercises(ViewYourTrainingsActivity.this, exercises);
                }
            });

            thisExerciseLayout.addView(exerciseName);
            thisExerciseLayout.addView(exerciseGroup);
            thisExerciseLayout.addView(deleteButton);
            exercisesLayout.addView(thisExerciseLayout);
        }

        Button buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
