package com.example.playlistmakercompose.ui.screens

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.playlistmakercompose.R
import com.example.playlistmakercompose.data.Track
import com.example.playlistmakercompose.ui.theme.YPColors

@Composable
fun SearchScreen(tracks: List<Track>){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(R.dimen.small_size))
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.normal_size))){
            MyTextField()
        }


        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(items = tracks, key = {"${it.trackName}${it.artistName}"}){ track ->
                TrackCard(track)
            }
        }
    }
}

@Composable
fun MyTextField(){
    var textValue by remember {mutableStateOf("")}
    val focusManager = LocalFocusManager.current
    BasicTextField(
        value = textValue,
        onValueChange = {textValue = it},
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .background(
                Color(0xFFE6E8EB),
                shape = RoundedCornerShape(8.dp)
            ),
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
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }


                    innerTextField()
                }

                if (textValue.isNotEmpty()){
                    IconButton(onClick = {
                        textValue = ""
                        focusManager.clearFocus()
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
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(12.dp)) {
//        Image(
//            painter = painterResource(R.drawable.ic_support),
//            contentDescription = null,
//            modifier = Modifier.size(45.dp, 45.dp)
//        )
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
                text = track.trackName,
                style = MaterialTheme.typography.bodyLarge
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = track.artistName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Icon(
                    painter = painterResource(R.drawable.ic_circle),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = track.trackTime,
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