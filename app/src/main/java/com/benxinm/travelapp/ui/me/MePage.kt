package com.benxinm.travelapp.ui.me

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.benxinm.travelapp.R
import com.benxinm.travelapp.data.Flavor
import com.benxinm.travelapp.data.Page
import com.benxinm.travelapp.data.responseModel.MyCollectModel
import com.benxinm.travelapp.logic.Repository
import com.benxinm.travelapp.ui.components.*
import com.benxinm.travelapp.ui.theme.white
import com.benxinm.travelapp.ui.theme.yellow1
import com.benxinm.travelapp.util.noRippleClickable
import com.benxinm.travelapp.viewModel.CommunityViewModel
import com.benxinm.travelapp.viewModel.DetailViewModel
import com.benxinm.travelapp.viewModel.UserViewModel

@Composable
fun MePage(navController: NavController,userViewModel: UserViewModel,detailViewModel: DetailViewModel,communityViewModel: CommunityViewModel) {
    val listState = rememberLazyListState()
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(key1 = userViewModel.targetEmail) {
        userViewModel.getFanSubNum(userViewModel.targetEmail)
        Repository.getMyCollect(userViewModel.targetEmail).observe(lifecycleOwner) {
            val result = it.getOrNull()
            if (result != null) {
//                collectList.addAll(result)
            }
        }
    }
    LaunchedEffect(key1 = true){
        Repository.checkBottle(userViewModel.token,userViewModel.email).observe(lifecycleOwner){
            val result=it.getOrNull()
            if (result!=null){
                for (list in result){
                    val index= when(list[0]){
                        "1"->3
                        "2"->2
                        "3"->0
                        "4"->4
                        "5"->1
                        else->0
                    }
                    userViewModel.degrees[index]=list[1].toFloat()
                }
            }
        }
        Repository.getMyPost(userViewModel.token,userViewModel.email).observe(lifecycleOwner){result->
            val list = result.getOrNull()
            if (list != null && result.isSuccess) {
                userViewModel.myPostList.clear()
                userViewModel.myPostList.addAll(list)
            }
        }
    }
    userViewModel.fanSubNumLiveData.observe(lifecycleOwner) { result ->
        val map = result.getOrNull()
        if (map != null) {
            userViewModel.fanNum = map["my_fans"]!!
            userViewModel.subNum = map["my_follow"]!!
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
                        color = white,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(430.dp)
                            .offset(y = (-30).dp),
                        shape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)
                    ) {
                        Box(modifier = Modifier.padding(horizontal = 25.dp)) {
                            Column {
                                Row(
                                    modifier = Modifier
                                        .height(35.dp)
                                        .fillMaxWidth(0.9f), horizontalArrangement = Arrangement.End
                                ) {
                                    Box(modifier = Modifier
                                        .offset(y = 12.dp)
                                        .clip(RoundedCornerShape(30.dp))
                                        .background(yellow1)
                                        .clickable {
                                            navController.navigate(Page.Personal.name)
                                        }) {
                                        Text(
                                            text = "编辑资料",
                                            fontSize = 10.sp,
                                            modifier = Modifier.padding(
                                                horizontal = 10.dp,
                                                vertical = 5.dp
                                            )
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                                Row(modifier = Modifier.padding(horizontal = 6.dp)) {
                                    Text(
                                        text = userViewModel.nickname,
                                        fontSize = 30.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(buildAnnotatedString {
                                        append("关注 ")
                                        withStyle(
                                            SpanStyle(
                                                fontSize = 21.sp,
                                            )
                                        ) {
                                            append(userViewModel.subNum.toString())   //TODO 关注粉丝
                                        }
                                        append(" ")
                                        append("粉丝 ")
                                        withStyle(
                                            SpanStyle(
                                                fontSize = 21.sp,
                                            )
                                        ) {
                                            append(userViewModel.fanNum.toString())   //TODO 关注粉丝
                                        }
                                    }, modifier = Modifier.padding(top = 8.dp))

                                }
                                Text(
                                    text = "走遍天下，吃遍天下",
                                    modifier = Modifier.padding(horizontal = 6.dp)
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Surface(
                                    modifier = Modifier
                                        .height(120.dp)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp),
                                    elevation = 10.dp
                                ) {
                                    Box(modifier = Modifier.padding(10.dp)) {
                                        Column {
                                            Text(
                                                text = "口味",
                                                modifier = Modifier.padding(start = 5.dp)
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Row(
                                                modifier = Modifier
                                                    .padding(start = 10.dp)
                                                    .noRippleClickable { navController.navigate(Page.FlavourBottle.name) },

                                            ) {
                                                FlavorBottle(flavor = Flavor.Sour, degree = userViewModel.degrees[0])
                                                FlavorBottle(flavor = Flavor.Sweet, degree = userViewModel.degrees[1])
                                                FlavorBottle(flavor = Flavor.Salty, degree = userViewModel.degrees[2])
                                                FlavorBottle(flavor = Flavor.Bitter, degree = userViewModel.degrees[3])
                                                FlavorBottle(flavor = Flavor.Spicy, degree = userViewModel.degrees[4])
                                            }
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                                Surface(
                                    modifier = Modifier
                                        .height(150.dp)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp),
                                    elevation = 10.dp
                                ) {
                                    Box(modifier = Modifier.padding(10.dp)) {
                                        Column {
                                            Text(text = "我的收藏")
                                            LazyRow {
                                                items(userViewModel.collectList) { it ->
                                                    Box(modifier = Modifier
                                                        .padding(end = 8.dp)
                                                        .width(90.dp)) {
                                                        Column(
                                                            modifier = Modifier
                                                                .fillMaxSize()
                                                                .noRippleClickable {
                                                                    if (it.route.isEmpty()) {
                                                                        detailViewModel.target =
                                                                            it.id
                                                                        communityViewModel.getUrls(
                                                                            userViewModel.token,
                                                                            it.id
                                                                        )
                                                                        communityViewModel.getPostDetail(
                                                                            userViewModel.token,
                                                                            it.id
                                                                        )
                                                                        detailViewModel.urlList.clear()
                                                                        navController.navigate(Page.Detail.name)
                                                                    } else {
                                                                        navController.navigate(it.route)
                                                                    }
                                                                },
                                                            verticalArrangement = Arrangement.Center
                                                        ) {
                                                            SubcomposeAsyncImage(
                                                                model = if (it.url.isEmpty()) it.imgRes else it.url,
                                                                contentScale = ContentScale.Crop,
                                                                modifier = Modifier
                                                                    .size(90.dp)
                                                                    .clip(RoundedCornerShape(5.dp)),
                                                                contentDescription = ""
                                                            ) {
                                                                val state = painter.state
                                                                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                                                                    CircularProgressIndicator()
                                                                } else {
                                                                    SubcomposeAsyncImageContent()
                                                                }
                                                            }
                                                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                                                                Text(text = it.name, fontSize = 13.sp)
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
                    SubcomposeAsyncImage(model = userViewModel.userProfile, contentDescription = "头像",
                        modifier = Modifier
                            .offset(x = 30.dp, y = (-80).dp)
                            .size(100.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Yellow, CircleShape),
                        contentScale = ContentScale.Crop) {
                        val state=painter.state
                        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                            CircularProgressIndicator(modifier = Modifier.size(80.dp))
                        } else {
                            SubcomposeAsyncImageContent()
                        }
                    }
//                    Image(
//                        painter = painterResource(id = R.drawable.dla01),
//                        contentDescription = "头像",
//                        modifier = Modifier
//                            .offset(x = 30.dp, y = (-80).dp)
//                            .size(100.dp)
//                            .clip(CircleShape)
//                            .border(2.dp, Color.Yellow, CircleShape)
//                            .background(Color.Black),
//                        contentScale = ContentScale.Crop
//                    )
                }
            }
            item {
                Column(modifier=Modifier.fillMaxSize().offset(y=(-30).dp)) {
                    Text(text = "帖子", fontSize = 22.sp, fontWeight = FontWeight.Black, modifier = Modifier.padding(start = 25.dp))
                    StaggeredVerticalGrid(
                        maxColumnWidth = 215.dp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    ) {
                        userViewModel.myPostList/*list*/.forEach { label ->
                            val likes = remember {
                                mutableStateOf(0)
                            }
                            Log.d("WaterFallUrl", label.picUrl)
                            WaterfallLabel(
                                url = label./*imgRes*/picUrl/*, id = label.imgRes.toInt()*/,
                                text = label./*text*/title,
                                likes = likes.value, onSelected = {
                                    detailViewModel.target = label.id
                                    detailViewModel.targetNickname = label.nickname
                                    communityViewModel.getUrls(
                                        userViewModel.token,
                                        label.id
                                    )
                                    communityViewModel.getPostDetail(
                                        userViewModel.token,
                                        label.id
                                    )
                                    detailViewModel.urlList.clear()
//                                        detailViewModel.detailModel= PostDetailModel("User#${list.indexOf(label)}","1",label.text,1L,1,1,label.text)
                                    navController.navigate(Page.Detail.name)
                                }
                            , userViewModel = userViewModel) { scaleButtonState ->
                                if (scaleButtonState == ScaleButtonState.IDLE) {
                                    Repository.addLike("123", "123")
                                        .observe(lifecycleOwner) {
                                            if (it.isSuccess) {
                                                likes.value++
                                            }
                                        }
                                } else {
                                    Repository.cancelLike("456", "456")
                                        .observe(lifecycleOwner) {
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
        val startAnimation by remember {
            derivedStateOf {
                listState.firstVisibleItemIndex > 0
            }
        }
        MePageTopBar(startAnimation,userViewModel)
    }
}

@Composable
fun MePageTopBar(startAnimation: Boolean,userViewModel: UserViewModel) {
    val topBarColor by animateColorAsState(
        targetValue = if (startAnimation) Color.DarkGray else Color.Transparent,
        animationSpec = tween(delayMillis = 0, durationMillis = 300, easing = LinearOutSlowInEasing)
    )
    Box(modifier = Modifier.background(topBarColor)) {
        Box(modifier = Modifier.systemBarsPadding()) {
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
                    SubcomposeAsyncImage(model =userViewModel.userProfile , contentDescription ="小头像",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop ) {
                        val state=painter.state
                        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                            CircularProgressIndicator(modifier = Modifier.size(10.dp))
                        } else {
                            SubcomposeAsyncImageContent()
                        }
                    }
                }
            }
        }
    }
}

