package com.benxinm.travelapp.ui.me

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.benxinm.travelapp.R
import com.benxinm.travelapp.viewModel.UserViewModel

@Composable
fun MePage() {
    val listState = rememberLazyListState()
    val lifecycleOwner = LocalLifecycleOwner.current
    val userViewModel:UserViewModel= viewModel()
    LaunchedEffect(key1 = userViewModel.targetEmail){
        userViewModel.getFanSubNum(userViewModel.targetEmail)
    }
    userViewModel.fanSubNumLiveData.observe(lifecycleOwner){result->
        val map =result.getOrNull()
        if (map!=null){
            userViewModel.fanNum=map["my_fans"]!!
            userViewModel.subNum=map["my_follow"]!!
        }
    }
    Box {
        LazyColumn(state = listState) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.dla01),
                    contentDescription = "background",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )
            }
            item {
                Box {
                    Surface(
                        color = Color.Cyan,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1500.dp)
                            .offset(y = (-30).dp),
                        shape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp),
                        elevation = 5.dp
                    ) {
                       Box(modifier = Modifier.padding(horizontal = 25.dp)) {
                           Column {
                               Row(modifier = Modifier.height(35.dp)) {

                               }
                               Spacer(modifier = Modifier.height(20.dp))
                               Row (modifier = Modifier.padding(horizontal = 6.dp)){
                                   Text(text = "吃货旅人", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                                   Spacer(modifier = Modifier.width(10.dp))
                                   Text(buildAnnotatedString {
                                       append("关注 ")
                                       withStyle(SpanStyle(
                                           fontSize = 21.sp,
                                       )){
                                           append(userViewModel.subNum.toString())   //TODO 关注粉丝
                                       }
                                       append(" ")
                                       append("粉丝 ")
                                       withStyle(SpanStyle(
                                           fontSize = 21.sp,
                                       )){
                                           append(userViewModel.fanNum.toString())   //TODO 关注粉丝
                                       }
                                   }, modifier = Modifier.padding(top = 8.dp))

                               }
                               Text(text = "走遍天下，吃遍天下",modifier = Modifier.padding(horizontal = 6.dp))
                               Spacer(modifier = Modifier.height(10.dp))
                               Surface(modifier = Modifier
                                   .height(100.dp)
                                   .fillMaxWidth(),shape = RoundedCornerShape(10.dp),elevation = 10.dp) {
                                   Box(modifier = Modifier.padding(10.dp)) {
                                       Text(text = "口味")
                                   }
                               }
                               Spacer(modifier = Modifier.height(20.dp))
                               Surface(modifier = Modifier
                                   .height(100.dp)
                                   .fillMaxWidth(),shape = RoundedCornerShape(10.dp),elevation = 10.dp) {
                                   Box(modifier = Modifier.padding(10.dp)) {
                                       Text(text = "我的收藏")
                                   }
                               }
                           }
                       }
                    }
                    Image(
                        painter = painterResource(id = R.drawable.dla01),
                        contentDescription = "头像",
                        modifier = Modifier
                            .offset(x = 30.dp, y = (-80).dp)
                            .size(100.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Yellow, CircleShape)
                            .background(Color.Black),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        val startAnimation by remember {
            derivedStateOf {
                listState.firstVisibleItemIndex > 0
            }
        }
        MePageTopBar(startAnimation)
    }
}

@Composable
fun MePageTopBar(startAnimation: Boolean) {
    val topBarColor by animateColorAsState(
        targetValue = if (startAnimation) Color.DarkGray else Color.Transparent,
        animationSpec = tween(delayMillis = 0, durationMillis = 300, easing = LinearOutSlowInEasing)
    )
    Box(modifier = Modifier.background(topBarColor)) {
        Box(modifier = Modifier.systemBarsPadding()) {
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "返回",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(start = 10.dp)
                        .clip(CircleShape)
                        .clickable { }, tint = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedVisibility(
                    visible = startAnimation,
                    enter = expandVertically(
                        expandFrom = Alignment.Top, animationSpec = tween(
                            delayMillis = 100, durationMillis = 500, easing = LinearOutSlowInEasing
                        )
                    ),
                    exit = shrinkVertically(
                        shrinkTowards = Alignment.Top, animationSpec = tween(
                            delayMillis = 100, durationMillis = 500, easing = LinearOutSlowInEasing
                        )
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dla01),
                        contentDescription = "小头像",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

