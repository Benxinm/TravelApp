package com.benxinm.travelapp.ui.post

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.benxinm.travelapp.R
import com.benxinm.travelapp.data.askModel.AddCommentModel
import com.benxinm.travelapp.ui.theme.blue1
import com.benxinm.travelapp.util.noRippleClickable

@Composable
fun PersonalPost(navController: NavController) {
    val context = LocalContext.current
    var selectImages by remember {
        mutableStateOf(listOf<Uri>())
    }
    val galleryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetMultipleContents()) {
            val tempList=ArrayList(selectImages)
            tempList.addAll(it)
            selectImages=tempList
        }
    Box(modifier = Modifier.systemBarsPadding()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "返回",
                    modifier = Modifier.size(30.dp).noRippleClickable {
                        navController.popBackStack()
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(selectImages) { uri ->
                    PostImageLabel(uri)
                }
                item {
                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_add),
                                modifier = Modifier
                                    .fillMaxSize(0.3f)
                                    .noRippleClickable { galleryLauncher.launch("image/*") },
                                contentDescription = "添加", tint = Color.LightGray
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            var title by remember {
                mutableStateOf("")
            }
            Box(
                Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                BasicTextField(
                    value = title,
                    onValueChange = {
                        title = it
                    },
                    textStyle = TextStyle(color = Color.Black, fontSize = 23.sp),
                    cursorBrush = SolidColor(Color.Black),
                    decorationBox = { innerTextField ->
                        Row(
                            Modifier
                                .padding(2.dp)
                                .fillMaxWidth()
                                .padding(1.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(5.dp))
                            Box(
                                modifier = Modifier
                                    .padding(
                                        top = 7.dp,
                                    )
                                    .height(30.dp)
                            ) {
                                if (title.isEmpty()){
                                    Text("标题", fontSize = 23.sp, color = Color.LightGray)
                                }else{
                                    innerTextField()
                                }
                                Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
                                    Text(text = "${20 - title.length}", color = Color.LightGray)
                                }
                            }
                        }
                    }
                )
            }
            Divider()
            var text by remember {
                mutableStateOf("")
            }
            Box(
                Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                BasicTextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    textStyle = TextStyle(color = Color.Black, fontSize = 21.sp),
                    cursorBrush = SolidColor(Color.Black),
                    decorationBox = { innerTextField ->
                        Row(
                            Modifier
                                .padding(2.dp)
                                .fillMaxWidth()
                                .padding(1.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(5.dp))
                            Box(
                                modifier = Modifier
                                    .padding(
                                        top = 7.dp,
                                    )
                                    .height(30.dp)
                            ) {
                                if (text.isEmpty()){
                                    Text("添加正文", fontSize = 21.sp, color = Color.LightGray)
                                }else{
                                    innerTextField()
                                }
                            }
                        }
                    }
                )
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Bottom, modifier = Modifier.fillMaxSize().padding(bottom = 10.dp)) {
            Box(modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .background(Color.Red)
                .fillMaxWidth(0.8f)
                .clickable {

                }) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "发送帖子",
                        color = Color.White,
                        fontSize = 28.sp,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PostImageLabel(uri: Uri) {
    Box(
        modifier = Modifier
            .padding(start = 5.dp)
            .size(90.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(
                1.dp, Color.LightGray,
                RoundedCornerShape(10.dp)
            )

    ) {
        SubcomposeAsyncImage(model = uri, contentDescription = "", contentScale = ContentScale.Crop) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                CircularProgressIndicator()
            } else {
                SubcomposeAsyncImageContent()
            }
        }
    }
}

@Composable
fun AddImageLabel() {

}