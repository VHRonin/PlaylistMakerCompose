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
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.playlistmakercompose.R
import com.example.playlistmakercompose.ui.navigation.AppNavHost
import com.example.playlistmakercompose.ui.navigation.Destination
import com.example.playlistmakercompose.ui.theme.MainScreen
import com.example.playlistmakercompose.ui.theme.PlaylistMakerComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaylistMakerComposeTheme(dynamicColor = false){
//                Scaffold(topBar = {
//                    MainTopBar()
//                }, containerColor = MaterialTheme.colorScheme.MainScreen, modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Column(modifier = Modifier.padding(innerPadding)) {
//                        MainScreen()
//                    }
//                }
                val navHostController = rememberNavController()

                AppNavHost(navHostController)
            }
        }
    }
}



//Text(text = stringResource(R.string.app_name),
//color = colorResource(R.color.white),
//fontSize = dimensionResource(R.dimen.normal_text).value.sp,
//modifier = Modifier.fillMaxWidth().statusBarsPadding())

//@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Composable
//fun GreetingPreview() {
//    PlaylistMakerComposeTheme(){
//        Scaffold(topBar = {
//            MainTopBar()
//        }, containerColor = MaterialTheme.colorScheme.MainScreen, modifier = Modifier.fillMaxSize()) { innerPadding ->
//            Column(modifier = Modifier.padding(innerPadding)) {
//                MainScreen(onNavigate = {})
//            }
//        }
//    }
//}
