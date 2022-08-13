package com.benxinm.travelapp.ui.community

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.benxinm.travelapp.R
import com.benxinm.travelapp.data.Label
import com.benxinm.travelapp.data.Page
import com.benxinm.travelapp.logic.Repository
import com.benxinm.travelapp.ui.components.*
import com.benxinm.travelapp.ui.theme.BackgroundGrey
import com.benxinm.travelapp.viewModel.CommunityViewModel
import com.benxinm.travelapp.viewModel.DetailViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun Community(navController: NavController) {
    val communityViewModel:CommunityViewModel= viewModel()
    val detailViewModel:DetailViewModel= viewModel()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context=LocalContext.current
    LaunchedEffect(key1 = true){
        communityViewModel.getAllPost().observe(lifecycleOwner){result->
            val list=result.getOrNull()
            if (list!=null&& result.isSuccess){
                communityViewModel.postList.addAll(list)
            }
        }
    }
    val list = listOf(
        Label("https://s2.loli.net/2022/08/02/JTYD61w3jlQRWcu.jpg", "福州佛跳墙"),
    )
    Box(modifier = Modifier.fillMaxSize()){
        Box(modifier = Modifier.systemBarsPadding()) {
            Column {
                Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_add),
                                tint = Color.Black,
                                contentDescription = "添加"
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            HomeSearchBar()
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Location()
                    }
                }
                Box(
                    modifier = Modifier
                        .background(BackgroundGrey)
                        .fillMaxSize()
                ) {
                    LazyColumn {
                        item {
                            StaggeredVerticalGrid(
                                maxColumnWidth = 215.dp,
                                modifier = Modifier.padding(horizontal = 15.dp)
                            ) {
                                /*communityViewModel.postList*/list.forEach { label ->
                                val likes = remember {
                                    mutableStateOf(0)
                                }
                                WaterfallLabel(
                                    url = label.imgRes,
                                    text = label.text,
                                    likes =likes.value, onSelected = {
                                       navController.navigate(Page.Detail.name)
                                    }
                                ) { scaleButtonState ->
                                    if (scaleButtonState == ScaleButtonState.IDLE) {
                                        Repository.addLike("123", "123").observe(lifecycleOwner) {
                                            if (it.isSuccess) {
                                                likes.value++
                                            }
                                        }
                                    } else {
                                        Repository.cancelLike("456", "456").observe(lifecycleOwner) {
                                            if (it.isSuccess) {
                                                likes.value--
                                            }/*else{
                                                likes.value--
                                            }*/
                                        }
                                    }
                                }
                            }
                            }
                        }
                    }
                }
            }
        }
    }
}