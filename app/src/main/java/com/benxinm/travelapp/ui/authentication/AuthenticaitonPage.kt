package com.benxinm.travelapp.ui.authentication

import android.util.Log
import android.widget.Toast
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
import androidx.navigation.NavController
import com.benxinm.travelapp.data.Page
import com.benxinm.travelapp.logic.Repository
import com.benxinm.travelapp.logic.dao.UserDao
import com.benxinm.travelapp.ui.theme.blue8
import com.benxinm.travelapp.ui.theme.white7blue
import com.benxinm.travelapp.util.noRippleClickable
import com.benxinm.travelapp.viewModel.LoginViewModel
import com.benxinm.travelapp.viewModel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AuthenticationPage(
    navController: NavController,
    loginViewModel: LoginViewModel,
    userViewModel: UserViewModel
) {
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
            MyInputBox(value = email, onValueChange = { email = it }, tint = "??????")
            if (isRegister) Spacer(modifier = Modifier.height(20.dp))
            AnimatedContent(targetState = isRegister) { isRegister ->
                if (isRegister) MyInputBox(
                    value = nickname,
                    onValueChange = { nickname = it },
                    tint = "??????"
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            MyInputBox(value = password, onValueChange = { password = it }, tint = "??????")
            if (isRegister) Spacer(modifier = Modifier.height(20.dp))
            AnimatedContent(targetState = isRegister) { isRegister ->
                if (isRegister) MyInputBox(
                    value = rePassword,
                    onValueChange = { rePassword = it },
                    tint = "??????????????????"
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            AnimatedContent(targetState = isRegister) { isRegister ->
                if (isRegister) {
                    Row(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        MyInputBox(
                            value = code,
                            onValueChange = { code = it },
                            tint = "?????????",
                            width = 0.5f
                        )
                        Button(
                            onClick = {
                                if (email.isNotEmpty()) {
                                    Repository.sendEmail(email).observe(lifecycleOwner) { result ->
                                        if (result.isSuccess) {
                                            val toast =
                                                Toast.makeText(context, "????????????!", Toast.LENGTH_SHORT)
                                            toast.show()
                                        } else {
                                            val toast =
                                                Toast.makeText(context, "????????????!", Toast.LENGTH_SHORT)
                                            toast.show()
                                        }
                                    }
                                }
                            }, shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(
                                backgroundColor = blue8.convert(
                                    ColorSpaces.Srgb
                                ), contentColor = white7blue.convert(ColorSpaces.Srgb)
                            ), modifier = Modifier
                                .height(55.dp)
                                .fillMaxWidth(0.85f)
                        ) {
                            Text(text = "???????????????")
                        }
                    }
                }
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
                Text(text = if (isRegister) "??????" else "??????", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = if (isRegister) "????????????" else "???????????????", modifier = Modifier.noRippleClickable {
                isRegister = !isRegister
            })
        }
        loginViewModel.userLiveData.observe(lifecycleOwner) { result ->
            val user = result.getOrNull()
            if (result.isSuccess) {
                val toast = Toast.makeText(context, "????????????!", Toast.LENGTH_SHORT)
                if (user != null) {
                    userViewModel.token = user["token"]!!
                    userViewModel.email = user["user_name"]!!
                    Log.d("Login", user["user_name"]!!)
                    userViewModel.nickname = user["user_nick"]!!
                    userViewModel.targetEmail = user["user_name"]!!
                    userViewModel.userProfile = user["head"]!!
                    scope.launch {
                        userDao.saveUserEmail(userViewModel.email)
                        userDao.saveUserPassword(password)
                    }
                }
                navController.navigate(Page.MainPage.name)
                toast.show()

            } else {
                val toast = Toast.makeText(context, "????????????!", Toast.LENGTH_SHORT)
                toast.show()
            }

        }
        loginViewModel.registerUserLiveData.observe(lifecycleOwner) { result ->

            Repository.verifyCode(code).observe(lifecycleOwner) { verified ->

                val isSuccess = result.isSuccess
                if (isSuccess /*&& verified != null && verified.isSuccess*/) {
                    val toast = Toast.makeText(context, "????????????!???????????????~", Toast.LENGTH_SHORT)
                    toast.show()
                    isRegister = false
                } else {
                    val toast = Toast.makeText(context, "????????????~?????????~", Toast.LENGTH_SHORT)
                    toast.show()
                }
            }

        }
    }
}

@Composable
fun MyInputBox(
    value: String,
    onValueChange: (String) -> Unit,
    tint: String,
    modifier: Modifier = Modifier,
    width: Float = 0.8f,
    height: Dp = 55.dp
) {
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
                    if (value.isEmpty() && tint.isNotEmpty()) {
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