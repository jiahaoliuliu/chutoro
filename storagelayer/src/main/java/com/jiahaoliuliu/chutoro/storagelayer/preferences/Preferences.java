package com.jiahaoliuliu.chutoro.storagelayer.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private static final String FILE_NAME = "Preferences.xml";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public Preferences(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // Long
    public long get(@PreferencesKey.LongKey String longKey, long defaultValue) {
        return sharedPreferences.getLong(longKey, defaultValue);
    }

    public void set(@PreferencesKey.LongKey String longKey, long value) {
        editor.putLong(longKey, value);
        editor.commit();
    }

    // Boolean
    public boolean get(@PreferencesKey.BooleanKey String longKey, boolean defaultValue) {
        return sharedPreferences.getBoolean(longKey, defaultValue);
    }

    public void set(@PreferencesKey.BooleanKey String longKey, boolean value) {
        editor.putBoolean(longKey, value);
        editor.commit();
    }
}
