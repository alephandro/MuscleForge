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

        Button buttonAddTraining = findViewById(R.id.buttonAddTraining);
        Button buttonViewTrainings = findViewById(R.id.buttonViewTrainings);
        Button buttonDeleteTraining = findViewById(R.id.buttonDeleteTraining);

        buttonAddTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Agregar lógica para abrir la actividad de añadir entrenamiento
                // Por ejemplo:
                //startActivity(new Intent(MainMenuActivity.this, AddTrainingActivity.class));
            }
        });

        buttonViewTrainings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Agregar lógica para abrir la actividad de consultar entrenamientos
                // Por ejemplo:
                startActivity(new Intent(MainMenuActivity.this, ViewTrainingsActivity.class));
            }
        });

        buttonDeleteTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Agregar lógica para abrir la actividad de eliminar entrenamiento
                // Por ejemplo:
                //startActivity(new Intent(MainMenuActivity.this, DeleteTrainingActivity.class));
            }
        });
    }
}
