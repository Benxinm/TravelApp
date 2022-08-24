package com.benxinm.travelapp.ui.me

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
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

@Composable
fun EditPage(navController: NavController) {

    val context = LocalContext.current
    val lifecycleOwner= LocalLifecycleOwner.current
    val userDao = UserDao(context)
    val scope= rememberCoroutineScope()
    var imageUrl =  userDao.getImage.collectAsState(initial = "").value
    var selectImages by remember {
        mutableStateOf(listOf<Uri>())
    }

    val userViewModel:UserViewModel= viewModel()
    val galleryLauncher= rememberLauncherForActivityResult(contract = ActivityResultContracts.GetMultipleContents()){ urls ->
        val file = File(FileUtil.getFileAbsolutePath(context,urls[0])?:"")
//        Util.uri2Bitmap(urls[0])
//        Log.d("ImageUpload", )
        val imageBody = file.asRequestBody("/multipart/form-data".toMediaTypeOrNull())
        val imageBodyPart= MultipartBody.Part.create(imageBody)
        //TODO TEST REMEMBER TO CHANGE
        Repository.uploadImage(userViewModel.tokenTest,userViewModel.emailTest,imageBodyPart).observe(lifecycleOwner){
            val result=it.getOrNull()
            if (result!=null && it.isSuccess){
                scope.launch {
                    userDao.saveUserImage(result["url"]!!)
                }
                userViewModel.profile=result["url"]!!
                Toast.makeText(context,"上传成功!",Toast.LENGTH_SHORT).show()
            }
        }
        selectImages=urls
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
                    model = if (selectImages.isEmpty()) if (imageUrl == "") defaultUri else imageUrl else selectImages[0],
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
                Button(onClick = { galleryLauncher.launch("image/*")}, shape = RoundedCornerShape(50)) {
                    Text(text = "修改头像")
                }
            }
        }
    }
}