package com.benxinm.travelapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benxinm.travelapp.R
import com.benxinm.travelapp.data.Flavor

@Composable
fun FlavorBottle(flavor: Flavor,degree:Float) {
    Box(modifier = Modifier.padding(horizontal = 8.dp)) {
       Image(painter = painterResource(id = when(flavor){
           Flavor.Bitter->when(degree){
               0f->R.drawable.ku0
               0.25f->R.drawable.ku25
               0.50f->R.drawable.ku50
               0.75f->R.drawable.ku75
               1.0f->R.drawable.ic_bitterbottle
               else->R.drawable.ku0
           }
           Flavor.Sour-> when(degree){
               0f->R.drawable.suan0
               0.25f->R.drawable.suan25
               0.50f->R.drawable.suan50
               0.75f->R.drawable.suan75
               1.0f->R.drawable.ic_sourbottle
               else->R.drawable.suan0
           }
           Flavor.Spicy-> when(degree){
               0f->R.drawable.la0
               0.25f->R.drawable.la25
               0.50f->R.drawable.la50
               0.75f->R.drawable.la75
               1.0f->R.drawable.ic_spicybottle
               else->R.drawable.la0
           }
           Flavor.Sweet-> when(degree){
               0f->R.drawable.tian0
               0.25f->R.drawable.tian25
               0.50f->R.drawable.tian50
               0.75f->R.drawable.tian75
               1.0f->R.drawable.ic_sweetbottle
               else->R.drawable.tian0
           }
           Flavor.Salty-> when(degree){
               0f->R.drawable.xian0
               0.25f->R.drawable.xian25
               0.50f->R.drawable.xian50
               0.75f->R.drawable.xian75
               1.0f->R.drawable.ic_saltybottle
               else->R.drawable.xian0
           }
       }), contentDescription = "口味瓶")
        Image(painter = painterResource(id = when(flavor){
            Flavor.Bitter->R.drawable.ic_bitter
            Flavor.Sour-> R.drawable.ic_sour
            Flavor.Spicy-> R.drawable.ic_spicy
            Flavor.Sweet-> R.drawable.ic_sweet
            Flavor.Salty-> R.drawable.ic_salty
        } ), modifier = Modifier
            .size(35.dp)
            .align(Alignment.BottomEnd) ,contentDescription = "标志")
    }
}

@Preview
@Composable
fun PreviewBox() {
    Row {
        FlavorBottle(flavor = Flavor.Sour, degree = 0f)
        FlavorBottle(flavor = Flavor.Sweet, degree = 0.25f)
        FlavorBottle(flavor = Flavor.Salty, degree = 0.5f)
        FlavorBottle(flavor = Flavor.Bitter, degree = 0.75f)
        FlavorBottle(flavor = Flavor.Spicy, degree = 1f)
    }
}
