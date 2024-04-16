package com.example.gym;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
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

import androidx.appcompat.app.AppCompatActivity;

import com.example.gym.R.id;
import com.example.gym.utils.Exercise;

import java.util.ArrayList;
import java.util.List;

public class DetailedExerciseActivity extends AppCompatActivity {

    private LinearLayout seriesLayout;
    private int seriesCounter = 1;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_exercise);
        seriesLayout = findViewById(R.id.seriesLayout);

        Exercise exercise = (Exercise) getIntent().getSerializableExtra("exercise");

        TextView textViewNombre = findViewById(id.textViewName);
        TextView textViewGrupoMuscular = findViewById(R.id.textViewMuscleGroup);
        TextView textViewDescription = findViewById(R.id.textViewDescription);


        if (exercise != null) {
            textViewNombre.setText(exercise.getName());
            textViewGrupoMuscular.setText(exercise.getMuscleGroup());
            textViewDescription.setText("Descripción del ejercicio: " + exercise.getDescription());
        }

        Button buttonSelect = findViewById(R.id.buttonSelect);
        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailedExerciseActivity.this, "Error: feo",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSeries(); // Agregar una nueva serie cuando se presiona el botón "+"
            }
        });

        Button buttonSave = new Button(this);
        buttonSave.setText("Guardar");
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> repetitionsList = new ArrayList<>();

                // Iterar sobre los elementos dentro del LinearLayout
                for (int i = 0; i < seriesLayout.getChildCount(); i++) {
                    View seriesView = seriesLayout.getChildAt(i);
                    if (seriesView instanceof LinearLayout) {
                        LinearLayout seriesLayout = (LinearLayout) seriesView;
                        EditText editTextRepetitions = (EditText) seriesLayout.getChildAt(i);
                        String repetitions = editTextRepetitions.getText().toString();
                        repetitionsList.add(repetitions);
                    }
                }

                StringBuilder sb = new StringBuilder();
                for (String repetitions : repetitionsList) {
                    sb.append(repetitions).append("\n");
                }
                Toast.makeText(getApplicationContext(), "Repeticiones:\n" + sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addSeries() {
        LinearLayout newSeriesLayout = new LinearLayout(this);
        newSeriesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newSeriesLayout.setOrientation(LinearLayout.HORIZONTAL);
        newSeriesLayout.setGravity(Gravity.CENTER_VERTICAL);


        Drawable border = getResources().getDrawable(R.drawable.border);

        TextView textViewCounter = new TextView(this);
        textViewCounter.setText(String.valueOf(seriesCounter));
        textViewCounter.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        textViewCounter.setGravity(Gravity.CENTER);

        EditText editTextRepetitions = new EditText(this);
        editTextRepetitions.setHint("Repeticiones");
        editTextRepetitions.setInputType(InputType.TYPE_CLASS_NUMBER);
        editTextRepetitions.setSingleLine();
        editTextRepetitions.setBackground(border);

        EditText editTextWeight = new EditText(this);
        editTextWeight.setHint("Peso kg");
        editTextWeight.setInputType(InputType.TYPE_CLASS_NUMBER
                | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editTextWeight.setSingleLine();
        editTextWeight.setBackground(border);

        newSeriesLayout.addView(textViewCounter);
        newSeriesLayout.addView(editTextRepetitions);
        newSeriesLayout.addView(editTextWeight);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 8, 0, 0);
        newSeriesLayout.setLayoutParams(layoutParams);

        seriesLayout.addView(newSeriesLayout);

        seriesCounter++;
    }


}
