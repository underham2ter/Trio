package com.example.trio;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class PreferenceScore {
    public static final String PREFERENCE_NAME_2 = "SCORE";
    private final SharedPreferences sharedpreferences;

    public PreferenceScore(Context context) {
        sharedpreferences = context.getSharedPreferences(PREFERENCE_NAME_2, Context.MODE_PRIVATE);
    }

    public int getValue() {
        int count = sharedpreferences.getInt("score", 0);
        return count;
    }

    public void setValue(int value) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("score", value);
        editor.commit();
        Log.e("SET113",sharedpreferences.getInt("score", 0)+"");
    }

    public void clearValue() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove("score");
        editor.commit();
    }
}
