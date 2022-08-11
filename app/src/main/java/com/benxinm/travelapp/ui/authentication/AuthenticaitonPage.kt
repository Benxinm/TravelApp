package com.benxinm.travelapp.ui.authentication

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.benxinm.travelapp.logic.dao.UserDao
import com.benxinm.travelapp.ui.theme.blue8
import com.benxinm.travelapp.ui.theme.white7blue
import com.benxinm.travelapp.util.noRippleClickable
import com.benxinm.travelapp.viewModel.LoginViewModel
import com.benxinm.travelapp.viewModel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AuthenticationPage() {
    val loginViewModel: LoginViewModel = viewModel()
    val userViewModel:UserViewModel = viewModel()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val userDao = UserDao(context)
    val scope = rememberCoroutineScope()
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var nickname by remember {
        mutableStateOf("")
    }
    var rePassword by remember {
        mutableStateOf("")
    }
    var isRegister by remember {
        mutableStateOf(false)
    }
    var code by remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier.systemBarsPadding()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MyInputBox(value = email, onValueChange = { email = it }, tint = "邮箱")
            if (isRegister)Spacer(modifier = Modifier.height(20.dp))
            AnimatedContent(targetState = isRegister) {isRegister->
                if (isRegister) MyInputBox(value = nickname, onValueChange = { nickname = it }, tint = "昵称")
            }
            Spacer(modifier = Modifier.height(20.dp))
            MyInputBox(value = password, onValueChange = { password = it }, tint = "密码")
            if (isRegister) Spacer(modifier = Modifier.height(20.dp))
            AnimatedContent(targetState = isRegister) {isRegister->
                if (isRegister)MyInputBox(value = rePassword, onValueChange = { rePassword = it }, tint = "再次输入密码")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    if (isRegister) {
                        loginViewModel.register(email, nickname, password, rePassword)
                    } else {
                        loginViewModel.login(email, password)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = blue8.convert(
                        ColorSpaces.Srgb
                    ), contentColor = white7blue.convert(ColorSpaces.Srgb)
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text(text = if (isRegister) "注册" else "登录", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = if (isRegister) "返回登录" else "新用户注册", modifier = Modifier.noRippleClickable {
                isRegister=!isRegister
            })
        }
        loginViewModel.userLiveData.observe(lifecycleOwner) { result ->
            val user = result.getOrNull()
            if (user != null) {
                userViewModel.email = user.email
                userViewModel.nickname = user.nickname
                userViewModel.targetEmail=user.email
                scope.launch {
                    userDao.saveUserEmail(user.email)
                    userDao.saveUserPassword(password)
                }
            }
        }
        loginViewModel.registerUserLiveData.observe(lifecycleOwner) { result ->
            val isSuccess = result.isSuccess
            val verified=loginViewModel.verifyEmail(code = code)
            if (isSuccess && verified!=null && verified) {
                //TODO Scaffold 提示框
            } else {

            }
        }
    }
}

@Composable
fun MyInputBox(value: String, onValueChange: (String) -> Unit, tint: String,modifier: Modifier=Modifier,width:Float=0.8f,height:Dp=55.dp) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        cursorBrush = SolidColor(Color.Black),
        decorationBox = { innerTextField ->
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
                    if (value.isEmpty()) {
                        Text(text = tint, color = Color.DarkGray)
                    }
                    innerTextField()
                }
            }
        },
        modifier = modifier
            .height(height)
            .fillMaxWidth(width)
    )
}