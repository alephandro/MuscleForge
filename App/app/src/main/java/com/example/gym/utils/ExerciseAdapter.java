package com.example.gym.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym.R;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private ArrayList<Exercise> exercises;
    private OnDeleteClickListener onDeleteClickListener;
    private OnItemClickListener onItemClickListener;


    public ExerciseAdapter(ArrayList<Exercise> exercises, OnDeleteClickListener listener) {
        this.exercises = exercises;
        this.onDeleteClickListener = listener;
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public interface OnItemClickListener {
        void onItemClick(Exercise exercise);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise, parent, false);
        return new ExerciseViewHolder(view, onDeleteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        holder.bind(exercise);
        if(!exercise.isDefault()) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(exercise);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView muscleGroup;
        private Button deleteButton;


        public ExerciseViewHolder(@NonNull View itemView, OnDeleteClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            muscleGroup = itemView.findViewById(R.id.muscleGroup);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                        listener.onDeleteClick(position);
                }
            });
        }

        public void bind(Exercise exercise) {
            name.setText(exercise.getName());
            muscleGroup.setText(exercise.getMuscleGroup());
            if(exercise.isDefault())
                deleteButton.setVisibility(View.INVISIBLE);
            else
                deleteButton.setVisibility(View.VISIBLE);
        }
    }

}
