package com.example.gym.utils;

import java.io.Serializable;

public class Series implements Serializable {

    private int reps;
    private float weight;

    public Series (String reps, String weight) {
        try {
            this.reps = Integer.parseInt(reps);
        } catch (NumberFormatException e) {
            this.reps = 0;
        }
        try {
            this.weight = Float.parseFloat(weight);
        } catch (NumberFormatException | NullPointerException e) {
            this.weight = 0;
        }
    }

    @Override
    public String toString() {
        return (this.reps + " x " + this.weight + "kg");
    }
}
