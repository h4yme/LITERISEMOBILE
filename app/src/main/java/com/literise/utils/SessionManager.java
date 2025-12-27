package com.literise.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "LiteRiseSession";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_PHONE_NUMBER = "phoneNumber";
    private static final String KEY_FULL_NAME = "fullName";
    private static final String KEY_STUDENT_ID = "studentId";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(int userId, String phoneNumber, String fullName, int studentId) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putInt(KEY_USER_ID, userId);
        editor.putString(KEY_PHONE_NUMBER, phoneNumber);
        editor.putString(KEY_FULL_NAME, fullName);
        editor.putInt(KEY_STUDENT_ID, studentId);
        editor.apply();
    }

    /**
     * Check if user is logged in
     */
    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    /**
     * Get user ID
     */
    public int getUserId() {
        return prefs.getInt(KEY_USER_ID, -1);
    }

    /**
     * Get student ID
     */
    public int getStudentId() {
        return prefs.getInt(KEY_STUDENT_ID, -1);
    }

    /**
     * Get phone number
     */
    public String getPhoneNumber() {
        return prefs.getString(KEY_PHONE_NUMBER, null);
    }

    /**
     * Get full name
     */
    public String getFullName() {
        return prefs.getString(KEY_FULL_NAME, null);
    }

    /**
     * Logout user
     */
    public void logout() {
        editor.clear();
        editor.apply();
    }
}
