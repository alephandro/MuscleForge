package com.example.gym.utils;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Workout implements Serializable {

    private String name;
    private Date date;

    private ArrayList<PerformedExercise> performedExercises;

    public Workout() {
        this.date = new Date();
        this.name = "Entrenamiento " + date.toString().substring(0, 10) + " " + date.toString().substring(30, 34);
        this.performedExercises = new ArrayList<>();
    }

    public void addPerformedExercise(PerformedExercise performedExercise) {
        performedExercises.add(performedExercise);
    }

    public void removePerformedExercise(int position) {
        performedExercises.remove(position);
    }

    public String toString() {
        String string = "";
        for (PerformedExercise pe : performedExercises) {
            string.concat(pe.toString() + "\n");
        }
        return string;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<PerformedExercise> getPerformedExercises() {
        return performedExercises;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof Workout workout) {
            return workout.getDate().equals(this.date);
        } else
            return super.equals(obj);
    }

}
