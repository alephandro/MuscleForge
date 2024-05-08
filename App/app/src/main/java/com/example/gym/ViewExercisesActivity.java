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
import com.example.gym.utils.Workout;

import java.util.ArrayList;
import java.util.Collections;

public class ViewExercisesActivity extends AppCompatActivity implements ExerciseAdapter.OnDeleteClickListener {

    ArrayList<Exercise> exercises = new ArrayList<>();
    RecyclerView recyclerView;
    ExerciseAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercises);

        //Get recyclerView (workout list)
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Get exercises
        //Get exercises from database
        Client client = new Client();
        Object object = client.sendMessage("SELECT * FROM exercises");
        client.close();

        if(object.getClass().equals(ArrayList.class))
            this.exercises.addAll((ArrayList) object);

        //Get exercises from local storage
        ArrayList<Exercise> myExercises = ExerciseStorage.getExercises(ViewExercisesActivity.this);
        if(myExercises != null)
            this.exercises.addAll(myExercises);

        //Sort exercises by alphabetical order
        Collections.sort(exercises);

        //Set the adapter
        adapter = new ExerciseAdapter(exercises, this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ExerciseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Exercise exercise) {
                Intent intent = new Intent(
                        ViewExercisesActivity.this,
                        NewExerciseActivity.class);
                intent.putExtra("exercise", exercise);
                startActivity(intent);
                finish();
            }
        });

        //Add button
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewExercisesActivity.this, NewExerciseActivity.class));
                finish();
            }
        });

        //Back button
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onDeleteClick(int position) {
        exercises.remove(position);
        ExerciseStorage.saveExercises(this, exercises);
        adapter.notifyItemRemoved(position);
    }
}
