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
import com.example.gym.utils.ExerciseStorage;
import com.example.gym.utils.OldWorkoutAdapter;
import com.example.gym.utils.Workout;

import java.util.ArrayList;
import java.util.List;

public class ViewTrainingsActivity extends AppCompatActivity {

    private Workout workout;
    RecyclerView recyclerView;
    List trainings = new ArrayList<>();
    OldWorkoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trainings);

        workout = (Workout) getIntent().getSerializableExtra("workout");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        trainings = new ArrayList<>();

        Client client = new Client();
        Object object = client.sendMessage("SELECT * FROM exercises");
        client.close();

        System.out.println(object.getClass());

        if(object.getClass().equals(ArrayList.class)) {
            this.trainings = (ArrayList) object;
            ArrayList<Exercise> exercises = ExerciseStorage.getExercises(ViewTrainingsActivity.this);
            if(exercises == null)
                exercises = new ArrayList<>();

            this.trainings.addAll(exercises);
        }

        Button buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        adapter = new OldWorkoutAdapter(trainings);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OldWorkoutAdapter.OnItemClickListener() {
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
