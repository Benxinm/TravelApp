package com.benxinm.travelapp.ui.community

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.benxinm.travelapp.R
import com.benxinm.travelapp.data.Label
import com.benxinm.travelapp.data.Page
import com.benxinm.travelapp.data.responseModel.PostDetailModel
import com.benxinm.travelapp.logic.Repository
import com.benxinm.travelapp.ui.components.*
import com.benxinm.travelapp.ui.theme.BackgroundGrey
import com.benxinm.travelapp.util.noRippleClickable
import com.benxinm.travelapp.viewModel.CommunityViewModel
import com.benxinm.travelapp.viewModel.DetailViewModel
import com.benxinm.travelapp.viewModel.UserViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun Community(
    navController: NavController,
    detailViewModel: DetailViewModel,
    userViewModel: UserViewModel,
    communityViewModel: CommunityViewModel
) {
//    val communityViewModel: CommunityViewModel = viewModel()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        communityViewModel.getAllPost().observe(lifecycleOwner) { result ->
            val list = result.getOrNull()
            if (list != null && result.isSuccess) {
                communityViewModel.postList.clear()
                communityViewModel.postList.addAll(list)
            }
        }
    }
//    val list = listOf(
//        Label("https://s2.loli.net/2022/08/02/JTYD61w3jlQRWcu.jpg", "福州佛跳墙"),
//    )
    val list = listOf(
        Label(R.drawable.c_1.toString(), "福州佛跳墙"),
        Label(R.drawable.c_2.toString(), "好吃餐厅推荐"),
        Label(R.drawable.c_3.toString(), "超好吃仙草冻"),
        Label(R.drawable.c_4.toString(), "面包配牛奶")
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.systemBarsPadding()) {
            Column {
                Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_add),
                                tint = Color.Black,
                                contentDescription = "添加", modifier = Modifier.noRippleClickable {
                                    navController.navigate(Page.AddPost.name)
                                }
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            HomeSearchBar()
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Location(navController,userViewModel)
                    }
                }
                Box(
                    modifier = Modifier
                        .background(BackgroundGrey)
                        .fillMaxSize()
                ) {
                    LazyColumn {
                        item {
                            StaggeredVerticalGrid(
                                maxColumnWidth = 215.dp,
                                modifier = Modifier.padding(horizontal = 15.dp)
                            ) {
                                communityViewModel.postList/*list*/.forEach { label ->
                                    val likes = remember {
                                        mutableStateOf(0)
                                    }
                                    Log.d("WaterFallUrl", label.picUrl)
                                    WaterfallLabel(
                                        url = label./*imgRes*/picUrl/*, id = label.imgRes.toInt()*/,
                                        text = label./*text*/title, id = label.id,
                                        likes = likes.value, onSelected = {
                                            detailViewModel.target = label.id
                                            detailViewModel.targetNickname = label.nickname
                                            communityViewModel.getUrls(
                                                userViewModel.token,
                                                label.id
                                            )
                                            communityViewModel.getPostDetail(
                                                userViewModel.token,
                                                label.id
                                            )
                                            detailViewModel.urlList.clear()
                                            detailViewModel.urlList.add(label./*imgRes*/picUrl)
//                                        detailViewModel.detailModel= PostDetailModel("User#${list.indexOf(label)}","1",label.text,1L,1,1,label.text)
                                            navController.navigate(Page.Detail.name)
                                        }, userViewModel = userViewModel
                                    ) { scaleButtonState ->
                                        if (scaleButtonState == ScaleButtonState.IDLE) {
                                            Repository.addLike("123", "123")
                                                .observe(lifecycleOwner) {
                                                    if (it.isSuccess) {
                                                        likes.value++
                                                    }
                                                }
                                        } else {
                                            Repository.cancelLike("456", "456")
                                                .observe(lifecycleOwner) {
                                                    if (it.isSuccess) {
                                                        likes.value--
                                                    }/*else{
                                                likes.value--
                                            }*/
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
//        communityViewModel.getPostDetailLivedata.observe(lifecycleOwner) {
//            val result = it.getOrNull()
//            if (result != null) {
//                detailViewModel.detailModel = result[0]
//            }
//        }
//        communityViewModel.getUrlsLiveData.observe(lifecycleOwner) {
//            val result = it.getOrNull()
//            if (result != null) {
//                detailViewModel.urlList.clear()
//                detailViewModel.urlList.addAll(result)
//            }
//        }
    }
}