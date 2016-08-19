package com.example.ashish.alumini.supporting_classes;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ashish.alumini.R;

/**
 * Created by ashish on 17/8/16.
 */
public class GlobalPrefs {
    public static Context mContext;
    SharedPreferences.Editor mEditor;

    public GlobalPrefs(Context context){
        mContext = context;
    }

    public static void putString(String key, String value){
        SharedPreferences.Editor editor = mContext.getSharedPreferences(
                mContext.getString(R.string.preference_file_key), Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();

    }
    public static String getString(String preferenceKey){
        String value = "";

        return value;
    }

    public static void putBooloean(String key, boolean value){
        SharedPreferences.Editor editor = mContext.getSharedPreferences(
                mContext.getString(R.string.preference_file_key), Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(String key){
        SharedPreferences preferences = mContext.getSharedPreferences(mContext.getString(R.string.preference_file_key)
        , Context.MODE_PRIVATE);
        return preferences.getBoolean(key,false);
    }
}
