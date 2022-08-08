package com.benxinm.travelapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benxinm.travelapp.R
import com.benxinm.travelapp.data.Sort

@Composable
fun WaterfallLabel(@DrawableRes res:Int, text:String) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.width(width = 215.dp).padding(5.dp), elevation = 5.dp
    ){
        Column {
            Image(//TODO 加载网络图片
                painter = painterResource(id = res),
                modifier = Modifier.width(width = 200.dp),
                contentScale = ContentScale.Fit,
                contentDescription = ""
            )

            Box(modifier = Modifier.padding(horizontal = 10.dp)) {
                Column(verticalArrangement = Arrangement.Center) {
                    Text(text = text)
                    var likeState by remember {
                        mutableStateOf(ScaleButtonState.IDLE)
                    }
                    var collectState by remember {
                        mutableStateOf(ScaleButtonState.IDLE)
                    }
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.width(180.dp)
                    ) {
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
                                    if (collectState == ScaleButtonState.IDLE) ScaleButtonState.ACTIVE else ScaleButtonState.IDLE
                            },
                            size = 20.dp,
                            activeColor = Color.Yellow,
                            idleColor = Color.LightGray,
                            idleSource = R.drawable.ic_collect01,
                            activeSource = R.drawable.ic_collected02
                        )
                    }
                }
            }
        }
    }
}



@Composable
fun MainPageLabel(sort: Sort, name: String) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.size(width = 215.dp, height = 210.dp), elevation = 5.dp
    ) {
        val type = when (sort) {
            Sort.Food -> "美食"
            Sort.Strategy -> "攻略"
            Sort.Restaurant -> "饭店"
        }
        val color = when (sort) {
            Sort.Food -> Color(0xFFf6f1af)
            Sort.Strategy -> Color(0xFFcfe9ef)
            Sort.Restaurant -> Color(0xFFee7c74)
        }
        Column {
            Image(//TODO 加载网络图片
                painter = painterResource(id = R.drawable.dla01),
                modifier = Modifier.size(width = 215.dp, height = 162.dp),
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
            Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                Column(verticalArrangement = Arrangement.Center) {
                    Text(text = name, maxLines = 1)
                    var likeState by remember {
                        mutableStateOf(ScaleButtonState.IDLE)
                    }
                    var collectState by remember {
                        mutableStateOf(ScaleButtonState.IDLE)
                    }
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.width(180.dp)
                    ) {
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
                                    if (collectState == ScaleButtonState.IDLE) ScaleButtonState.ACTIVE else ScaleButtonState.IDLE
                            },
                            size = 20.dp,
                            activeColor = Color.Yellow,
                            idleColor = Color.LightGray,
                            idleSource = R.drawable.ic_collect01,
                            activeSource = R.drawable.ic_collected02
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp),
            horizontalArrangement = Arrangement.End,
        ) {
            Box(
                modifier = Modifier
                    .offset(x = (-10).dp, y = 10.dp)
                    .size(width = 28.dp, height = 12.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color)
            ) {
                Text(
                    text = type,
                    fontSize = 8.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }


    }
}

@Preview
@Composable
fun PreviewLabel() {
    MainPageLabel(Sort.Food, "Content~Content~")
}