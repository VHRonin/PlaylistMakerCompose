package com.example.playlistmakercompose.ui.screens

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.playlistmakercompose.R
import com.example.playlistmakercompose.ui.theme.MainScreen
import com.example.playlistmakercompose.ui.theme.PlaylistMakerComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaylistMakerComposeTheme(){
                Scaffold(topBar = {
                    MainTopBar()
                }, containerColor = MaterialTheme.colorScheme.MainScreen, modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        MainScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(){
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(dimensionResource(R.dimen.normal_size))){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.normal_size))){

            NavigationButton(
                text = stringResource(R.string.search),
                iconRes = R.drawable.ic_search,
                modifier = Modifier.weight(1f),
                onClick = {
                    val searchIntent = Intent(context, SearchActivity::class.java)
                    context.startActivity(searchIntent)
                }
            )

            NavigationButton(
                text = stringResource(R.string.media_library),
                iconRes = R.drawable.ic_library,
                modifier = Modifier.weight(1f)
            )

            NavigationButton(
                text = stringResource(R.string.settings),
                iconRes = R.drawable.ic_settings,
                modifier = Modifier.weight(1f),
                onClick = {
                    val settingsIntent = Intent(context, SettingsActivity::class.java)
                    context.startActivity(settingsIntent)
                })
        }
    }
}




@Composable
fun NavigationButton(text: String, iconRes: Int, modifier: Modifier = Modifier, onClick: () -> Unit = {}){
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.white),
            contentColor = colorResource(R.color.black)
        ),
        shape = RoundedCornerShape(dimensionResource(R.dimen.normal_size))
    ) {
        Icon(painter = painterResource(
            iconRes),
            contentDescription = null,
            tint = colorResource(R.color.black))

        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.small_size)))

        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(){
    TopAppBar(
        title = {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleLarge)
    },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.MainScreen,
            titleContentColor = Color.White
        ),
        windowInsets = TopAppBarDefaults.windowInsets)
}

//Text(text = stringResource(R.string.app_name),
//color = colorResource(R.color.white),
//fontSize = dimensionResource(R.dimen.normal_text).value.sp,
//modifier = Modifier.fillMaxWidth().statusBarsPadding())

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun GreetingPreview() {
    PlaylistMakerComposeTheme(){
        Scaffold(topBar = {
            MainTopBar()
        }, containerColor = MaterialTheme.colorScheme.MainScreen, modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                MainScreen()
            }
        }
    }
}
