package com.example.playlistmakercompose.ui.screens

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import com.example.playlistmakercompose.R
import com.example.playlistmakercompose.ui.viewmodel.ThemeViewModel
import androidx.core.content.edit

class SettingsScreenViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {
    var isChecked by mutableStateOf(false)
        private set

    fun toggleCheck(check: Boolean){
        isChecked = check
        sharedPreferences.edit {
            putBoolean(ThemeViewModel.DARK_THEME_KEY, check)
        }
    }
    fun shareApp(context: Context){
        val linkToCourse = context.getString(R.string.course_link)

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = context.getString(R.string.share_intent_type)
        shareIntent.putExtra(Intent.EXTRA_TEXT, linkToCourse)

        context.startActivity(shareIntent)
    }

    fun textSupport(context: Context){
        val supportIntent = Intent(Intent.ACTION_SENDTO)

        supportIntent.data = context.getString(R.string.support_intent_data).toUri()
        supportIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(context.getString(R.string.email)))
        supportIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.mail_theme))
        supportIntent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.mail_body))

        context.startActivity(supportIntent)
    }

    fun openUserAgreement(context: Context){
        val agreementIntent = Intent(Intent.ACTION_VIEW)
        agreementIntent.data = context.getString(R.string.agreement_link).toUri()

        context.startActivity(agreementIntent)
    }

    fun getTheme(){
        isChecked = sharedPreferences.getBoolean(ThemeViewModel.APP_PREFERENCES, false)
    }
}