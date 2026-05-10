package com.example.playlistmakercompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.playlistmakercompose.R
import com.example.playlistmakercompose.data.Track
import com.example.playlistmakercompose.ui.components.MyTopBar
import com.example.playlistmakercompose.ui.theme.PlaylistMakerComposeTheme
import com.example.playlistmakercompose.ui.theme.YPColors

@Composable
fun PlayerRoute(){

}

@Composable
fun PlayerScreen(track: Track){
    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(26.dp))

        Column(modifier = Modifier.padding(24.dp)) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth().aspectRatio(1f).clip(RoundedCornerShape(2.dp)),
                model = track.getCoverArtwork(),
                contentDescription = null,
                placeholder = painterResource(R.drawable.ic_placeholder_track),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = track.trackName ?: stringResource(R.string.unknown_track_name),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 22.sp, lineHeight = 22.sp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = track.artistName ?: stringResource(R.string.unknown_track_name),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp, lineHeight = 14.sp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.ic_add_library_button),
                        contentDescription = null,
                        tint = Color.Unspecified
                        )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PlayerPreview(){
    PlaylistMakerComposeTheme(dynamicColor = false) {
        PlayerScreen(Track("Yesterday (Remastered 2009)", "The Beatles ", 64646474, "https://www.figma.com/design/WKIfi0QI0g90ImfTOgvVbN/Playlist-Maker--YP-?node-id=1863-10827&t=keIsMzKS3XnhwytY-4", 1, "Yesterday (Remastered 2009)", "1965", "Rock", "Великобритания"))
    }
}