package com.example.playlistmakercompose.ui.screens

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.playlistmakercompose.R
import com.example.playlistmakercompose.data.Track
import com.example.playlistmakercompose.network.ITunesApi
import com.example.playlistmakercompose.ui.components.AppBottomNavigation
import com.example.playlistmakercompose.ui.components.MyTopBar
import com.example.playlistmakercompose.ui.theme.PlaylistMakerComposeTheme
import com.example.playlistmakercompose.ui.theme.YPColors
import com.example.playlistmakercompose.ui.theme.YSDisplayFamily
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Locale

private val retrofit = Retrofit.Builder()
    .baseUrl("https://itunes.apple.com")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val iTunesService = retrofit.create(ITunesApi::class.java)
@Composable
fun SearchRoute(onBackClick: () -> Unit, navController: NavController){
    val viewModel: SearchScreenViewModel = viewModel()

    Scaffold(
        topBar = {
            MyTopBar(
                headText = stringResource(R.string.search),
                onClick = {onBackClick()}
            )
        },
        bottomBar = {
            AppBottomNavigation(navController)
        },
        modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            SearchScreen(viewModel)
        }
    }
}

@Composable
fun SearchScreen(viewModel: SearchScreenViewModel){
    val tracks = viewModel.tracks
    val notFound = viewModel.notFound
    val noInternet = viewModel.noInternet
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(R.dimen.small_size))
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.normal_size))){
            MyTextField(viewModel)
        }


        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(items = tracks, key = {it.trackId ?: "${it.trackName}${it.artistName}${it.hashCode()}"}){ track ->
                TrackCard(track)
            }
        }

        if (noInternet){
            ErrorMessage(
                if (!isSystemInDarkTheme()) R.drawable.ic_no_internet_light else R.drawable.ic_no_internet_dark,
                stringResource(R.string.no_internet),
                true,
                onRefreshClick = {viewModel.searchTracks(viewModel.lastFailedQuery)}
            )
        }
        else if (notFound){
            ErrorMessage(
                if (!isSystemInDarkTheme()) R.drawable.ic_not_found_light else R.drawable.ic_not_found_dark,
                stringResource(R.string.no_found),
            )
        }
    }
}

@Composable
fun MyTextField(viewModel: SearchScreenViewModel){
    val textValue = viewModel.query
    val focusManager = LocalFocusManager.current
    BasicTextField(
        value = textValue,
        onValueChange = {
            viewModel.updateText(it)
            if (viewModel.query.isEmpty()){
                viewModel.clearTracks()
                viewModel.clearMessageVisibility()
            }
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .background(
                Color(0xFFE6E8EB),
                shape = RoundedCornerShape(8.dp)
            ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()

            viewModel.searchTracks(viewModel.query)
        }),
        decorationBox = {innerTextField ->
            Row(modifier = Modifier.fillMaxSize().padding(start = 12.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    tint = if (isSystemInDarkTheme()) YPColors.Black else YPColors.TextGray,
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.width(8.dp))

                Box(modifier = Modifier.weight(1f)){
                    if (textValue.isEmpty()){
                        Text(
                            text = stringResource(R.string.search),
                            color = if (isSystemInDarkTheme()) YPColors.Black else YPColors.TextGray,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }


                    innerTextField()
                }

                if (textValue.isNotEmpty()){
                    IconButton(onClick = {
                        viewModel.clearText()
                        focusManager.clearFocus()
                        viewModel.clearTracks()
                        viewModel.clearMessageVisibility()
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_clear),
                            contentDescription = null,
                            tint = YPColors.TextGray,
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun TrackCard(track: Track){
    val trackName = track.trackName ?: stringResource(R.string.unknown_track_name)
    val artistName = track.artistName ?: stringResource(R.string.unknown_artist_name)
    val trackTime = track.trackTime?.let { formatTime(it) } ?: "--:--"

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(12.dp)) {
        AsyncImage(
            modifier = Modifier.size(45.dp).clip(RoundedCornerShape(2.dp)),
            model = track.artworkUrl100,
            contentDescription = null,
            placeholder = painterResource(R.drawable.ic_placeholder_track),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = trackName,
                style = MaterialTheme.typography.bodyMedium
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = artistName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Icon(
                    painter = painterResource(R.drawable.ic_circle),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = trackTime,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Icon(
            painter = painterResource(R.drawable.ic_forward_arrow),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun ErrorMessage(iconRes: Int, problemText: String, showButton: Boolean = false, onRefreshClick: () -> Unit = {}){
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(102.dp))
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = problemText,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,

        )

        if (showButton){
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {onRefreshClick()},
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onBackground,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
                shape = RoundedCornerShape(54.dp)
            ) {
                Text(
                    text = stringResource(R.string.update),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.background,
                )
            }
        }
    }
}

private fun formatTime(trackTime: Long?): String{
//    val millis = trackTime?.toLongOrNull() ?: return "--:--"
    return SimpleDateFormat(
        "mm:ss",
        Locale.getDefault()
    )
        .format(trackTime)
        .toString()
}



@Preview(showBackground = true, showSystemUi = false, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SearchPreview(){
    SearchRoute(onBackClick = {}, rememberNavController())
}