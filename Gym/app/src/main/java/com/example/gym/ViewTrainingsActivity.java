package com.example.gym;

import android.content.Intent;
import android.os.Bundle;

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

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar lista de entrenamientos
        trainings = new ArrayList<>();

        /**IMPLEMENTAR LOGICA DE LLENAR ARRAYLIST**/

        Exercise ej1 = new Exercise("Flexiones", "Pecho");

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
