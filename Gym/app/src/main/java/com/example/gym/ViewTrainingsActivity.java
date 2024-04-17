package com.example.gym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym.utils.Exercise;
import com.example.gym.utils.WorkoutAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewTrainingsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List trainings = new ArrayList<>();
    WorkoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trainings);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Button buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Inicializar lista de entrenamientos
        trainings = new ArrayList<>();

        /**IMPLEMENTAR LOGICA DE LLENAR ARRAYLIST**/

        Exercise ej1 = new Exercise("Flexiones", "Pecho", "Lorem ipsum " +
                "dolor sit amet, consectetur adipiscing elit. Suspendisse eu molestie orci. Sed vel " +
                "est tempus, ultrices enim at, suscipit ligula. Curabitur non sapien vitae l" +
                "orem commodo consequat id porttitor quam. Maecenas imperdiet congue dolor, vitae " +
                "vestibulum nulla sollicitudin in. Suspendisse et.");

        trainings.add(ej1);
        trainings.add(ej1);
        trainings.add(ej1);
        trainings.add(ej1);
        trainings.add(ej1);
        trainings.add(ej1);
        trainings.add(ej1);
        trainings.add(ej1);
        trainings.add(ej1);
        trainings.add(ej1);
        trainings.add(ej1);
        trainings.add(ej1);
        trainings.add(ej1);
        trainings.add(ej1);
        trainings.add(ej1);
        trainings.add(ej1);
        trainings.add(ej1);

        adapter = new WorkoutAdapter(trainings);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new WorkoutAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Exercise exercise) {
                // Abrir la nueva actividad cuando se hace clic en un elemento de la lista
                Intent intent = new Intent(ViewTrainingsActivity.this,
                        DetailedExerciseActivity.class);
                intent.putExtra("exercise", exercise);
                startActivity(intent);
            }
        });
    }
}
