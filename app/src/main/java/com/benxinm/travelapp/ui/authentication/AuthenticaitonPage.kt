package com.benxinm.travelapp.ui.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp

@Composable
fun AuthenticationPage() {
    var username by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.systemBarsPadding()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BasicTextField(value = username, onValueChange = {
                username = it
            }, cursorBrush = SolidColor(Color.Black), decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.background(Color.LightGray, RoundedCornerShape(50)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(
                                start = 10.dp,
                                top = 7.dp,
                                bottom = 7.dp,
                                end = 7.dp
                            ), contentAlignment = Alignment.CenterStart
                    ) {
                        if (username.isEmpty()) {
                            Text(text = "账号", color = Color.LightGray)
                        }
                        innerTextField()
                    }
                }
            }, modifier = Modifier
                .height(55.dp)
                .fillMaxWidth(0.8f)
            )
        }
    }
}