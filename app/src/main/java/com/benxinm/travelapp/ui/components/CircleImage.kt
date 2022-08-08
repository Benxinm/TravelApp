package com.benxinm.travelapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp


@Composable
fun CircleImage(@DrawableRes res:Int, size:Dp,onClicked:(Long)->Unit={}) {
    Image(
        painter = painterResource(id = res),
        contentDescription = "小头像",
        modifier = Modifier
            .size(size)
            .clip(CircleShape).clickable {
                //TODO onClicked(UserId)
                 },
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop
    )
}