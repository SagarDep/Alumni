package com.example.ashish.alumini.supporting_classes;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ashish.alumini.R;

/**
 * Created by ashish on 17/8/16.
 */
public class GlobalPrefs {
    static Context mContext;
    SharedPreferences.Editor mEditor;

    GlobalPrefs(Context context){
        mContext = context;
    }

    static void putString(String key, String value){
        SharedPreferences.Editor editor = mContext.getSharedPreferences(
                mContext.getString(R.string.preference_file_key), Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();

    }
    static String getString(String preferenceKey){
        String value = "";

        return value;
    }
}
