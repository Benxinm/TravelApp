package com.benxinm.travelapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.benxinm.travelapp.R

@Composable
fun HomeSearchBar() {
    Surface(elevation = 15.dp, modifier = Modifier.wrapContentSize(), shape = RoundedCornerShape(30.dp)) {
        Box(modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .height(40.dp)
            .padding(start = 12.dp, end = 12.dp)
            ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "搜索美食/饭店/地点等",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = 20.dp),
                    color = Color.LightGray
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "搜索",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 20.dp)
                )
            }
        }
    }
}