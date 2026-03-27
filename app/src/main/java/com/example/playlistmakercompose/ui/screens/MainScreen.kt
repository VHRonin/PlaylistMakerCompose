package com.example.playlistmakercompose.ui.screens

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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.playlistmakercompose.R
import com.example.playlistmakercompose.ui.navigation.Destination
import com.example.playlistmakercompose.ui.theme.MainScreen

@Composable
fun MainScreen(onNavigate: (String) -> Unit){
    // val context = LocalContext.current
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
//                    val searchIntent = Intent(context, SearchActivity::class.java)
//                    context.startActivity(searchIntent)
                    // navHostController.navigate(Destination.Search.route)
                    onNavigate(Destination.Search.route)
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
//                    val settingsIntent = Intent(context, SettingsActivity::class.java)
//                    context.startActivity(settingsIntent)
                    // navHostController.navigate(Destination.Settings.route)

                    onNavigate(Destination.Settings.route)
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