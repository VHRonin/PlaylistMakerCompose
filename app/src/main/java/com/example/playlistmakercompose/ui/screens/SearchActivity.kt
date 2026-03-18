package com.example.playlistmakercompose.ui.screens

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.platform.LocalFocusManager
import com.example.playlistmakercompose.ui.theme.YPColors

class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaylistMakerComposeTheme {
                Scaffold(topBar = {
                    MyTopBar(
                        headText = stringResource(R.string.search),
                        onClick = {finish()}
                    )
                },
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        SearchScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun SearchScreen(){
    Column(
        modifier = Modifier
        .fillMaxWidth()
        .padding(
            horizontal = dimensionResource(R.dimen.normal_size),
            vertical = dimensionResource(R.dimen.small_size))
    ) {
        MyTextField()
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


@Preview(showBackground = true, showSystemUi = false, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SearchPreview(){
    PlaylistMakerComposeTheme {
        Scaffold(topBar = {
            MyTopBar(
                headText = stringResource(R.string.search),
                onClick = {}
            )
        },
            modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                SearchScreen()
            }
        }
    }
}