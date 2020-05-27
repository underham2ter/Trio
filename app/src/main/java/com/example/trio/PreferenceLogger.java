package com.example.trio;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class PreferenceLogger {
    public static final String PREFERENCE_NAME = "LOGGED";
    private final SharedPreferences sharedpreferences;

    public PreferenceLogger(Context context) {
        sharedpreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public int getValue() {
        int count = sharedpreferences.getInt("isLogged", -1);
        return count;
    }

    public void setValue(int value) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("isLogged", value);
        editor.commit();
        Log.e("SET111",sharedpreferences.getInt("isLogged", -1)+"");
    }

    public void clearValue() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove("isLogged");
        editor.commit();
    }
    public String  getName() {
        String userName = sharedpreferences.getString("name", "none");
        return userName;
    }

    public void setName(String name) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("name", name);
        editor.commit();
        Log.e("SET114",sharedpreferences.getString("name", "none")+"");
    }

    public void clearName() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove("name");
        editor.commit();
    }
}

