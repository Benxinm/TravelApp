package com.benxinm.travelapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.benxinm.travelapp.R
import com.benxinm.travelapp.util.noRippleClickable


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedScaleButton(
    modifier: Modifier=Modifier,
    state: ScaleButtonState,
    idleColor: Color,
    activeColor:Color,
    @DrawableRes idleSource:Int,
    @DrawableRes activeSource:Int,
    onToggle: () -> Unit,
    size:Dp
) {
    val transition = updateTransition(
        targetState = if (state == ScaleButtonState.IDLE) ScaleButtonState.ACTIVE else ScaleButtonState.IDLE,
        label = ""
    )
    val scaleButtonColor by transition.animateColor(label = "") { state ->
        when (state) {
            ScaleButtonState.IDLE -> activeColor
            ScaleButtonState.ACTIVE -> idleColor
        }
    }
    AnimatedContent(
        targetState = if (state == ScaleButtonState.IDLE) ScaleButtonState.ACTIVE else ScaleButtonState.IDLE,
        transitionSpec = {
            scaleIn() with scaleOut()
        }) {
        Icon(
            painter = painterResource(id = if (ScaleButtonState.IDLE == state) idleSource else activeSource),
            contentDescription = "",
            tint = scaleButtonColor,
            modifier = modifier
                .size(size)
                .noRippleClickable(onClick = onToggle)
        )
    }
}


enum class ScaleButtonState {
    IDLE, ACTIVE
}
