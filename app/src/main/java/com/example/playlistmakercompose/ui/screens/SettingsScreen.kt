package com.example.playlistmakercompose.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import com.example.playlistmakercompose.R

private fun shareApp(context: Context){
    val linkToCourse = context.getString(R.string.course_link)

    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = context.getString(R.string.share_intent_type)
    shareIntent.putExtra(Intent.EXTRA_TEXT, linkToCourse)

    context.startActivity(shareIntent)
}

private fun textSupport(context: Context){
    val supportIntent = Intent(Intent.ACTION_SENDTO)

    supportIntent.data = context.getString(R.string.support_intent_data).toUri()
    supportIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(context.getString(R.string.email)))
    supportIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.mail_theme))
    supportIntent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.mail_body))

    context.startActivity(supportIntent)
}

private fun openUserAgreement(context: Context){
    val agreementIntent = Intent(Intent.ACTION_VIEW)
    agreementIntent.data = context.getString(R.string.agreement_link).toUri()

    context.startActivity(agreementIntent)
}

@Composable
fun SettingsScreen(){
    val context = LocalContext.current
    var isChecked by remember { mutableStateOf(false)}

    Column(modifier = Modifier.fillMaxSize()) {

        // Spacer(modifier = Modifier.width(20.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(vertical = dimensionResource(R.dimen.big_size), horizontal = dimensionResource(
                R.dimen.normal_size))) {

            Text(
                text = stringResource(R.string.night_theme),
                style = MaterialTheme.typography.bodyLarge
            )

            Switch(
                checked = isChecked,
                onCheckedChange = {isChecked = it},

                )
        }

        SettingsItem(
            text = stringResource(R.string.share_application),
            iconRes = R.drawable.ic_share,
            onClick = {shareApp(context)})

        SettingsItem(
            text = stringResource(R.string.support),
            iconRes = R.drawable.ic_support,
            onClick = {textSupport(context)})

        SettingsItem(
            text = stringResource(R.string.user_agreement),
            iconRes = R.drawable.ic_forward_arrow,
            onClick = {openUserAgreement(context)})
    }
}

@Composable
fun SettingsItem(text: String, iconRes: Int, onClick: () -> Unit){
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable{onClick()}
            .padding(vertical = dimensionResource(R.dimen.big_size), horizontal = dimensionResource(R.dimen.normal_size))
    ) {

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )

        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = Color(0xFFAEAFB4))
    }
}
