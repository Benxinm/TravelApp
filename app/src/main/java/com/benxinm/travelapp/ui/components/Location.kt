package com.benxinm.travelapp.ui.components

import android.location.Location
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.benxinm.travelapp.R
import com.benxinm.travelapp.data.Page
import com.benxinm.travelapp.util.noRippleClickable
import com.benxinm.travelapp.viewModel.UserViewModel

@Composable
fun Location(navController: NavController,userViewModel: UserViewModel) {
    Box(modifier = Modifier.wrapContentSize().noRippleClickable { navController.navigate(Page.Map.name) }) {
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(top = 5.dp)) {
            Image(painter = painterResource(id = R.drawable.ic_locate), contentDescription ="位置", modifier = Modifier.size(25.dp))
            Spacer(modifier = Modifier.width(3.dp))
            Text(text = userViewModel.location, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}