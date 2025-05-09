package com.edumate.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class PreferencesHelper(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("eduMatePrefs", Context.MODE_PRIVATE)
    private val EMAIL_KEY = "email"
    private val PASSWORD_KEY = "password"
    private val IS_LOGGED_IN_KEY = "isLoggedIn"
    private val LANGUAGE_KEY = "selected_language"
    private val DARK_MODE_KEY = "dark_mode"
    private val REMEMBER_ME_KEY = "remember_me"

    init {
        // Debug log
        Log.d("PreferencesHelper", "SharedPreferences initialized")

}
    // Menyimpan email ke SharedPreferences
    fun setEmail(email: String) {
        prefs.edit().putString(EMAIL_KEY, email).apply()
    }

    // Menyimpan password ke SharedPreferences
    fun setPassword(password: String) {
        prefs.edit().putString(PASSWORD_KEY, password).apply()
    }

    fun getEmail(): String? {
        return prefs.getString(EMAIL_KEY, null)
    }

    fun getPassword(): String? {
        return prefs.getString(PASSWORD_KEY, null)
    }
    // Save and retrieve selected language
    fun setSelectedLanguage(languageCode: String) {
        prefs.edit().putString("selected_language", languageCode).apply()
    }

    fun getSelectedLanguage(): String? {
        return prefs.getString("selected_language", "id") // Default to "id"
    }

    // Dark Mode
    fun isDarkModeEnabled(): Boolean {
        return prefs.getBoolean("dark_mode", false)
    }

    fun setDarkModeEnabled(enabled: Boolean) {
        prefs.edit().putBoolean("dark_mode", enabled).apply()
    }

    // Simpan Email dan Password
    fun saveUserEmail(email: String) {
        prefs.edit().putString("user_email", email).apply()
    }

    fun saveUserPassword(password: String) {
        prefs.edit().putString("user_password", password).apply()
    }

    fun saveUserCredentials(email: String, password: String) {
        prefs.edit().putString("user_email", email)
            .putString("user_password", password)
            .apply()
    }

    // Menyimpan status login atau preferensi lainnya
    // Misalnya: menyimpan apakah pengguna telah login
    fun setLoggedIn(isLoggedIn: Boolean) {
        prefs.edit().putBoolean("isLoggedIn", isLoggedIn).apply()
    }

    // Mendapatkan status login
    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("isLoggedIn", false)
    }

    // Remember Me
    fun isRememberMeEnabled(): Boolean {
        return prefs.getBoolean("remember_me", false)
    }

    fun setRememberMeEnabled(enabled: Boolean) {
        prefs.edit().putBoolean("remember_me", enabled).apply()
    }

    fun setRememberMe(isRemembered: Boolean) {
        prefs.edit().putBoolean("remember_me", isRemembered).apply()
    }

    fun isRememberMe(): Boolean {
        return prefs.getBoolean("remember_me", false)
    }

    fun resetRememberMe() {
        prefs.edit().remove("remember_me").apply()
    }

    fun clearLoginData() {
        prefs.edit()
            .remove("user_email")
            .remove("user_password")
            .remove("logged_in")
            .remove("remember_me")
            .apply()
    }

    fun clearUserData() {
        clearLoginData()
    }
}
