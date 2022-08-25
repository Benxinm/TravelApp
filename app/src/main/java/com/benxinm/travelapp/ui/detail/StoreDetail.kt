package com.benxinm.travelapp.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.benxinm.travelapp.R
import com.benxinm.travelapp.util.noRippleClickable

@Composable
fun StoreDetail(navController: NavController) {
    Column(modifier = Modifier
        .systemBarsPadding()
        .fillMaxSize()
        .padding(horizontal = 15.dp)) {
        Icon(painter = painterResource(id =R.drawable.ic_back), contentDescription ="",modifier=Modifier.size(30.dp).noRippleClickable { navController.popBackStack() } )
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            LazyColumn(modifier = Modifier.fillMaxSize()){
                item { 
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Image(painter = painterResource(id = R.drawable.m_3), contentDescription ="", modifier = Modifier
                            .size(90.dp)
                            .clip(
                                RoundedCornerShape(15.dp)
                            ), contentScale = ContentScale.Crop)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)) {
                            Text(text = "聚春园", fontSize = 20.sp, fontWeight = FontWeight.Black, modifier = Modifier.padding(bottom = 8.dp))
                            Text(text = "福建省福州市鼓楼区东街2号")
                            Text(text = "Tel: 0591-87502328")
                        }
                    }
                }
                item { 
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "简介", fontSize = 20.sp, fontWeight = FontWeight.Black, modifier = Modifier.padding(start = 10.dp))
                        Surface(modifier = Modifier.fillMaxWidth()) {
                            Box(modifier = Modifier.padding(10.dp)) {
                                Text("福州聚春园大酒店位于市区王者之地东街口，乃驰名神州百年老店聚春园继往开来之作，既有假山喷泉之情趣，又具宫殿广厦之恢宏，中西合璧，别具神韵。")
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "招牌菜", fontSize = 20.sp, fontWeight = FontWeight.Black, modifier = Modifier.padding(start = 10.dp))
                        Spacer(modifier = Modifier.height(15.dp))
                        StoreFoodLabel(id = R.drawable.meet, name = "荔枝肉", introduce ="酸甜可口，鲜香酥嫩" )
                        Spacer(modifier = Modifier.height(12.dp))
                        StoreFoodLabel(id = R.drawable.c_1, name = "佛跳墙", introduce ="鲜香可口，浓郁荤香" )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
                item {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Text(text = "8-25 官方发布", fontSize = 15.sp, color = Color.LightGray)
                    }
                }
            }
        }
    }
}

@Composable
fun StoreFoodLabel(id:Int,name:String,introduce:String) {
    Surface(elevation = 3.dp, shape = RoundedCornerShape(15.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Image(painter = painterResource(id = id), contentDescription ="", modifier = Modifier
                .size(90.dp)
                .clip(
                    RoundedCornerShape(15.dp)
                ), contentScale = ContentScale.Crop)
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)) {
                Text(text = name, fontSize = 20.sp, fontWeight = FontWeight.Black)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = introduce)

            }

        }
        Row (modifier = Modifier
            .fillMaxSize()
            .padding(end = 20.dp, top = 15.dp), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically){
            ZhaoPai()
        }
    }
}

@Composable
fun ZhaoPai() {
    Box {
        Image(painter = painterResource(id = R.drawable.ic_zhaopai), modifier = Modifier.size(65.dp),contentDescription = "")
        Text(text = "招牌菜", fontSize = 22.sp, modifier = Modifier
            .rotate(40f)
            .offset(x = 10.dp, y = 10.dp))
    }
}