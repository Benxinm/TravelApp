package com.benxinm.travelapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.benxinm.travelapp.data.Page
import com.benxinm.travelapp.ui.community.Community
import com.benxinm.travelapp.ui.detail.DetailPage
import com.benxinm.travelapp.ui.components.NavigationBar
import com.benxinm.travelapp.ui.detail.GuideDetail
import com.benxinm.travelapp.ui.main.GuidePage
import com.benxinm.travelapp.ui.main.MainPage
import com.benxinm.travelapp.ui.main.MapPage
import com.benxinm.travelapp.ui.main.StorePage
import com.benxinm.travelapp.ui.me.EditPage
import com.benxinm.travelapp.ui.me.FlavorSelectPage
import com.benxinm.travelapp.ui.me.MePage
import com.benxinm.travelapp.viewModel.UserViewModel

@Composable
fun TravelApp() {
    val allPages = Page.values().copyOfRange(fromIndex = 0, toIndex = 3).toList()
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentPage = Page.fromRoute(backStackEntry.value?.destination?.route)
    var check by remember {
        mutableStateOf(true)
    }
    val userViewModel:UserViewModel= viewModel()
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
                MainPage(navController)
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
                DetailPage(navController)
            }
            composable(Page.Personal.name){
                LaunchedEffect(key1 = Unit) {
                    check = false
                }
                EditPage(navController)
            }
            composable(Page.FlavourBottle.name){
                LaunchedEffect(key1 = Unit) {
                    check = false
                }
                FlavorSelectPage(navController,userViewModel)
            }
            composable(Page.Me.name) {
                LaunchedEffect(key1 = Unit) {
                    check = true
                }
                MePage(navController,userViewModel)
            }
            composable(Page.Guide.name){
                LaunchedEffect(key1 = Unit) {
                    check = false
                }
                GuidePage(navController = navController)
            }
            composable(Page.Store.name){
                LaunchedEffect(key1 = Unit) {
                    check = false
                }
                StorePage(navController = navController)
            }
            composable(Page.Map.name){
                LaunchedEffect(key1 = Unit) {
                    check = false
                }
                MapPage(navController = navController)
            }
            composable(Page.GuideDetail.name){
                LaunchedEffect(key1 = Unit) {
                    check = false
                }
                GuideDetail(navController = navController)
            }
        }
    }
}