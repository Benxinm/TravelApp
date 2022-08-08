package com.benxinm.travelapp.ui.components

import android.location.Location
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benxinm.travelapp.R

@Composable
fun Location() {
    Box(modifier = Modifier.wrapContentSize()) {
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(top = 5.dp)) {
            Icon(painter = painterResource(id = R.drawable.ic_locate), contentDescription ="位置", modifier = Modifier.size(25.dp))
            Spacer(modifier = Modifier.width(3.dp))
            Text(text = "福州", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}