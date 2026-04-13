package com.example.playlistmakercompose.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.playlistmakercompose.ui.navigation.Destination
import com.example.playlistmakercompose.ui.screens.SearchScreen
import com.example.playlistmakercompose.ui.screens.SearchScreenViewModel
import com.example.playlistmakercompose.ui.theme.PlaylistMakerComposeTheme
import com.example.playlistmakercompose.ui.theme.YPColors

fun Modifier.topBorder(width: Dp, color: Color): Modifier = this.drawBehind {
    drawLine(
        color = color,
        start = Offset(0f, 0f),
        end = Offset(size.width, 0f),
        strokeWidth = width.toPx()
    )
}
@Composable
fun AppBottomNavigation(navController: NavController){
    val screens = listOf(
        Destination.Search,
        Destination.Library,
        Destination.Settings
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.navigationBarsPadding().heightIn(min = 56.dp, max = 56.dp).topBorder(0.5.dp, color = if (isSystemInDarkTheme()) YPColors.White else YPColors.LightGray),
        windowInsets = NavigationBarDefaults.windowInsets
    ) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        screens.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {navController.navigate(screen.route)},
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(screen.iconId),
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = screen.label,
                        style = MaterialTheme.typography.labelSmall,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = YPColors.Blue,
                    selectedTextColor = YPColors.Blue,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                    unselectedTextColor = MaterialTheme.colorScheme.onBackground,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview
@Composable
fun NavigationPreview(){
    val viewModel: SearchScreenViewModel = viewModel()
    val navController: NavController = rememberNavController()
    PlaylistMakerComposeTheme {
        Scaffold(bottomBar = {
            AppBottomNavigation(navController)
        },
            modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                SearchScreen(viewModel)
            }
        }
    }
}