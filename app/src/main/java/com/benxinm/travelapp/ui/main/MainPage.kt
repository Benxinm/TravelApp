package com.benxinm.travelapp.ui.main

import android.widget.Space
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.benxinm.travelapp.R
import com.benxinm.travelapp.data.Page
import com.benxinm.travelapp.data.Sort
import com.benxinm.travelapp.logic.Repository
import com.benxinm.travelapp.ui.components.*
import com.benxinm.travelapp.util.noRippleClickable
import com.benxinm.travelapp.viewModel.UserViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import java.nio.file.attribute.BasicFileAttributeView

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainPage(navController: NavController,userViewModel: UserViewModel) {
    var tabType by remember { mutableStateOf(Type.Recommendation) }
    val lifecycleOwner= LocalLifecycleOwner.current
    Box(modifier = Modifier.systemBarsPadding()) {
        Box(modifier = Modifier.padding(horizontal = 10.dp)) {
            val state = rememberPagerState()
            Column {
                Row(horizontalArrangement = Arrangement.Center) {
                    Location(navController,userViewModel)
                    Spacer(modifier = Modifier.width(6.dp))
                    HomeSearchBar()
                }
                HorizontalSlider(state = state)
                Spacer(modifier = Modifier.height(4.dp))
                DotsIndicator(totalDots = 3, selectedIndex = state.currentPage)
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
                ) {
                    BasicItem(res = R.drawable.ic_shop, text = "美味店铺", modifier = Modifier
                        .weight(1f)
                        .noRippleClickable { navController.navigate(Page.Store.name) })
                    BasicItem(res = R.drawable.ic_speciality, text = "特色菜肴",Modifier.weight(1f).noRippleClickable { navController.navigate(Page.Food.name) })
                    BasicItem(res = R.drawable.ic_deliciouslibrary, text = "美食攻略",
                        Modifier
                            .weight(1f)
                            .noRippleClickable {
                                navController.navigate(
                                    Page.Guide.name
                                )
                            })
                }
                AnimatedUnderLineSelector(
                    backgroundColor = Color.Transparent,
                    tabType = tabType,
                    onTabSelected = { currentPage ->
                        tabType = currentPage
                    }
                )
                LazyVerticalGrid(columns = GridCells.Fixed(2)){
                    item {
                        Box(modifier = Modifier.padding(5.dp)){
                            MainPageLabel(sort = Sort.Restaurant, id = R.drawable.m_3, route = Page.StoreDetail.name,onSelected = {navController.navigate(Page.StoreDetail.name)},name ="聚春园", userViewModel = userViewModel ){
                                    scaleButtonState ->
                                if (scaleButtonState == ScaleButtonState.IDLE) {
                                    Repository.addLike("123", "123")
                                        .observe(lifecycleOwner) {
//                                        if (it.isSuccess) {
//                                            likes.value++
//                                        }
                                        }
                                } else {
                                    Repository.cancelLike("456", "456")
                                        .observe(lifecycleOwner) {
//
                                        }
                                }
                            }
                        }
                    }
                    item {
                        Box(modifier = Modifier.padding(8.dp)) {
                            MainPageLabel(sort = Sort.Strategy, id = R.drawable.m_1, route = Page.GuideDetail.name ,onSelected = {navController.navigate(Page.GuideDetail .name)},name ="达闽美食街夜游", userViewModel = userViewModel){
                                    scaleButtonState ->
                                if (scaleButtonState == ScaleButtonState.IDLE) {
                                    Repository.addLike("123", "123")
                                        .observe(lifecycleOwner) {
//                                        if (it.isSuccess) {
//                                            likes.value++
//                                        }
                                        }
                                } else {
                                    Repository.cancelLike("456", "456")
                                        .observe(lifecycleOwner) {
//                                        if (it.isSuccess) {
//                                            likes.value--
//                                        }/*else{
//                                                likes.value--
//                                            }*/
                                        }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalSlider(state: PagerState) {
    val imageUrl = remember {//TODO 预留接口
        mutableStateOf("")
    }
    val list = listOf(R.drawable.m_1, R.drawable.m_3, R.drawable.m_4)
    Column {
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalPager(state = state, count = 3) { page ->
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier.size(width = 460.dp, height = 200.dp)
            ) {
                Image(
                    painter = painterResource(id = list[page]), modifier = Modifier.clip(
                        RoundedCornerShape(10.dp)
                    ), contentScale = ContentScale.Crop, contentDescription = ""
                )
            }
        }
    }
    LaunchedEffect(key1 = state.currentPage) {
        delay(3000)
        var newPosition = state.currentPage + 1
        if (newPosition > 2) newPosition = 0
        state.animateScrollToPage(newPosition)
    }
}

@Composable
fun DotsIndicator(totalDots: Int, selectedIndex: Int) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center
    ) {
        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.DarkGray)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.LightGray)
                )
            }
            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Composable
fun BasicItem(@DrawableRes res: Int, text: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier.padding(10.dp)) {
        Column(verticalArrangement = Arrangement.Center, modifier = modifier.height(100.dp)) {
            Image(painter = painterResource(id = res), modifier= modifier.size(90.dp),contentDescription = text)
            Row( horizontalArrangement = Arrangement.Center) {
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = text, fontSize = 20.sp, fontWeight = FontWeight.Black)
            }
        }
    }
}
