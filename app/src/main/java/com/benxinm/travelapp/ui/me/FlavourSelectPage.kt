package com.benxinm.travelapp.ui.me


import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.benxinm.travelapp.R
import com.benxinm.travelapp.data.Flavor
import com.benxinm.travelapp.data.Page
import com.benxinm.travelapp.logic.Repository
import com.benxinm.travelapp.ui.components.FlavorBottle
import com.benxinm.travelapp.util.noRippleClickable
import com.benxinm.travelapp.viewModel.UserViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun FlavorSelectPage(navController: NavController,userViewModel: UserViewModel) {
    val flavorList = listOf(Flavor.Sour, Flavor.Sweet, Flavor.Salty, Flavor.Bitter, Flavor.Spicy)
    val scope = rememberCoroutineScope()
    val state = rememberPagerState()
    var isChange by remember {
        mutableStateOf(false)
    }
    val lifecycleOwner= LocalLifecycleOwner.current
    Box(modifier = Modifier.systemBarsPadding()) {
        com.google.accompanist.insets.ui.Scaffold(topBar = {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.noRippleClickable {
                    for (i in flavorList.indices){
                        val type= when(flavorList[i]){
                            Flavor.Sour->"3"
                            Flavor.Sweet->"5"
                            Flavor.Salty->"2"
                            Flavor.Bitter->"1"
                            Flavor.Spicy->"4"
                        }
                        val degree=userViewModel.degrees[i].toString()
                        Repository.setBottle(userViewModel.token,userViewModel.email,type, degree).observe(lifecycleOwner){}
                    }
                    navController.popBackStack()
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    modifier = Modifier.size(20.dp),
                    contentDescription = ""
                )
                Text(text = "??????", fontSize = 20.sp, color = Color.Blue)
            }
        }) {
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.ic_back02),
                    contentDescription = " ",
                    modifier = Modifier
                        .noRippleClickable {
                            isChange = false
                            scope.launch {
                                val formerPage =
                                    if (state.currentPage == 0) 4 else state.currentPage - 1
                                state.animateScrollToPage(formerPage)
                            }
                        }
                        .size(30.dp))
                Box(modifier = Modifier.fillMaxWidth(0.9f)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        HorizontalPager(count = 5, state = state) { page ->
                            FlavorBottle(
                                flavor = flavorList[page],
                                degree = userViewModel.degrees[page],
                                size = 40.dp
                            )
                        }
                        Text(
                            text = when (state.currentPage) {
                                0 -> "???"
                                1 -> "???"
                                2 -> "???"
                                3 -> "???"
                                4 -> "???"
                                else -> "???"
                            },
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier.padding(20.dp)
                        )
                        if (!isChange) {
                            Text(
                                text = when (userViewModel.degrees[state.currentPage]) {
                                    0f -> "???????????????"
                                    0.25f -> "?????????"
                                    0.5f -> "??????"
                                    0.75f -> "??????"
                                    1.0f -> "????????????"
                                    else -> "??????"
                                }
                            )
                        }
                        var selectedIndex by remember {
                            mutableStateOf(userViewModel.degrees[state.currentPage])
                        }
                        Box(modifier = Modifier.animateContentSize()) {
                            if (isChange) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = "????????????",
                                        color = if (selectedIndex == 1f) Color.Red else Color.Black,
                                        modifier = Modifier.noRippleClickable {
                                            selectedIndex = 1.0f
                                        })
                                    Text(
                                        text = "??????",
                                        color = if (selectedIndex == 0.75f) Color.Red else Color.Black,
                                        modifier = Modifier.noRippleClickable {
                                            selectedIndex = 0.75f
                                        })
                                    Text(
                                        text = "??????",
                                        color = if (selectedIndex == 0.5f) Color.Red else Color.Black,
                                        modifier = Modifier.noRippleClickable {
                                            selectedIndex = 0.5f
                                        })
                                    Text(
                                        text = "?????????",
                                        color = if (selectedIndex == 0.25f) Color.Red else Color.Black,
                                        modifier = Modifier.noRippleClickable {
                                            selectedIndex = 0.25f
                                        })
                                    Text(
                                        text = "???????????????",
                                        color = if (selectedIndex == 0f) Color.Red else Color.Black,
                                        modifier = Modifier.noRippleClickable {
                                            selectedIndex = 0f
                                        })
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(modifier = Modifier
                            .noRippleClickable {
                                if (isChange) {
                                    userViewModel.degrees[state.currentPage] = selectedIndex
                                }
                                isChange = !isChange
                            }
                            .clip(RoundedCornerShape(40))
                            .background(Color(0xFFebe141))) {
                            Text(
                                text = if (isChange) "??????" else "??????",
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                            )
                        }

                    }
                }
                Icon(painter = painterResource(id = R.drawable.ic_forward),
                    contentDescription = "",
                    modifier = Modifier
                        .noRippleClickable {
                            isChange = false
                            scope.launch {
                                val nextPage =
                                    if (state.currentPage == 4) 0 else state.currentPage + 1
                                state.animateScrollToPage(nextPage)
                            }
                        }
                        .size(30.dp))
            }
        }
    }
}