package com.example.gym.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ExerciseStorage {

    private static final String PREF_NAME = "ExercisesPreferences";
    private static final String KEY_EXERCISES = "serializedExercises";

    public static void saveExercises(Context context, ArrayList<Exercise> exercises) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String serializedExercises = gson.toJson(exercises);

        editor.putString(KEY_EXERCISES, serializedExercises);
        editor.apply();
    }

    public static ArrayList getExercises(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String serializedExercises = sharedPreferences.getString(KEY_EXERCISES, null);

        if (serializedExercises != null) {
            Gson gson = new Gson();
            // Provide the correct type information to Gson
            Type exerciseListType = new TypeToken<ArrayList<Exercise>>(){}.getType();
            return gson.fromJson(serializedExercises, exerciseListType);
        }

        return null;
    }

}
