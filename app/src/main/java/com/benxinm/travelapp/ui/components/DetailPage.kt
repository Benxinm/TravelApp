package com.benxinm.travelapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benxinm.travelapp.R
import com.benxinm.travelapp.util.noRippleClickable
import org.w3c.dom.Comment

const val topBarHeight = 40
val list = listOf("1312321")

@Composable
fun DetailPage() {
    DetailPageTopBar()
    Box(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .padding(top = topBarHeight.dp)
            .background(Color.White)
    ) {
        LazyColumn {
            item {
                PictureContainer()
            }
            item {
                TextComponent()
            }
            items(3) {
                Comment()
            }
        }
    }
}

@Composable
fun DetailPageTopBar() {
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
                        .size(20.dp)
                        .clip(CircleShape)
                        .noRippleClickable { }
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

@Composable
fun PictureContainer() {
    //TODO 图片参数
    Image(
        painter = painterResource(id = R.drawable.dla01),
        contentDescription = "图片",
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun TextComponent() {
    //TODO title 和 content 放map传参进来 或者直接两个参数
    Box(modifier = Modifier.padding(8.dp)) {
        Column {
            Text(text = "Title", fontSize = 20.sp)
            Text(text = "Content~\nContent~\nContent~\nContent~Content~Content~Content~Content~")
            LineDivider(10.dp)
        }
    }
}

@Composable
fun Comment() {
    Box(modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)) {
        Row {
            CircleImage(res = R.drawable.dla01, size = 30.dp)
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "UserName", color = Color.Gray)
                Text(
                    text = "Content~\n" +
                            "Content~\n" +
                            "Content~"
                )
                LineDivider(10.dp)
            }
        }
        var state by remember {
            mutableStateOf(ScaleButtonState.IDLE)
        }
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        ) {
            AnimatedScaleButton(
                state = state,
                onToggle = {
                    state =
                        if (state == ScaleButtonState.IDLE) ScaleButtonState.ACTIVE else ScaleButtonState.IDLE
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


//@Preview
//@Composable
//fun PreviewBar() {
//    DetailPage()
//}