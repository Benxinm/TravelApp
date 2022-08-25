package com.benxinm.travelapp.ui.main

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.benxinm.travelapp.R
import com.benxinm.travelapp.data.Collect
import com.benxinm.travelapp.data.Page
import com.benxinm.travelapp.data.responseModel.TwoParamShowModel
import com.benxinm.travelapp.ui.components.AnimatedScaleButton
import com.benxinm.travelapp.ui.components.ScaleButtonState
import com.benxinm.travelapp.util.noRippleClickable
import com.benxinm.travelapp.viewModel.UserViewModel

@Composable
fun StorePage(navController: NavController,userViewModel: UserViewModel) {
    val list = listOf(TwoParamShowModel("达闽美食街夜游", R.drawable.m_1.toString()))
    Box(modifier = Modifier.systemBarsPadding()) {
        Box(modifier = Modifier.padding(horizontal = 10.dp)) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            modifier = Modifier
                                .size(25.dp)
                                .noRippleClickable {
                                    navController.popBackStack()
                                },
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "美味店铺", fontWeight = FontWeight.Black, fontSize = 30.sp)
                    }
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = ""
                        )
                    }
                }
                Box {
                    Text(text = "推荐", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth(0.15f)
                            .align(Alignment.BottomStart)
                    ) {
                        drawLine(
                            Color.Yellow,
                            Offset(x = 0f, y = 0f),
                            Offset(
                                x = size.width,
                                0f
                            ),
                            strokeWidth = 13f,
                            cap = StrokeCap.Round
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        StoreLabel(url = R.drawable.m_3.toString(), name ="聚春园" , text ="汤汁醇厚，海味山珍",navController ,userViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun StoreLabel(url: String, name: String, text: String,navController: NavController,userViewModel: UserViewModel) {
    Surface(shape = RoundedCornerShape(8.dp), elevation = 3.dp, modifier = Modifier.height(120.dp)) {
        Box(modifier = Modifier.padding(12.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.noRippleClickable {
                    navController.navigate(Page.StoreDetail.name)
                }
            ) {
                Image(
                    painter = painterResource(id = url.toInt()), modifier = Modifier
                        .size(90.dp)
                        .clip(
                            RoundedCornerShape(8.dp)
                        ), contentDescription = "", contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.fillMaxWidth().padding(start = 8.dp)) {
                    Box (modifier = Modifier.fillMaxWidth()){
                        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {
                            Text(text = name, fontSize = 25.sp, fontWeight = FontWeight.Black)
                        }
                        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                            var likeState by remember {
                                mutableStateOf(ScaleButtonState.IDLE)
                            }
                            var collectState by remember {
                                mutableStateOf(if (userViewModel.collectList.contains(Collect(R.drawable.m_3,name,Page.StoreDetail.name))) ScaleButtonState.ACTIVE else ScaleButtonState.IDLE)
                            }
                            AnimatedScaleButton(
                                state = likeState,
                                onToggle = {
                                    likeState =
                                        if (likeState == ScaleButtonState.IDLE) ScaleButtonState.ACTIVE else ScaleButtonState.IDLE
                                },
                                size = 20.dp,
                                activeColor = Color.Red,
                                idleColor = Color.LightGray,
                                idleSource = R.drawable.ic_heart_outlined,
                                activeSource = R.drawable.ic_heart_filleded
                            )
                            AnimatedScaleButton(
                                state = collectState,
                                onToggle = {
                                    collectState =
                                        if (collectState == ScaleButtonState.IDLE) {
                                            userViewModel.collectList.add(Collect(R.drawable.m_3,name,Page.StoreDetail.name))
                                            ScaleButtonState.ACTIVE
                                        }else{
                                            userViewModel.collectList.remove(Collect(R.drawable.m_3,name,Page.StoreDetail.name))
                                            ScaleButtonState.IDLE
                                        }
                                },
                                size = 20.dp,
                                activeColor = Color.Yellow,
                                idleColor = Color.LightGray,
                                idleSource = R.drawable.ic_collect01,
                                activeSource = R.drawable.ic_collected02
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = text, fontSize = 20.sp)
                }
            }
        }
    }
}