package com.example.gym.utils;

import java.io.Serializable;

public class Exercise implements Serializable {

    private String name;
    private String muscleGroup;

    public Exercise(String name, String muscleGroup) {
        this.name = name;
        this.muscleGroup = muscleGroup;
    }

    public String getName() {
        return name;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }
}
