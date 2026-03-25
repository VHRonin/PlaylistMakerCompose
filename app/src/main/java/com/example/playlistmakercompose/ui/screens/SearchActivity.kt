package com.example.playlistmakercompose.ui.screens

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.playlistmakercompose.R
import com.example.playlistmakercompose.ui.components.MyTopBar
import com.example.playlistmakercompose.ui.theme.PlaylistMakerComposeTheme
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import coil.compose.AsyncImage
import com.example.playlistmakercompose.data.Track
import com.example.playlistmakercompose.ui.theme.YPColors

class SearchActivity : ComponentActivity() {
    val tracks = listOf(
        Track(
            "Smells Like Teen Spirit",
            "Nirvana",
            "5:01",
            "https://is5-ssl.mzstatic.com/image/thumb/Music115/v4/7b/58/c2/7b58c21a-2b51-2bb2-e59a-9bb9b96ad8c3/00602567924166.rgb.jpg/100x100bb.jpg"
        ),
        Track(
            "Billie Jean",
            "Michael Jackson",
            "4:35",
            "https://is5-ssl.mzstatic.com/image/thumb/Music125/v4/3d/9d/38/3d9d3811-71f0-3a0e-1ada-3004e56ff852/827969428726.jpg/100x100bb.jpg"
        ),
        Track(
            "Stayin' Alive",
            "Bee Gees",
            "4:10",
            "https://is4-ssl.mzstatic.com/image/thumb/Music115/v4/1f/80/1f/1f801fc1-8c0f-ea3e-d3e5-387c6619619e/16UMGIM86640.rgb.jpg/100x100bb.jpg"
        ),
        Track(
            "Whole Lotta Love",
            "Led Zeppelin",
            "5:33",
            "https://is2-ssl.mzstatic.com/image/thumb/Music62/v4/7e/17/e3/7e17e33f-2efa-2a36-e916-7f808576cf6b/mzm.fyigqcbs.jpg/100x100bb.jpg"
        ),
        Track(
            "Sweet Child O'Mine",
            "Guns N' Roses",
            "5:03",
            "https://is5-ssl.mzstatic.com/image/thumb/Music125/v4/a0/4d/c4/a04dc484-03cc-02aa-fa82-5334fcb4bc16/18UMGIM24878.rgb.jpg/100x100bb.jpg"
        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaylistMakerComposeTheme(dynamicColor = false) {
                Scaffold(topBar = {
                    MyTopBar(
                        headText = stringResource(R.string.search),
                        onClick = {finish()}
                    )
                },
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        SearchScreen(tracks)
                    }
                }
            }
        }
    }
}

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
            items(tracks){ track ->
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

 @Preview
@Composable
fun TrackPreview(){
    PlaylistMakerComposeTheme(dynamicColor = false) {
        TrackCard(
            Track(
                "Whole Lotta Love",
                "Led Zeppelin",
                "5:33",
                "https://is2-ssl.mzstatic.com/image/thumb/Music62/v4/7e/17/e3/7e17e33f-2efa-2a36-e916-7f808576cf6b/mzm.fyigqcbs.jpg/100x100bb.jpg"
            ))
    }
}


@Preview(showBackground = true, showSystemUi = false, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SearchPreview(){
    val tracks = listOf(
        Track(
            "Smells Like Teen Spirit",
            "Nirvana",
            "5:01",
            "https://is5-ssl.mzstatic.com/image/thumb/Music115/v4/7b/58/c2/7b58c21a-2b51-2bb2-e59a-9bb9b96ad8c3/00602567924166.rgb.jpg/100x100bb.jpg"
        ),
        Track(
            "Billie Jean",
            "Michael Jackson",
            "4:35",
            "https://is5-ssl.mzstatic.com/image/thumb/Music125/v4/3d/9d/38/3d9d3811-71f0-3a0e-1ada-3004e56ff852/827969428726.jpg/100x100bb.jpg"
        ),
        Track(
            "Stayin' Alive",
            "Bee Gees",
            "4:10",
            "https://is4-ssl.mzstatic.com/image/thumb/Music115/v4/1f/80/1f/1f801fc1-8c0f-ea3e-d3e5-387c6619619e/16UMGIM86640.rgb.jpg/100x100bb.jpg"
        ),
        Track(
            "Whole Lotta Love",
            "Led Zeppelin",
            "5:33",
            "https://is2-ssl.mzstatic.com/image/thumb/Music62/v4/7e/17/e3/7e17e33f-2efa-2a36-e916-7f808576cf6b/mzm.fyigqcbs.jpg/100x100bb.jpg"
        ),
        Track(
            "Sweet Child O'Mine",
            "Guns N' Roses",
            "5:03",
            "https://is5-ssl.mzstatic.com/image/thumb/Music125/v4/a0/4d/c4/a04dc484-03cc-02aa-fa82-5334fcb4bc16/18UMGIM24878.rgb.jpg/100x100bb.jpg"
        )
    )

    PlaylistMakerComposeTheme(dynamicColor = false) {
        Scaffold(topBar = {
            MyTopBar(
                headText = stringResource(R.string.search),
                onClick = {}
            )
        },
            modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                SearchScreen(tracks)
            }
        }
    }
}