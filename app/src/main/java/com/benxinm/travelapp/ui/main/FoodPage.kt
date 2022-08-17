package com.benxinm.travelapp.ui.main

import androidx.compose.animation.*

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.benxinm.travelapp.R
import com.benxinm.travelapp.logic.Repository
import com.benxinm.travelapp.ui.components.*
import com.benxinm.travelapp.util.noRippleClickable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FoodPage() {
    val lifecycleOwner = LocalLifecycleOwner.current
    var tabTopic by remember { mutableStateOf(Topic.First) }
    val pagerState = rememberPagerState()
    val scope= rememberCoroutineScope()
    Box(modifier = Modifier.systemBarsPadding()) {
        Column {
            Row(horizontalArrangement = Arrangement.Start) {
                Icon(painter = painterResource(id = R.drawable.ic_back), modifier = Modifier.size(30.dp), contentDescription = "")
            }
            Box(modifier = Modifier.padding(horizontal = 8.dp)) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        SubcomposeAsyncImage(
                            model = defaultPortrait,
                            contentDescription = "",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.Fit
                        ) {
                            val painterState = painter.state
                            if (painterState is AsyncImagePainter.State.Loading || painterState is AsyncImagePainter.State.Error) {
                                CircularProgressIndicator()
                            } else {
                                SubcomposeAsyncImageContent()
                            }
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "鱼丸做法", fontSize = 35.sp)
                        var likes by remember {
                            mutableStateOf(0)
                        }
                        var state by remember {
                            mutableStateOf(ScaleButtonState.IDLE)
                        }
                        Spacer(modifier = Modifier.width(20.dp))

                        AnimatedScaleButton(
                            state = state,
                            onToggle = {
                                state =
                                    if (state == ScaleButtonState.IDLE) ScaleButtonState.ACTIVE else ScaleButtonState.IDLE
                            },
                            onClick = { scaleButtonState ->
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
                            size = 30.dp,
                            activeColor = Color.Red,
                            idleColor = Color.LightGray,
                            idleSource = R.drawable.ic_heart_outlined,
                            activeSource = R.drawable.ic_heart_filleded
                        )
                        var collectState by remember {
                            mutableStateOf(ScaleButtonState.IDLE)
                        }
                        AnimatedScaleButton(
                            state = collectState,
                            onToggle = {
                                collectState =
                                    if (collectState == ScaleButtonState.IDLE) ScaleButtonState.ACTIVE else ScaleButtonState.IDLE
                            },
                            size = 30.dp,
                            activeColor = Color.Yellow,
                            idleColor = Color.LightGray,
                            idleSource = R.drawable.ic_collect01,
                            activeSource = R.drawable.ic_collected02
                        )
                    }
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)) {
                        AnimatedUnderLinerSelector02(
                            backgroundColor = Color.Transparent,
                            tabTopic = tabTopic,
                            onTabSelected = {currentPage->
                                scope.launch {
                                    pagerState.animateScrollToPage( if (currentPage==Topic.First) 0 else 1)
                                }
                                tabTopic=currentPage
                            })
                    }
                    HorizontalPager(count = 2,state=pagerState) {page ->
                        if (page==0){
                            var expanded by remember { mutableStateOf(false) }
                            Box(modifier = Modifier.fillMaxSize()) {
                                Column {
                                    Box(modifier = Modifier.animateContentSize()) {
                                        Surface(elevation = 8.dp) {
                                            Column {
                                                Text(text = "asdwdasdasjdhg iajwfhkjszdfhljkhdlkah kjs fjksadhf klashlsak hlass jh", fontSize = 30.sp, maxLines = if (expanded) Int.MAX_VALUE  else 1)
                                                Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.End) {
                                                    Box(modifier = Modifier.noRippleClickable {
                                                        expanded=!expanded
                                                    }) {
                                                        Row {
                                                            Text(text = if (expanded)"收起" else "展开")
                                                            Icon(painter = painterResource(id = if (expanded) R.drawable.ic_up else R.drawable.ic_down),  modifier = Modifier.size(20.dp), contentDescription ="" )
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }else{
                            Box(modifier = Modifier
                                .fillMaxSize()) {
                                LazyColumn(modifier = Modifier.fillMaxSize()){
                                    item { 
                                        Surface(elevation = 3.dp,modifier = Modifier.fillMaxWidth()) {
                                            Column(modifier = Modifier.padding(10.dp)) {
                                                Text(text = "配料:", fontSize = 25.sp)
                                                Text(text = "46548747654")
                                            }
                                        }
                                    }
                                    item {
                                        Column {
                                            Spacer(modifier = Modifier.height(20.dp))
                                            Surface(elevation = 3.dp,modifier = Modifier.fillMaxWidth()) {
                                                Column(modifier = Modifier.padding(10.dp)) {
                                                    Text(text = "步骤:", fontSize = 25.sp)
                                                    val list= listOf("123","asdwd","sadwdasdw","64654")
                                                    list.forEach {
                                                        Text(text = it)
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
    }
}

