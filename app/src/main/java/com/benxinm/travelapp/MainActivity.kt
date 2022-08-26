package com.benxinm.travelapp

import SearchPage
import android.Manifest
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.benxinm.travelapp.ui.TravelApp
import com.benxinm.travelapp.ui.authentication.AuthenticationPage
import com.benxinm.travelapp.ui.detail.GuideDetail
import com.benxinm.travelapp.ui.detail.StoreDetail
import com.benxinm.travelapp.ui.main.FoodPage
import com.benxinm.travelapp.ui.post.PersonalPost
import com.benxinm.travelapp.ui.theme.TravelAppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState

import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.io.File

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            TravelAppTheme {
                rememberSystemUiController().setStatusBarColor(
                    Color.Transparent,
                    darkIcons = MaterialTheme.colors.isLight
                )
                val permissionState =
                    rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)
                LaunchedEffect(key1 = true) {
                    permissionState.launchPermissionRequest()
                }
                when (permissionState.status) {
                    PermissionStatus.Granted -> {
                        Box {
                            TravelApp()
//                            SearchPage()
//                            FoodPage()
//                            StoreDetail()
                        }
                    }
                    is PermissionStatus.Denied -> {

                    }
                }
            }
        }
    }
}
