package com.example.playlistmakercompose

import android.content.res.Configuration
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.example.playlistmakercompose.ui.theme.MainScreen
import com.example.playlistmakercompose.ui.theme.PlaylistMakerComposeTheme

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaylistMakerComposeTheme {
                Scaffold(topBar = {
                    SettingsTopBar()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopBar(){
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.settings),
                style = MaterialTheme.typography.titleLarge)
        },
        navigationIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_back_arrow),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.medium_size))
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground
        ),
        windowInsets = TopAppBarDefaults.windowInsets)
}

//Row(modifier = Modifier.fillMaxWidth().statusBarsPadding(),
//verticalAlignment = Alignment.CenterVertically) {
//    Icon(
//        painter = painterResource(R.drawable.ic_back_arrow),
//        contentDescription = null,
//        tint = colorResource(R.color.black),
//        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.medium_size))
//    )
//    Text(
//        text = stringResource(R.string.settings),
//        color = colorResource(R.color.black),
//        fontSize = dimensionResource(R.dimen.normal_text).value.sp,
//        fontWeight = FontWeight.Bold
//    )
//}

@Composable
fun SettingsScreen(){
    Column(modifier = Modifier.fillMaxSize()) {

        // Spacer(modifier = Modifier.width(20.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(vertical = dimensionResource(R.dimen.big_size), horizontal = dimensionResource(R.dimen.normal_size))) {

            Text(
                text = stringResource(R.string.night_theme),
                style = MaterialTheme.typography.bodyLarge
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
        modifier = Modifier.fillMaxWidth().padding(vertical = dimensionResource(R.dimen.big_size), horizontal = dimensionResource(R.dimen.normal_size))) {

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

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SettingsPreview(){
    PlaylistMakerComposeTheme {
        Scaffold(topBar = {
            SettingsTopBar()
        },
            modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                SettingsScreen()
            }
        }
    }
}