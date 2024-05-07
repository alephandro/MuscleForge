package com.example.gym;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gym.utils.Exercise;
import com.example.gym.utils.ExerciseStorage;

import java.util.ArrayList;

public class NewExerciseActivity extends AppCompatActivity {

    Exercise exercise;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);

        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameEditText = findViewById(R.id.editTextName);
                EditText muscleGroupEditText = findViewById(R.id.editTextMuscleGroup);
                EditText descriptionEditText = findViewById(R.id.editTextDescription);

                exercise = new Exercise(
                        nameEditText.getText().toString(),
                        muscleGroupEditText.getText().toString(),
                        descriptionEditText.getText().toString(),
                        false);

                ArrayList<Exercise> exercises = ExerciseStorage.getExercises(NewExerciseActivity.this);
                if(exercises == null)
                    exercises = new ArrayList<>();
                exercises.add(exercise);
                ExerciseStorage.saveExercises(NewExerciseActivity.this, exercises);

                startActivity(new Intent(NewExerciseActivity.this, ViewExercisesActivity.class));
                finish();
            }
        });
    }
}
