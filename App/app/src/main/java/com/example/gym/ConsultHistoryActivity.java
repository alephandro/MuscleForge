package com.example.gym;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym.utils.History;
import com.example.gym.utils.HistoryAdapter;
import com.example.gym.utils.HistoryStorage;
import com.example.gym.utils.Workout;

public class ConsultHistoryActivity extends AppCompatActivity {

    Workout tempWorkout;
    RecyclerView recyclerView;
    History history;
    private HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_history);

        //Get history
        history = HistoryStorage.getHistory(ConsultHistoryActivity.this);
        if(history == null)
            history = new History();

        //Get workout
        tempWorkout = (Workout) getIntent().getSerializableExtra("workout");
        if(tempWorkout != null) {
            history.addWorkout(tempWorkout);
            if(getIntent().getSerializableExtra("delete") != null) {
                history.removeWorkout(tempWorkout);
                Toast.makeText(ConsultHistoryActivity.this, "Entrenamiento borrado", Toast.LENGTH_SHORT).show();
            }

        }
        //Save history
        HistoryStorage.saveHistory(ConsultHistoryActivity.this, history);

        //Get recyclerView (workout list)
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Back button
        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //Add button
        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultHistoryActivity.this,
                        WorkoutActivity.class);
                intent.putExtra("workout", new Workout());
                startActivity(intent);
                finish();
            }
        });

        //ShowrecyclerView (workout list)
        adapter = new HistoryAdapter(history.getWorkouts());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new HistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Workout workout) {
                Intent intent = new Intent(ConsultHistoryActivity.this,
                        WorkoutActivity.class);
                intent.putExtra("workout", workout);
                startActivity(intent);
                finish();
            }
        });
    }

}
