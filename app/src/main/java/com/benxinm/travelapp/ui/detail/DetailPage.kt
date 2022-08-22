package com.benxinm.travelapp.ui.detail

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.benxinm.travelapp.R
import com.benxinm.travelapp.logic.Repository
import com.benxinm.travelapp.ui.authentication.MyInputBox
import com.benxinm.travelapp.ui.components.*
import com.benxinm.travelapp.ui.main.DotsIndicator
import com.benxinm.travelapp.util.noRippleClickable
import com.benxinm.travelapp.viewModel.DetailViewModel
import com.benxinm.travelapp.viewModel.UserViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import java.text.SimpleDateFormat


const val topBarHeight = 40
@Composable
fun DetailPage(navController: NavController) {
    val detailViewModel: DetailViewModel = viewModel()
    val userViewModel: UserViewModel = viewModel()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val listState = rememberLazyListState()
    var page by remember {
        mutableStateOf(1)
    }
    LaunchedEffect(key1 = userViewModel.targetEmailForDetail) {
        detailViewModel.getComments(3, userViewModel.targetEmailForDetail, 1, page)
    }
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        DetailPageTopBar(navController)
        Box(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize()
                .padding(top = topBarHeight.dp)
                .background(Color.White)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
                item {
                    PictureContainer(detailViewModel)
                }
                item {
                    TextComponent(detailViewModel)
                }
                items(detailViewModel.commentList) { comment ->
                    Comment(comment.userName, comment.word, comment.time, comment.head)
                }
            }
        }
        DetailPageBottomBar(detailViewModel = detailViewModel, userViewModel = userViewModel)
    }
    detailViewModel.addCommentLiveData.observe(lifecycleOwner) {
        Log.d("motherfucker","get it")
        Log.d("motherfucker",if (it.isSuccess) "yes" else "no")
        if (it.isSuccess) {
            val toast = Toast.makeText(context, "评论成功", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
    detailViewModel.getCommentLiveData.observe(lifecycleOwner) {
        val result = it.getOrNull()
        if (result != null) {
            detailViewModel.commentList.addAll(result)
        }
    }
}

@Composable
fun DetailPageTopBar(navController: NavController) {
    Box(modifier = Modifier.background(Color.White)) {
        Box(modifier = Modifier.systemBarsPadding()) {
            Row(
                modifier = Modifier
                    .height(topBarHeight.dp)
                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "返回",
                    modifier = Modifier
                        .size(25.dp)
                        .clip(CircleShape)
                        .noRippleClickable {navController.popBackStack()}
                )
                CircleImage(res = R.drawable.dla01, size = 30.dp)
                Text(text = "Benxinm", modifier = Modifier.padding(start = 10.dp))
            }
            var subscribed by remember {
                mutableStateOf(false)
            }
            val colorState =
                animateColorAsState(targetValue = if (subscribed) Color.LightGray else Color.Red)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(topBarHeight.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    .offset(x = (-10).dp)
                    .clip(RoundedCornerShape(30.dp))
                    .border(
                        1.dp, colorState.value,
                        RoundedCornerShape(30.dp)
                    )
                    .clickable {
                        subscribed = !subscribed
                    }) {
                    Text(
                        text = if (subscribed) "已关注" else "关注",
                        color = colorState.value,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PictureContainer(detailViewModel: DetailViewModel) {
    // 图片参数
//    Image(
//        painter = painterResource(id = R.drawable.dla01),
//        contentDescription = "图片",
//        modifier = Modifier.fillMaxWidth(),
//        contentScale = ContentScale.Crop
//    )
    val state = rememberPagerState()
    Column {
        HorizontalPager(count = detailViewModel.urlList.size,state=state) {page->
            SubcomposeAsyncImage(model =detailViewModel.urlList[page], modifier = Modifier.fillMaxWidth().height(300.dp) ,contentScale = ContentScale.Fit , contentDescription ="") {
                val state=painter.state
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                    CircularProgressIndicator()
                } else {
                    SubcomposeAsyncImageContent()
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        DotsIndicator(totalDots = detailViewModel.urlList.size, selectedIndex = state.currentPage)
    }
}

@Composable
fun TextComponent(detailViewModel: DetailViewModel) {
    Box(modifier = Modifier.padding(8.dp)) {
        Column {
            Text(text = detailViewModel.detailModel!!.title, fontSize = 20.sp)
            Text(text = detailViewModel.detailModel!!.detail)
            LineDivider(10.dp)
        }
    }
}

@Composable
fun Comment(username: String, text: String, timestamp: Long, headPortrait: String) {
    val lifecycleOwner = LocalLifecycleOwner.current
    Box(modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)) {
        Row {
            CoilCircleImage(url = headPortrait, size = 30.dp)
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = username, color = Color.Gray)
                val time = SimpleDateFormat("MM-dd").format(timestamp)
                Text(buildAnnotatedString {
                    append(text)
                    withStyle(SpanStyle(fontSize = 15.sp, color = Color.LightGray)) {
                        append(if (text.length > 20) "\n$time" else "  $time")
                    }
                })
                LineDivider(10.dp)
            }
        }
        var state by remember {
            mutableStateOf(ScaleButtonState.IDLE)
        }
        var likes by remember {
            mutableStateOf(0)
        }
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        ) {
            AnimatedNumber(num = likes)
            AnimatedScaleButton(
                state = state,
                onToggle = {
                    state =
                        if (state == ScaleButtonState.IDLE) ScaleButtonState.ACTIVE else ScaleButtonState.IDLE
                }, onClick = { scaleButtonState ->
                    if (scaleButtonState == ScaleButtonState.IDLE) {
                        Repository.addLike("123", "123").observe(lifecycleOwner) {
                            if (it.isSuccess) {
                                likes++
                            }
                        }
                    } else {
                        Repository.cancelLike("456", "456").observe(lifecycleOwner) {
                            if (it.isSuccess) {
                                likes--
                            }
                        }
                    }
                },
                size = 20.dp,
                activeColor = Color.Red,
                idleColor = Color.LightGray,
                idleSource = R.drawable.ic_heart_outlined,
                activeSource = R.drawable.ic_heart_filleded
            )
        }
    }
}

@Composable
fun DetailPageBottomBar(
    detailViewModel: DetailViewModel,
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.Bottom, modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Column {
                Divider()
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MyInputBox(
                        value = detailViewModel.inputText,
                        onValueChange = { detailViewModel.inputText = it },
                        tint = "说点什么...",
                        modifier = Modifier.padding(8.dp),
                        width = 0.75f,
                        height = 45.dp
                    )
                    Box(modifier = Modifier
                        .size(width = 80.dp, height = 45.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color.Red)
                        .clickable {
                            if (detailViewModel.inputText.isNotEmpty()) {
                                detailViewModel.addComment(
                                    userViewModel.token,//TODO 之后改viewModel拿
                                    userViewModel.email,
                                    3,
                                    detailViewModel.inputText,
                                    userViewModel.targetEmail,
                                    1
                                )
                                detailViewModel.commentList.add(
                                    com.benxinm.travelapp.data.CommentWithHead(//TODO 数据解决
                                        "1",
                                        userViewModel.nickname,
                                        "1",
                                        detailViewModel.inputText,
                                        detailViewModel.target,
                                        1,
                                        0,
                                        System.currentTimeMillis(),
                                        userViewModel.defaultPortrait
                                    )
                                )
                                detailViewModel.inputText = ""
                            }
                        }) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "发送",
                                color = Color.White,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewBar() {
//    DetailPage()
}