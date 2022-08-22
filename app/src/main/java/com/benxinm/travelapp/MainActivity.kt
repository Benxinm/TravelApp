package com.benxinm.travelapp

import SearchPage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.benxinm.travelapp.ui.TravelApp
import com.benxinm.travelapp.ui.detail.GuideDetail
import com.benxinm.travelapp.ui.theme.TravelAppTheme

import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)

        setContent {
            TravelAppTheme {
                rememberSystemUiController().setStatusBarColor(
                    Color.Transparent, darkIcons = MaterialTheme.colors.isLight)
                Box {
//                    TravelApp()
                    SearchPage()
//                    GuideDetail()
//                    FoodPage()
//                    AuthenticationPage()
                }
            }
        }
    }
}

