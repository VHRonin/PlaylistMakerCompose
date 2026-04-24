package com.example.playlistmakercompose.ui.viewmodel

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.core.content.edit

class ThemeViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {
    var darkTheme by mutableStateOf(
        sharedPreferences.getBoolean(DARK_THEME_KEY, false)
    )

    fun switchTheme(darkThemeEnabled: Boolean){
        darkTheme = darkThemeEnabled
        sharedPreferences.edit {
            putBoolean(DARK_THEME_KEY, darkThemeEnabled)
        }
    }
    companion object{
        const val APP_PREFERENCES = "app_preferences"
        const val DARK_THEME_KEY = "dark_theme"
    }
}

//private const val APP_PREFERENCES = "app_preferences"
//private const val DARK_THEME_KEY = "dark_theme"
//class App : Application() {
//    var darkTheme = false
//    lateinit var sharedPrefs: SharedPreferences
//
//    override fun onCreate() {
//        super.onCreate()
//
//        sharedPrefs = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
//
//        darkTheme = sharedPrefs.getBoolean(DARK_THEME_KEY, false)
//
//        switchTheme(darkTheme)
//    }
//
//    fun switchTheme(darkThemeEnabled: Boolean){
//        darkTheme = darkThemeEnabled
//        AppCompatDelegate.setDefaultNightMode(
//            if (darkThemeEnabled){
//                AppCompatDelegate.MODE_NIGHT_YES
//            }
//            else{
//                AppCompatDelegate.MODE_NIGHT_NO
//            }
//        )
//
//        sharedPrefs.edit()
//            .putBoolean(DARK_THEME_KEY, darkThemeEnabled)
//            .apply()
//    }
//}