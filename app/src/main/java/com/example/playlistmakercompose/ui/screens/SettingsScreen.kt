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
import androidx.compose.material3.Scaffold
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.playlistmakercompose.R
import com.example.playlistmakercompose.ui.components.AppBottomNavigation
import com.example.playlistmakercompose.ui.components.MyTopBar

@Composable
fun SettingsRoute(onBackClick: () -> Unit, navController: NavController){
    val viewModel: SettingsScreenViewModel = viewModel()
    Scaffold(topBar = {
        MyTopBar(
            headText = stringResource(R.string.settings),
            onClick = {onBackClick()}
        )
    },
        bottomBar = {
            AppBottomNavigation(navController)
        },
        modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            SettingsScreen(viewModel)
        }
    }
}

@Composable
fun SettingsScreen(viewModel: SettingsScreenViewModel){
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {

        // Spacer(modifier = Modifier.width(20.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(vertical = dimensionResource(R.dimen.big_size), horizontal = dimensionResource(
                R.dimen.normal_size))) {

            Text(
                text = stringResource(R.string.night_theme),
                style = MaterialTheme.typography.bodyMedium
            )

            Switch(
                checked = viewModel.isChecked,
                onCheckedChange = {viewModel.toggleCheck(it)},

                )
        }

        SettingsItem(
            text = stringResource(R.string.share_application),
            iconRes = R.drawable.ic_share,
            onClick = {viewModel.shareApp(context)})

        SettingsItem(
            text = stringResource(R.string.support),
            iconRes = R.drawable.ic_support,
            onClick = {viewModel.textSupport(context)})

        SettingsItem(
            text = stringResource(R.string.user_agreement),
            iconRes = R.drawable.ic_forward_arrow,
            onClick = {viewModel.openUserAgreement(context)})
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
            style = MaterialTheme.typography.bodyMedium
        )

        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = Color(0xFFAEAFB4))
    }
}
