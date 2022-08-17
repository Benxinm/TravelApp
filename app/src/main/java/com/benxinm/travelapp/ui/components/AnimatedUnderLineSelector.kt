package com.benxinm.travelapp.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benxinm.travelapp.util.noRippleClickable

@Composable
fun AnimatedUnderLineSelector(
    backgroundColor: Color,
    tabType: Type,
    onTabSelected: (type: Type) -> Unit,
) {
    TabRow(
        modifier = Modifier.width(250.dp),
        selectedTabIndex = tabType.ordinal,
        backgroundColor = backgroundColor,
        indicator = { tabPositions: List<TabPosition> ->
            UnderlineIndicator(tabPositions = tabPositions, tabType = tabType)
        }, divider = { Spacer(modifier = Modifier.width(10.dp)) }) {
        TextTab(title = "推荐", tabType == Type.Recommendation) { onTabSelected(Type.Recommendation) }
        TextTab(
            title = "老字号",
            tabType == Type.Restaurant,
            modifier = Modifier.offset(x = (-8).dp)
        ) { onTabSelected(Type.Restaurant) }
        TextTab(title = "海鲜", tabType == Type.SeaFood) { onTabSelected(Type.SeaFood) }
    }
}

@Composable
fun AnimatedUnderLinerSelector02(
    backgroundColor: Color,
    tabTopic: Topic,
    onTabSelected: (topic: Topic) -> Unit,
) {
    TabRow(
        modifier = Modifier.width(250.dp),
        selectedTabIndex = tabTopic.ordinal,
        backgroundColor = backgroundColor,
        indicator = { tabPositions: List<TabPosition> ->
            UnderlineIndicator02(tabPositions = tabPositions, tabTopic = tabTopic)
        }, divider = { Spacer(modifier = Modifier.width(10.dp)) }) {
        TextTab(title = "简介", tabTopic == Topic.First) { onTabSelected(Topic.First) }
        TextTab(title = "做法", tabTopic == Topic.Second) { onTabSelected(Topic.Second) }
    }
}


@Composable
private fun TextTab(
    title: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Text(
        text = title,
        modifier = modifier.noRippleClickable(onClick),
        fontSize = 23.sp,
        color = if (selected) Color.Black else Color.LightGray,
        fontWeight = if (selected) FontWeight.Bold else FontWeight.SemiBold
    )
}

@Composable
private fun UnderlineIndicator(tabPositions: List<TabPosition>, tabType: Type) {
    val transition = updateTransition(tabType, label = null)
    val indicatorLeft by transition.animateDp(transitionSpec = {
        if (Type.Recommendation isTransitioningTo Type.SeaFood) {
            spring(stiffness = Spring.StiffnessMediumLow)
        } else if (Type.Recommendation isTransitioningTo Type.Restaurant || Type.Restaurant isTransitioningTo Type.SeaFood) {
            spring(stiffness = Spring.StiffnessLow)
        } else if (Type.SeaFood isTransitioningTo Type.Recommendation) {
            spring(stiffness = Spring.StiffnessHigh)
        } else {
            spring(stiffness = Spring.StiffnessMedium)
        }
    }, label = "Indicator Left") { type ->
        tabPositions[type.ordinal].left
    }
    val indicatorRight by transition.animateDp(transitionSpec = {
        if (Type.Recommendation isTransitioningTo Type.SeaFood) {
            spring(stiffness = Spring.StiffnessHigh)
        } else if (Type.Recommendation isTransitioningTo Type.Restaurant || Type.Restaurant isTransitioningTo Type.SeaFood) {
            spring(stiffness = Spring.StiffnessMedium)
        } else if (Type.SeaFood isTransitioningTo Type.Recommendation) {
            spring(stiffness = Spring.StiffnessMediumLow)
        } else {
            spring(stiffness = Spring.StiffnessLow)
        }
    }, label = "Indicator Right") { type ->
        tabPositions[type.ordinal].right
    }
    Box(
        Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = indicatorLeft)
            .width(indicatorRight - indicatorLeft)
            .padding(4.dp)
            .fillMaxSize()
            .height(3.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
        ) {
            drawLine(
                Color.Yellow,
                Offset(x = if (tabType == Type.Restaurant) (-30).dp.value else 0f, y = 0f),
                Offset(
                    x = (indicatorRight - indicatorLeft + if (tabType == Type.Restaurant) 70.dp else 25.dp).value,
                    0f
                ),
                strokeWidth = 13f,
                cap = StrokeCap.Round
            )
        }
    }
}

@Composable
private fun UnderlineIndicator02(tabPositions: List<TabPosition>, tabTopic: Topic) {
    val transition = updateTransition(tabTopic, label = null)
    val indicatorLeft by transition.animateDp(transitionSpec = {
        if (Topic.First isTransitioningTo Topic.Second) {
            spring(stiffness = Spring.StiffnessLow)
        } else {
            spring(stiffness = Spring.StiffnessHigh)
        }
    }, label = "Indicator Left") { type ->
        tabPositions[type.ordinal].left
    }
    val indicatorRight by transition.animateDp(transitionSpec = {
        if (Topic.First isTransitioningTo Topic.Second) {
            spring(stiffness = Spring.StiffnessHigh)
        } else {
            spring(stiffness = Spring.StiffnessLow)
        }
    }, label = "Indicator Right") { type ->
        tabPositions[type.ordinal].right
    }
    Box(
        Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = indicatorLeft)
            .width(indicatorRight - indicatorLeft)
            .padding(4.dp)
            .fillMaxSize()
            .height(3.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
        ) {
            drawLine(
                Color.Yellow,
                Offset(x = 0f, y = 0f),
                Offset(
                    x = (indicatorRight - indicatorLeft + 25.dp).value,
                    0f
                ),
                strokeWidth = 13f,
                cap = StrokeCap.Round
            )
        }
    }
}


enum class Type {
    Recommendation, Restaurant, SeaFood
}

enum class Topic {
    First, Second
}