package com.example.gym;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.TypedValue;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gym.R.id;
import com.example.gym.utils.Exercise;
import com.example.gym.utils.PerformedExercise;
import com.example.gym.utils.Series;
import com.example.gym.utils.Workout;

import java.util.ArrayList;
import java.util.List;

public class DetailedExerciseActivity extends AppCompatActivity {

    private Workout workout;
    private Exercise exercise;
    private PerformedExercise performedExercise;
    private LinearLayout seriesLayout;
    private int seriesCounter = 1;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_exercise);
        seriesLayout = findViewById(R.id.seriesLayout);

        workout = (Workout) getIntent().getSerializableExtra("workout");
        exercise = (Exercise) getIntent().getSerializableExtra("exercise");
        performedExercise = (PerformedExercise) getIntent().getSerializableExtra("performedExercise");
        if(performedExercise != null) {
            exercise = performedExercise.getType();
            for (Series series : performedExercise.getSeries()) {
                addSeries("" + series.getReps(), "" + series.getWeight());
            }
        }

        TextView textViewNombre = findViewById(id.textViewName);
        TextView textViewGrupoMuscular = findViewById(R.id.textViewMuscleGroup);
        TextView textViewDescription = findViewById(R.id.textViewDescription);


        if (exercise != null) {
            textViewNombre.setText(exercise.getName());
            textViewGrupoMuscular.setText(exercise.getMuscleGroup());
            textViewDescription.setText(exercise.getDescription());
        }

        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSeries(null, null);
            }
        });

        Button buttonSave = findViewById(id.buttonSave);
        buttonSave.setText("Guardar");
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformedExercise performedExercise = new PerformedExercise(exercise);

                for (int i = 0; i < seriesLayout.getChildCount(); i++) {
                    View seriesView = seriesLayout.getChildAt(i);
                    if (seriesView instanceof LinearLayout) {
                        Series series;
                        LinearLayout seriesLayout = (LinearLayout) seriesView;
                        EditText reps = (EditText) seriesLayout.getChildAt(1);
                        EditText weight = (EditText) seriesLayout.getChildAt(2);
                        series = new Series(reps.getText().toString(), weight.getText().toString());
                        performedExercise.addSeries(series);
                    }
                }
                Toast.makeText(DetailedExerciseActivity.this, "Guardado correctamente!",
                        Toast.LENGTH_SHORT).show();
                workout.addPerformedExercise(performedExercise);

                Intent intent = new Intent(DetailedExerciseActivity.this,
                        WorkoutActivity.class);
                intent.putExtra("workout", workout);
                startActivity(intent);
                finish();
            }
        });

        Button cancelButton = findViewById(id.buttonCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailedExerciseActivity.this,
                        WorkoutActivity.class);
                workout.addPerformedExercise(performedExercise);
                intent.putExtra("workout", workout);
                startActivity(intent);
                finish();
            }
        });

    }

    private void addSeries(@Nullable String series, @Nullable String weight) {

        //Place everything
        LinearLayout newSeriesLayout = new LinearLayout(this);
        newSeriesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newSeriesLayout.setOrientation(LinearLayout.HORIZONTAL);
        newSeriesLayout.setGravity(Gravity.CENTER_VERTICAL);
        Drawable border = getResources().getDrawable(R.drawable.border);

        //Series counter
        TextView textViewCounter = new TextView(this);
        textViewCounter.setText(String.valueOf(seriesCounter));
        textViewCounter.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        textViewCounter.setGravity(Gravity.CENTER);

        //Repetitions TextBox
        EditText editTextRepetitions = new EditText(this);
        editTextRepetitions.setHint("Repeticiones");
        editTextRepetitions.setInputType(InputType.TYPE_CLASS_NUMBER);
        editTextRepetitions.setSingleLine();
        editTextRepetitions.setBackground(border);
        if(series != null)
            editTextRepetitions.setText(series);


        //Weight TextBox
        EditText editTextWeight = new EditText(this);
        editTextWeight.setHint("Peso kg");
        editTextWeight.setInputType(InputType.TYPE_CLASS_NUMBER
                | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editTextWeight.setSingleLine();
        editTextWeight.setBackground(border);
        if(weight != null)
            editTextWeight.setText(weight);


        //Delete button
        Button deleteButton = new Button(this);
        deleteButton.setText("X");
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seriesLayout.removeView(newSeriesLayout);
                seriesCounter--;
                int aux = 1;
                for(int i = 0; i < seriesLayout.getChildCount(); i++) {
                    LinearLayout l = (LinearLayout)seriesLayout.getChildAt(i);
                    ((TextView)l.getChildAt(0)).setText(String.valueOf(aux++));
                }
            }
        });

        //Add everything to rig
        newSeriesLayout.addView(textViewCounter);
        newSeriesLayout.addView(editTextRepetitions);
        newSeriesLayout.addView(editTextWeight);
        newSeriesLayout.addView(deleteButton);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 8, 0, 0);
        newSeriesLayout.setLayoutParams(layoutParams);

        seriesLayout.addView(newSeriesLayout);

        seriesCounter++;
    }

}
