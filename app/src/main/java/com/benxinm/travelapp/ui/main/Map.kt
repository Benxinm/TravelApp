package com.benxinm.travelapp.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.benxinm.travelapp.R
import com.benxinm.travelapp.util.noRippleClickable

@Composable
fun MapPage(navController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxSize()
            .offset(x = 100.dp)) {

            Image(painter = painterResource(id = R.drawable.nanping), contentDescription ="" , modifier = Modifier
                .size(200.dp)
                .noRippleClickable { })
            Image(painter = painterResource(id = R.drawable.ningde), contentDescription ="" , modifier = Modifier
                .size(160.dp)
                .offset(x = 135.dp, y = 60.dp)
                .noRippleClickable { })
            Image(painter = painterResource(id = R.drawable.fuzhou), contentDescription ="" , modifier = Modifier
                .size(width = 140.dp, height = 130.dp)
                .offset(x = 125.dp, y = 175.dp)
                .noRippleClickable { })
            Image(painter = painterResource(id = R.drawable.sanming), contentDescription ="" , modifier = Modifier
                .size(width = 210.dp, height = 160.dp)
                .offset(x = (-55).dp, y = 125.dp)
                .noRippleClickable { })
            Image(painter = painterResource(id = R.drawable.putian), contentDescription ="" ,modifier = Modifier
                .size(width = 90.dp, height = 70.dp)
                .offset(x = 135.dp, y = 265.dp)
                .noRippleClickable { })
            Image(painter = painterResource(id = R.drawable.quanzhou), contentDescription ="" ,modifier = Modifier
                .size(width = 120.dp, height = 135.dp)
                .offset(x = 60.dp, y = 250.dp)
                .noRippleClickable { })
            Image(painter = painterResource(id = R.drawable.longyan), contentDescription ="" ,modifier = Modifier
                .size(width = 170.dp, height = 160.dp)
                .offset(x = (-100).dp, y = 240.dp)
                .noRippleClickable { })
            Image(painter = painterResource(id = R.drawable.xiamen), contentDescription ="" ,modifier = Modifier
                .size(width = 50.dp, height = 50.dp)
                .offset(x = 85.dp, y = 350.dp)
                .noRippleClickable { })
            Image(painter = painterResource(id = R.drawable.zhangzhou), contentDescription = "",modifier = Modifier
                .size(width = 120.dp, height = 180.dp)
                .offset(x = (-10).dp, y = 320.dp)
                .noRippleClickable { })
            Text(text = "南平市", modifier = Modifier.offset(x = 70.dp, y = 100.dp), fontSize = 25.sp)
            Text(text = "宁德市",modifier = Modifier.offset(x = 175.dp, y = 130.dp), fontSize = 25.sp)
            Text(text = "福州市",modifier = Modifier.offset(x = 160.dp, y = 220.dp), fontSize = 25.sp)
            Text(text = "莆田市",modifier = Modifier.offset(x = 185.dp, y = 315.dp), fontSize = 25.sp)
            Text(text = "三明市",modifier = Modifier.offset(x = 5.dp, y = 195.dp), fontSize = 25.sp)
            Text(text = "泉州市",modifier = Modifier.offset(x = 70.dp, y = 300.dp), fontSize = 25.sp)
            Text(text = "厦门市",modifier = Modifier.offset(x = 115.dp, y = 400.dp), fontSize = 25.sp)
            Text(text = "龙岩市",modifier = Modifier.offset(x = (-50).dp, y = 300.dp), fontSize = 25.sp)
            Text(text = "漳州市",modifier = Modifier.offset(x = 10.dp, y = 400.dp), fontSize = 25.sp)
        }
    }
}

@Preview
@Composable
fun PrewView() {
//    Map()
}