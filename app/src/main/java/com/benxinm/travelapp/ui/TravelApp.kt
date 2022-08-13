package com.benxinm.travelapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.benxinm.travelapp.data.Page
import com.benxinm.travelapp.ui.authentication.AuthenticationPage
import com.benxinm.travelapp.ui.community.Community
import com.benxinm.travelapp.ui.components.DetailPage
import com.benxinm.travelapp.ui.components.NavigationBar
import com.benxinm.travelapp.ui.components.StaggeredVerticalGrid
import com.benxinm.travelapp.ui.main.MainPage
import com.benxinm.travelapp.ui.me.MePage
import com.benxinm.travelapp.viewModel.DetailViewModel

@Composable
fun TravelApp() {
    val allPages = Page.values().copyOfRange(fromIndex = 0, toIndex = 3).toList()
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentPage = Page.fromRoute(backStackEntry.value?.destination?.route)
    var check by remember {
        mutableStateOf(true)
    }
    Scaffold(bottomBar = {
        NavigationBar(
            allPages = allPages,
            onTabSelected = { page ->
                navController.navigate(page.name)
            },
            currentPage = currentPage, check
        )
    }, modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Page.MainPage.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Page.MainPage.name) {
                LaunchedEffect(key1 = Unit) {
                    check = true
                }
                MainPage()
            }
            composable(Page.Community.name) {
                LaunchedEffect(key1 = Unit) {
                    check = true
                }
                Community(navController)
            }
            composable(Page.Detail.name) {
                LaunchedEffect(key1 = Unit) {
                    check = false
                }
                DetailPage()
            }
            composable(Page.Me.name) {
                LaunchedEffect(key1 = Unit) {
                    check = true
                }
                MePage()
            }
        }
    }
}