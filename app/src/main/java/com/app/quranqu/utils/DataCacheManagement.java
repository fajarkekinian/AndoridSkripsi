package com.app.quranqu.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by trian on 3/18/16.
 *
 */
public class DataCacheManagement {

    SharedPreferences sharePref;
    public static final String AUTH_TOKEN = "auth_token";

    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "myindihome_cache";
    public DataCacheManagement(Context context) {
        sharePref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    }

    public void setAuthToken(String s){
        SharedPreferences.Editor editor = sharePref.edit();
        editor.putString(AUTH_TOKEN,s);
    }
    public String get (){
        return sharePref.getString(AUTH_TOKEN, null);
    }
    public void clearCache() {
        SharedPreferences.Editor editor = sharePref.edit();
        editor.clear();
        editor.apply();
    }
}
