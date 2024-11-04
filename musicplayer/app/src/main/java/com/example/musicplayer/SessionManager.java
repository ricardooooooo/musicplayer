package com.example.musicplayer;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private SessionManager() {}

    private static SharedPreferences sharedPreferences;
    private static final String FILENAME = "sessionSharedPrefs";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USER_NAME = "name";
    private static final String KEY_USER_EMAIL = "email";


    private static SharedPreferences getSharedPreferences(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getApplicationContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }


    public static void logout(Context context) {
        getSharedPreferences(context).edit().clear().apply();
    }

    public static User getActiveSession(Context context) {
        if (getSharedPreferences(context).contains(KEY_USER_EMAIL)) {
            SharedPreferences sharedPreferences = getSharedPreferences(context);
            long id = sharedPreferences.getLong(KEY_USER_ID, 0);
            String name = sharedPreferences.getString(KEY_USER_NAME, "");
            String email = sharedPreferences.getString(KEY_USER_EMAIL, "");
            User user = new User(id, name, email, "");
            return user;
        }
        return null;
    }

    public static void saveSession(Context context, User user) {
        getSharedPreferences(context).edit()
                .putLong(KEY_USER_ID, user.getUserId())
                .putString(KEY_USER_NAME, user.getName())
                .putString(KEY_USER_EMAIL, user.getEmail())
                .apply();
    }
}
