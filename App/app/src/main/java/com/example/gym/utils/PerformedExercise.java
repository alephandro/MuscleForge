package com.example.gym.utils;

import java.io.Serializable;
import java.util.ArrayList;

public class PerformedExercise implements Serializable {

    private Exercise type;
    private ArrayList<Series> series;

    public PerformedExercise(Exercise type) {
        this.type = type;
        this.series = new ArrayList<>();
    }

    public void addSeries(Series serie) {
        series.add(serie);
    }

    public String toString() {
        String string = type.getName();
        for (Series series1 : this.series) {
            string += "\n" + series1.toString();
        }
        return string;
    }

}
