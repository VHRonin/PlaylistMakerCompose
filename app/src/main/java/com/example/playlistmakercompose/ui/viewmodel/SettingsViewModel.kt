package com.example.playlistmakercompose.ui.viewmodel
//
//import android.content.Context
//import android.content.Intent
//import androidx.core.net.toUri
//import androidx.lifecycle.ViewModel
//import com.example.playlistmakercompose.R
//
//class SettingsViewModel(private val context: Context) : ViewModel() {
//    fun shareApp(context: Context){
//        val linkToCourse = context.getString(R.string.course_link)
//
//        val shareIntent = Intent(Intent.ACTION_SEND)
//        shareIntent.type = context.getString(R.string.share_intent_type)
//        shareIntent.putExtra(Intent.EXTRA_TEXT, linkToCourse)
//
//        context.startActivity(shareIntent)
//    }
//
//    fun textSupport(){
//        val supportIntent = Intent(Intent.ACTION_SENDTO)
//
//        supportIntent.data = context.getString(R.string.support_intent_data).toUri()
//        supportIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(context.getString(R.string.email)))
//        supportIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.mail_theme))
//        supportIntent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.mail_body))
//
//        context.startActivity(supportIntent)
//    }
//
//    fun openUserAgreement(){
//        val agreementIntent = Intent(Intent.ACTION_VIEW)
//        agreementIntent.data = context.getString(R.string.agreement_link).toUri()
//
//        context.startActivity(agreementIntent)
//    }
//
//}