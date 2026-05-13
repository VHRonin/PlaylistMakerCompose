package com.example.playlistmakercompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.playlistmakercompose.R
import com.example.playlistmakercompose.data.Track
import com.example.playlistmakercompose.ui.components.MyTopBar
import com.example.playlistmakercompose.ui.theme.PlaylistMakerComposeTheme
import com.example.playlistmakercompose.ui.theme.YPColors
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun PlayerRoute(){

}

@Composable
fun PlayerScreen(navController: NavController){
    val track = navController.previousBackStackEntry?.savedStateHandle?.get<Track>("track")
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState)) {

        Spacer(modifier = Modifier.height(26.dp))

        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth().aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                model = track?.getCoverArtwork(),
                contentDescription = null,
                placeholder = painterResource(R.drawable.ic_placeholder_track),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = track?.trackName ?: stringResource(R.string.unknown_track_name),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 22.sp, lineHeight = 22.sp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = track?.artistName ?: stringResource(R.string.unknown_track_name),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp, lineHeight = 14.sp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                val isDark = MaterialTheme.colorScheme.background.luminance() < 0.5f

                IconButton(onClick = {}, modifier = Modifier.size(51.dp)) {
                    Icon(
                        painter = if (isDark) painterResource(R.drawable.ic_add_to_library_dark) else painterResource(R.drawable.ic_add_library_button),
                        contentDescription = null,
                        tint = Color.Unspecified
                        )
                }

                IconButton(onClick = {}, modifier = Modifier.size(100.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.ic_play_button),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                IconButton(onClick = {}, Modifier.size(51.dp)) {
                    Icon(
                        painter = if (isDark) painterResource(R.drawable.ic_like_button_dark) else painterResource(R.drawable.ic_like_button),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "0:00",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        TrackInfo(stringResource(R.string.duration), track?.trackTime?.let { formatTime(it) } ?: "--:--")
        TrackInfo(stringResource(R.string.album), track?.collectionName ?: "")
        TrackInfo(stringResource(R.string.year), track?.releaseDate?.take(4) ?: "")
        TrackInfo(stringResource(R.string.genre), track?.primaryGenreName ?: "")
        TrackInfo(stringResource(R.string.country), track?.country ?: "")
    }
}

@Composable
fun TrackInfo(key: String, value: String){
    if (value.isNotEmpty()){
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
            Text(
                text = key,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp, lineHeight = 13.sp),
                color = YPColors.TextGray
            )

            Text(
                text = value,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp, lineHeight = 13.sp),
                textAlign = TextAlign.End
            )
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

@Preview(showSystemUi = true)
@Composable
fun PlayerPreview(){
    PlaylistMakerComposeTheme(dynamicColor = false) {
//        PlayerScreen(Track("Yesterday (Remastered 2009)", "The Beatles ", 64646474, "https://www.figma.com/design/WKIfi0QI0g90ImfTOgvVbN/Playlist-Maker--YP-?node-id=1863-10827&t=keIsMzKS3XnhwytY-4", 1, "Yesterday (Remastered 2009)", "1965", "Rock", "Великобритания"))
    }
}