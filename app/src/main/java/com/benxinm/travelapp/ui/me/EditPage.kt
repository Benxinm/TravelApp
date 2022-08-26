package com.benxinm.travelapp.ui.me

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.benxinm.travelapp.R
import com.benxinm.travelapp.logic.Repository
import com.benxinm.travelapp.logic.dao.UserDao
import com.benxinm.travelapp.ui.authentication.MyInputBox
import com.benxinm.travelapp.ui.theme.yellow1
import com.benxinm.travelapp.util.FileUtil
import com.benxinm.travelapp.util.noRippleClickable
import com.benxinm.travelapp.viewModel.UserViewModel
import com.google.accompanist.insets.ui.Scaffold
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

const val defaultUri = "https://s2.loli.net/2022/08/03/2W9Nmf1SBpoRFdi.jpg"

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EditPage(navController: NavController, userViewModel: UserViewModel) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val userDao = UserDao(context)
    val scope = rememberCoroutineScope()
//    var selectImages by remember {
//        mutableStateOf(listOf<Uri>())
//    }
    var changeNickname by remember {
        mutableStateOf(false)
    }
    var name by remember {
        mutableStateOf(userViewModel.nickname)
    }
    var changePassword by remember {
        mutableStateOf(false)
    }
    var password by remember {
        mutableStateOf("")
    }
    var rePassword by remember {
        mutableStateOf("")
    }
    val galleryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetMultipleContents()) { urls ->
            val file = File(FileUtil.getFileAbsolutePath(context, urls[0]) ?: "")
            val imageBody = file.asRequestBody("/multipart/form-data".toMediaTypeOrNull())
            val imageBodyPart = MultipartBody.Part.createFormData("file", "1", imageBody)
            //TODO TEST REMEMBER TO CHANGE
            Repository.uploadImage(userViewModel.token, userViewModel.emailTest, imageBodyPart)
                .observe(lifecycleOwner) {
                    val result = it.getOrNull()
                    if (result != null && it.isSuccess) {
                        scope.launch {
                            userDao.saveUserImage(result["url"]!!)
                        }
                        userViewModel.userProfile = result["url"]!!
                        Toast.makeText(context, "上传成功!", Toast.LENGTH_SHORT).show()
                    }
                }
//            selectImages = urls
        }
    Box(modifier = Modifier.systemBarsPadding()) {
        Scaffold(topBar = {
            Row(horizontalArrangement = Arrangement.Start) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    modifier = Modifier
                        .noRippleClickable {
                            navController.popBackStack()
                        }
                        .size(25.dp),
                    contentDescription = "返回")
            }
        }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                SubcomposeAsyncImage(
                    model = userViewModel.userProfile,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(
                            CircleShape
                        )
                        .size(80.dp),
                    contentDescription = null
                ) {
                    val state = painter.state
                    if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                        CircularProgressIndicator()
                    } else {
                        SubcomposeAsyncImageContent()
                    }
                }
                Button(
                    onClick = { galleryLauncher.launch("image/*") },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(yellow1)
                ) {
                    Text(text = "修改头像")
                }
                AnimatedContent(targetState =changeNickname ) {
                    if (changeNickname){
                        MyInputBox(value = name, onValueChange = {name=it}, tint ="新的昵称", width = 0.5f )
                    }
                }
                Button(onClick = {
                    if (changeNickname){
                        Repository.changeNickname(userViewModel.token, userViewModel.email, name)
                            .observe(lifecycleOwner) {
                                if (it.isSuccess) {
                                    userViewModel.nickname = name
                                } else {
                                    val toast = Toast.makeText(context, "修改失败", Toast.LENGTH_SHORT)
                                    toast.show()
                                }
                            }
                    }
                    changeNickname=!changeNickname
                }, shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(yellow1)) {
                    Text(text = if (changeNickname) "确认" else "修改昵称")
                }
                AnimatedContent(targetState = changePassword) {
                    if (changePassword){
                        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                            MyInputBox(value = password, onValueChange = {password=it}, tint ="输入新密码", width = 0.7f )
                            Spacer(modifier = Modifier.height(10.dp))
                            MyInputBox(value = rePassword, onValueChange = {rePassword=it}, tint = "重新输入新密码", width = 0.7f)
                        }
                    }
                }
                Button(onClick = {
                    if (changePassword){
                        Repository.changePassword(
                            userViewModel.token,
                            userViewModel.email,
                            password,
                            rePassword
                        ).observe(lifecycleOwner) {
                            if (it.isSuccess) {
                                val toast = Toast.makeText(context, "修改成功", Toast.LENGTH_SHORT)
                                toast.show()
                            } else {
                                val toast = Toast.makeText(context, "修改失败", Toast.LENGTH_SHORT)
                                toast.show()
                            }
                        }
                    }
                    changePassword=!changePassword
                }, shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(yellow1)) {
                    Text(text = if (changePassword) "确认" else "修改密码")
                }
            }
        }
    }
}