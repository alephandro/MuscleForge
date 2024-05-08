package com.example.gym.utils;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Exercise implements Serializable, Comparable {

    private String name;
    private String muscleGroup;
    private String description;
    private boolean isDefault;

    public Exercise(String name, String muscleGroup, String description, boolean isDefault) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.description = description;
        this.isDefault = isDefault;
    }

    public String getName() {
        return name;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDefault() {
        return isDefault;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof Exercise exercise)
            return this.name.equals(exercise.name);
        return super.equals(obj);
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Exercise e)
            return this.name.toLowerCase().compareTo(e.name.toLowerCase());
        return 0;
    }
}
