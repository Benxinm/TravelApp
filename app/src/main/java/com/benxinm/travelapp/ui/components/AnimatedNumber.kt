package com.benxinm.travelapp.ui.components

import androidx.compose.animation.*
import androidx.compose.material.Text
import androidx.compose.runtime.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedNumber() {
    var count by remember {
        mutableStateOf(0)
    }
    AnimatedContent(targetState = count, transitionSpec = {
        if (targetState > initialState) {
            slideInVertically { height -> height } + fadeIn() with
                    slideOutVertically { height -> -height } + fadeOut()
        } else {
            slideInVertically { height -> -height } + fadeIn() with
                    slideOutVertically { height -> height } + fadeOut()
        }.using(
            SizeTransform(clip = false)
        )
    }) { targetCount ->
        Text(text = "$targetCount")
    }
}