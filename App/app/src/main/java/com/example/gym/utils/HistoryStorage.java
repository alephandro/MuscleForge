package com.example.gym.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class HistoryStorage {

    private static final String PREF_NAME = "HistoryPreferences";
    private static final String KEY_HISTORY = "serializedHistory";

    public static void saveHistory(Context context, History history) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String serializedHistory = gson.toJson(history);

        editor.putString(KEY_HISTORY, serializedHistory);
        editor.apply();
    }

    public static History getHistory(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String serializedHistory = sharedPreferences.getString(KEY_HISTORY, null);

        if (serializedHistory != null) {
            Gson gson = new Gson();
            return gson.fromJson(serializedHistory, History.class);
        }

        return null;
    }

}
