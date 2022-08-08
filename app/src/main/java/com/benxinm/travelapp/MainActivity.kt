package com.benxinm.travelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.benxinm.travelapp.ui.TravelApp
import com.benxinm.travelapp.ui.me.MePage
import com.benxinm.travelapp.ui.theme.TravelAppTheme

import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)

        setContent {
            TravelAppTheme {
                // A surface container using the 'background' color from the theme

                rememberSystemUiController().setStatusBarColor(
                    Color.Transparent, darkIcons = MaterialTheme.colors.isLight)
//                Box(modifier = Modifier.background(Color.Gray)){
//                    Box(Modifier.systemBarsPadding()) {
//                        Box(Modifier.imePadding()) {
//                            Text(text = "首页\r\n首页1\r\n首页2\r\n首页3")
//                        }
//                    }
//                }
                TravelApp()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TravelAppTheme {
        Greeting("Android")
    }
}