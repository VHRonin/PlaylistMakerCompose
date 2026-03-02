package com.example.playlistmakercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playlistmakercompose.ui.theme.PlaylistMakerComposeTheme

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaylistMakerComposeTheme {
                Scaffold(topBar = {
                    Row(modifier = Modifier.fillMaxWidth().statusBarsPadding(),
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.ic_back_arrow),
                            contentDescription = null,
                            tint = colorResource(R.color.black),
                            modifier = Modifier.clickable{finish()}.padding(horizontal = dimensionResource(R.dimen.medium_size))
                        )
                        Text(
                            text = stringResource(R.string.settings),
                            color = colorResource(R.color.black),
                            fontSize = dimensionResource(R.dimen.normal_text).value.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        SettingsScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun SettingsScreen(){
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.large_size)))
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.medium_size)))
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(vertical = dimensionResource(R.dimen.big_size), horizontal = dimensionResource(R.dimen.medium_size))) {

            Text(
                text = stringResource(R.string.night_theme),
                fontSize = dimensionResource(R.dimen.medium_text).value.sp,
            )

            Switch(
                checked = false,
                onCheckedChange = null
            )
        }

        SettingsItem(
            text = stringResource(R.string.share_application),
            iconRes = R.drawable.ic_share)

        SettingsItem(
            text = stringResource(R.string.support),
            iconRes = R.drawable.ic_support)

        SettingsItem(
            text = stringResource(R.string.user_agreement),
            iconRes = R.drawable.ic_forward_arrow)
    }
}

@Composable
fun SettingsItem(text: String, iconRes: Int){
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(vertical = dimensionResource(R.dimen.big_size), horizontal = dimensionResource(R.dimen.medium_size))) {

        Text(
            text = text,
            fontSize = dimensionResource(R.dimen.medium_text).value.sp
        )

        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = colorResource(R.color.black))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsPreview(){
    Scaffold(topBar = {
        Row(modifier = Modifier.fillMaxWidth().statusBarsPadding(),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.ic_back_arrow),
                contentDescription = null,
                tint = colorResource(R.color.black),
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.medium_size))
            )
            Text(
                text = stringResource(R.string.settings),
                color = colorResource(R.color.black),
                fontSize = dimensionResource(R.dimen.normal_text).value.sp,
                fontWeight = FontWeight.Bold
            )
        }
    },
        modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            SettingsScreen()
        }
    }
}