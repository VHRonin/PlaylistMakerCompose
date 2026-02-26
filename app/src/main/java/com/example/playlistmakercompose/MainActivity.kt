package com.example.playlistmakercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playlistmakercompose.ui.theme.PlaylistMakerComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold {innerPadding ->
                Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(){
    Column(
        modifier = Modifier.fillMaxSize()
        .background(colorResource(R.color.blue))
        .padding(dimensionResource(R.dimen.normal_size))){
        Text(text = stringResource(R.string.app_name),
            color = colorResource(R.color.white),
            fontSize = dimensionResource(R.dimen.normal_text).value.sp,
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.large_size)))
        
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.normal_size))){

            NavigationButton(
                text = stringResource(R.string.search),
                iconRes = R.drawable.ic_search,
                modifier = Modifier.weight(1f))

            NavigationButton(
                text = stringResource(R.string.media_library),
                iconRes = R.drawable.ic_library,
                modifier = Modifier.weight(1f))

            NavigationButton(
                text = stringResource(R.string.settings),
                iconRes = R.drawable.ic_settings,
                modifier = Modifier.weight(1f))
        }
    }
}


@Composable
fun NavigationButton(text: String, iconRes: Int, modifier: Modifier = Modifier){
    Button(
        onClick = {},
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
            fontSize = dimensionResource(R.dimen.normal_text).value.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreen()
}