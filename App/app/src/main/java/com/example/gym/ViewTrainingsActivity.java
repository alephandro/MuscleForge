package com.example.gym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym.utils.Client;
import com.example.gym.utils.Exercise;
import com.example.gym.utils.ExerciseAdapter;
import com.example.gym.utils.ExerciseStorage;
import com.example.gym.utils.OldExerciseAdapter;
import com.example.gym.utils.Workout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewTrainingsActivity extends AppCompatActivity {

    private Workout workout;
    RecyclerView recyclerView;
    List exercises = new ArrayList<>();
    OldExerciseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trainings);

        workout = (Workout) getIntent().getSerializableExtra("workout");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        exercises = new ArrayList<>();

        Client client = new Client();
        Object object = client.sendMessage("SELECT * FROM exercises");
        client.close();

        System.out.println(object.getClass());

        if(object.getClass().equals(ArrayList.class)) {
            this.exercises = (ArrayList) object;
            ArrayList<Exercise> myExercises = ExerciseStorage.getExercises(ViewTrainingsActivity.this);
            if(myExercises == null)
                myExercises = new ArrayList<>();

            this.exercises.addAll(myExercises);
        }
        Collections.sort(exercises);

        Button buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        adapter = new OldExerciseAdapter(exercises);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OldExerciseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Exercise exercise) {
                // Abrir la nueva actividad cuando se hace clic en un elemento de la lista
                Intent intent = new Intent(ViewTrainingsActivity.this,
                        DetailedExerciseActivity.class);
                intent.putExtra("exercise", exercise);
                intent.putExtra("workout", workout);
                startActivity(intent);
                finish();
            }
        });
    }
}
