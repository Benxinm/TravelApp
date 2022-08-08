package com.benxinm.travelapp.data

import com.benxinm.travelapp.R

enum class Page(val text: String, val iconInt: Int) {
    MainPage(
        text = "首页",
        iconInt = R.drawable.ic_home_filled
    ),
    Community(
        text = "社区",
        iconInt = R.drawable.ic_message_filled
    ),
    Me(
        text = "我的",
        iconInt = R.drawable.ic_me_filled
    );
    companion object{
        fun fromRoute(route:String?):Page=
            when(route?.substringBefore("/")){
                MainPage.name->MainPage
                Community.name->Community
                Me.name->Me
                null->MainPage
                else->throw IllegalArgumentException("Route $route is not recognized")
            }
    }
}