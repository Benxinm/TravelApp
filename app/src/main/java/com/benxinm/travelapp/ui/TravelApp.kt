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

@Composable
fun TravelApp() {
    val allPages = Page.values().toList()
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentPage = Page.fromRoute(backStackEntry.value?.destination?.route)
    Scaffold(bottomBar = {
        NavigationBar(
            allPages = allPages,
            onTabSelected = { page ->
                navController.navigate(page.name)
            },
            currentPage = currentPage
        )
    }, modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Page.MainPage.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Page.MainPage.name) {
//                MainPage()
                DetailPage()
//                AuthenticationPage()
            }
            composable(Page.Community.name) {
                Community()
            }
            composable(Page.Me.name) {
                MePage()
            }
        }
    }
}