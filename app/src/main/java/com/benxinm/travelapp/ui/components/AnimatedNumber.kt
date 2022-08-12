package com.benxinm.travelapp.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedNumber(num: Int) {
    AnimatedContent(targetState = num, transitionSpec = {
        if (targetState > initialState) {
            slideInVertically { height -> height } + fadeIn() with
                    slideOutVertically { height -> -height } + fadeOut()
        } else {
            slideInVertically { height -> -height } + fadeIn() with
                    slideOutVertically { height -> height } + fadeOut()
        }.using(
            SizeTransform(clip = false)
        )
    }, modifier = Modifier.offset(x = (-2).dp, y = (-2).dp)) { targetCount ->
        Text(text = "$targetCount")
    }
}