package com.benxinm.travelapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.benxinm.travelapp.data.Page
import com.benxinm.travelapp.ui.theme.Purple200
import com.benxinm.travelapp.ui.theme.Purple500


private const val TabFadeInAnimationDuration = 100
private const val TabFadeOutAnimationDuration = 50
private const val TabFadeInAnimationDelay = 50

@Composable
fun NavigationBar(allPages: List<Page>, onTabSelected: (Page) -> Unit, currentPage: Page) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)) {
        Row(modifier = Modifier
            .selectableGroup()
            .fillMaxWidth()) {
            allPages.forEach{page ->
                PageTab(
                    text = page.text,
                    onSelected = { onTabSelected(page) },
                    selected = currentPage == page,
                    iconInt = page.iconInt,
                    modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun PageTab(text: String, onSelected: () -> Unit, selected: Boolean, iconInt: Int,modifier: Modifier=Modifier) {
    val color = Purple500
    val durationMillis = if (selected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration
    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }
    val tabTintColor by animateColorAsState(
        targetValue = if (selected) color else Purple200,
        animationSpec = animSpec
    )
    Column(
        modifier = modifier
            .height(80.dp).clip(RoundedCornerShape(50))
            .selectable(
                selected = selected,
                onClick = onSelected,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = true,
                    radius = Dp.Unspecified,
                    color = Color.Unspecified
                )
            )
            .clearAndSetSemantics { contentDescription = text },
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = iconInt),
            contentDescription = text,
            tint = tabTintColor,
            modifier = Modifier.height(32.dp),
        )
        Text(text = text)
    }
}