package com.example.gym.utils;

import java.util.ArrayList;

public class History {

    private ArrayList<Workout> workouts;

    public History() {
        workouts = new ArrayList<>();
    }

    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }

    public void addWorkout(Workout workout) {
        workouts.add(workout);
    }

    public void removeWorkout(int position) {
        workouts.remove(position);
    }

}
