package com.benxinm.travelapp.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.benxinm.travelapp.R
import com.benxinm.travelapp.ui.theme.white
import com.benxinm.travelapp.util.noRippleClickable


@Composable
fun GuideDetail(navController: NavController) {
    val listState = rememberLazyListState()
    val lifecycleOwner = LocalLifecycleOwner.current
    Box {

        LazyColumn(state = listState) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.m_1),
                    contentDescription = "background",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )
            }
            item {
                Box {
                    Surface(
                        color = white,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .offset(y = (-30).dp),
                        shape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)
                    ) {
                        Box(modifier = Modifier.padding(horizontal = 25.dp)) {
                            Column {
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(text = "夜游福州达闽美食街！荟萃百家美食！", fontSize = 25.sp, fontWeight = FontWeight.Black)
                                Text(text = "       第一次来福州想要遍览福州特色美食？位于福州市" +
                                            "中心的达闽美食街提供了一个好去处，与东街口，大洋" +
                                            "百货相邻，交通方便，商家众多。\n" +
                                            "       福州美食酸酸甜甜，味道清淡。不同的烹饪方式，\n" +
                                            "美妙食材与福州人的奇妙碰撞造就了福州美食的奇特\n" +
                                            "风味。", fontSize = 20.sp)
                                Image(painter = painterResource(id = R.drawable.m_1), contentDescription ="" )
                                Text(text = "      达闽街上好吃好玩。有独特福州糕点，有福州特色\n" +
                                        "美食，有鱼丸，有糯米饼，香甜可人，十分诱人。\n" +
                                        "      达闽街上的这家XX饭馆 好评众多，煮出了老一" +
                                        "辈人的福州味道。")
                                Text(text = "08-20 官方发布", color = Color.LightGray)
                            }
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(listOf(Color.DarkGray, Color.LightGray)),
                    alpha = 0.5f
                )
                .systemBarsPadding(), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                modifier = Modifier.size(30.dp).noRippleClickable { navController.popBackStack() },
                tint = Color.White,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "达闽夜游", color = Color.White, fontSize = 30.sp)
        }

    }
}