package com.benxinm.travelapp.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
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
import com.benxinm.travelapp.viewModel.UserViewModel

@Composable
fun MapPage(navController: NavController, userViewModel: UserViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .noRippleClickable {
                        navController.popBackStack()
                    })
        }
        Box(
            modifier = Modifier
                .fillMaxSize(0.9f)
                .offset(x = 80.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.nanping),
                contentDescription = "",
                modifier = Modifier
                    .size(180.dp)
                    .clickable {
                        userViewModel.location = "南平"
                        navController.popBackStack()
                    })
            Image(painter = painterResource(id = R.drawable.ningde),
                contentDescription = "",
                modifier = Modifier
                    .size(140.dp)
                    .offset(x = 125.dp, y = 50.dp)
                    .clickable {
                        userViewModel.location = "宁德"
                        navController.popBackStack()
                    })
            Image(painter = painterResource(id = R.drawable.fuzhou),
                contentDescription = "",
                modifier = Modifier
                    .size(width = 120.dp, height = 110.dp)
                    .offset(x = 120.dp, y = 150.dp)
                    .clickable {
                        userViewModel.location = "福州"
                        navController.popBackStack()
                    })
            Image(painter = painterResource(id = R.drawable.sanming),
                contentDescription = "",
                modifier = Modifier
                    .size(width = 190.dp, height = 140.dp)
                    .offset(x = (-55).dp, y = 120.dp)
                    .clickable {
                        userViewModel.location = "三明"
                        navController.popBackStack()
                    })
            Image(painter = painterResource(id = R.drawable.putian),
                contentDescription = "",
                modifier = Modifier
                    .size(width = 70.dp, height = 50.dp)
                    .offset(x = 120.dp, y = 230.dp)
                    .clickable {
                        userViewModel.location = "莆田"
                        navController.popBackStack()
                    })
            Image(painter = painterResource(id = R.drawable.quanzhou),
                contentDescription = "",
                modifier = Modifier
                    .size(width = 100.dp, height = 125.dp)
                    .offset(x = 60.dp, y = 220.dp)
                    .clickable {
                        userViewModel.location = "泉州"
                        navController.popBackStack()
                    })
            Image(painter = painterResource(id = R.drawable.longyan),
                contentDescription = "",
                modifier = Modifier
                    .size(width = 150.dp, height = 140.dp)
                    .offset(x = (-90).dp, y = 220.dp)
                    .clickable {
                        userViewModel.location = "龙岩"
                        navController.popBackStack()
                    })
            Image(painter = painterResource(id = R.drawable.xiamen),
                contentDescription = "",
                modifier = Modifier
                    .size(width = 40.dp, height = 40.dp)
                    .offset(x = 85.dp, y = 320.dp)
                    .clickable {
                        userViewModel.location = "厦门"
                        navController.popBackStack()
                    })
            Image(painter = painterResource(id = R.drawable.zhangzhou),
                contentDescription = "",
                modifier = Modifier
                    .size(width = 100.dp, height = 160.dp)
                    .offset(x = (-5).dp, y = 280.dp)
                    .clickable {
                        userViewModel.location = "漳州"
                        navController.popBackStack()
                    })
            Text(text = "南平市", modifier = Modifier.offset(x = 60.dp, y = 90.dp), fontSize = 25.sp)
            Text(text = "宁德市", modifier = Modifier.offset(x = 165.dp, y = 110.dp), fontSize = 25.sp)
            Text(text = "福州市", modifier = Modifier.offset(x = 160.dp, y = 190.dp), fontSize = 25.sp)
            Text(text = "莆田市", modifier = Modifier.offset(x = 155.dp, y = 275.dp), fontSize = 25.sp)
            Text(text = "三明市", modifier = Modifier.offset(x = 5.dp, y = 185.dp), fontSize = 25.sp)
            Text(text = "泉州市", modifier = Modifier.offset(x = 70.dp, y =280.dp), fontSize = 25.sp)
            Text(text = "厦门市", modifier = Modifier.offset(x = 115.dp, y = 360.dp), fontSize = 25.sp)
            Text(text = "龙岩市", modifier = Modifier.offset(x = (-55).dp, y = 280.dp), fontSize = 25.sp)
            Text(text = "漳州市", modifier = Modifier.offset(x = 10.dp, y = 350.dp), fontSize = 25.sp)
        }
    }
}

@Preview
@Composable
fun PrewView() {
//    MapPage()
}