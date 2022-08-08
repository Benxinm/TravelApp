package com.benxinm.travelapp.ui.community

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.benxinm.travelapp.R
import com.benxinm.travelapp.data.Label
import com.benxinm.travelapp.ui.components.HomeSearchBar
import com.benxinm.travelapp.ui.components.Location
import com.benxinm.travelapp.ui.components.StaggeredVerticalGrid
import com.benxinm.travelapp.ui.components.WaterfallLabel
import com.benxinm.travelapp.ui.theme.BackgroundGrey

@Composable
fun Community() {
    val list = listOf(
        Label(R.drawable.c_1, "福州佛跳墙"),
        Label(R.drawable.c_2, "好吃餐厅推荐！"),
        Label(R.drawable.c_3, "超好吃仙草冻"),
        Label(R.drawable.c_4, "面包配牛奶！")
    )

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
            Box(modifier = Modifier
                .background(BackgroundGrey)
                .fillMaxSize()) {
                LazyColumn{
                    item {
                        StaggeredVerticalGrid(maxColumnWidth = 215.dp, modifier = Modifier.padding(horizontal = 15.dp)) {
                            list.forEach{label ->
                                WaterfallLabel(res = label.imgRes, text = label.text)
                            }
                        }
                    }
                }
            }
        }
    }
}