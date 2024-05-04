package com.example.gym.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private ArrayList<Workout> workouts;

    public HistoryAdapter(ArrayList<Workout> workouts) {
        this.workouts = workouts;
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Workout workout);
    }

    public void setOnItemClickListener(HistoryAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        Workout workout = workouts.get(position);
        holder.bind(workout);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(workout);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNombre;
        private TextView textViewFecha;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewName);
            textViewFecha = itemView.findViewById(R.id.textViewDate);
        }

        public void bind(Workout workout) {
            textViewNombre.setText(workout.getName());
            textViewFecha.setText(workout.getDate().toString());
        }
    }

}
