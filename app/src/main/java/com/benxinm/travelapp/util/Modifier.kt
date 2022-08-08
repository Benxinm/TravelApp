package com.benxinm.travelapp.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

inline fun Modifier.noRippleClickable(noinline onClick:()->Unit):Modifier=composed {
    clickable(indication = null, interactionSource = remember {
        MutableInteractionSource()
    }, onClick = onClick)
}