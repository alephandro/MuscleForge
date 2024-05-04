package com.example.gym.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym.R;

import java.util.List;

public class OldWorkoutAdapter extends RecyclerView.Adapter<OldWorkoutAdapter.EntrenamientoViewHolder>{
    private List<Exercise> exercises;

    public OldWorkoutAdapter(List<Exercise> entrenamientos) {
        this.exercises = entrenamientos;
    }
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Exercise exercise);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public EntrenamientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_item, parent, false);
        return new EntrenamientoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntrenamientoViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        holder.bind(exercise);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(exercise);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public static class EntrenamientoViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNombre;
        private TextView textViewGrupoMuscular;

        public EntrenamientoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewName);
            textViewGrupoMuscular = itemView.findViewById(R.id.textViewMuscleGroup);
        }

        public void bind(Exercise exercise) {
            textViewNombre.setText(exercise.getName());
            textViewGrupoMuscular.setText(exercise.getMuscleGroup());
        }
    }
}
