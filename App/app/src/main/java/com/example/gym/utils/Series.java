package com.example.gym.utils;

public class Series {

    private Exercise exercise;
    private String reps;
    private String weight;

    public Series (Exercise exercise, String reps, String weight) {
        this.exercise = exercise;
        this.reps = reps;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return exercise.getName() + ": reps = " + this.reps + " and weight = " + this.weight + " kg";
    }
}
