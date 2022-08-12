package com.benxinm.travelapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

const val defaultPortrait = "https://s2.loli.net/2022/08/03/2W9Nmf1SBpoRFdi.jpg"

@Composable
fun CircleImage(@DrawableRes res: Int, size: Dp, onClicked: (Long) -> Unit = {}) {
    Image(
        painter = painterResource(id = res),
        contentDescription = "小头像",
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .clickable {
                //TODO onClicked(UserId)
            },
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun CoilCircleImage(url: String = defaultPortrait, size: Dp, onClicked: (Long) -> Unit = {}) {
    SubcomposeAsyncImage(model = url, contentScale = ContentScale.Crop, modifier = Modifier
        .size(size)
        .clip(CircleShape)
        .clickable {
            //TODO onClicked(UserId)
        }, alignment = Alignment.Center, contentDescription = "头像") {
        val state=painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            CircularProgressIndicator()
        } else {
            SubcomposeAsyncImageContent()
        }
    }
}