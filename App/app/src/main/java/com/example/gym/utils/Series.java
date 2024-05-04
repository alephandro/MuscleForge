package com.example.gym.utils;

import java.io.Serializable;

public class Series implements Serializable {

    private int reps;
    private float weight;

    public Series (String reps, String weight) {
        this.reps = Integer.parseInt(reps);
        this.weight = Float.parseFloat(weight);
    }

    @Override
    public String toString() {
        return (this.reps + " x " + this.weight + "kg");
    }
}
