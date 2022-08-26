package com.benxinm.travelapp.data

import com.benxinm.travelapp.R

enum class Page(val text: String, val iconInt: Int, val selectedIconInt: Int) {
    MainPage(
        text = "首页",
        iconInt = R.drawable.ic_mainpage01,
        selectedIconInt = R.drawable.ic_mainpage02
    ),
    Community(
        text = "社区",
        iconInt = R.drawable.ic_social01,
        selectedIconInt = R.drawable.ic_social02
    ),
    Me(
        text = "我的",
        iconInt = R.drawable.ic_mine01,
        selectedIconInt = R.drawable.ic_mine02
    ),
    Detail(
        text = "详情",
        iconInt = 0,
        selectedIconInt = 0
    ),
    Personal(text = "编辑", iconInt = 0, selectedIconInt = 0),
    FlavourBottle(
        text = "风味瓶",
        iconInt = 0,
        selectedIconInt = 0
    ),
    Guide(
        text = "美食攻略",
        iconInt = 0,
        selectedIconInt = 0
    ),
    Store(
        text = "美味店铺",
        iconInt = 0,
        selectedIconInt = 0
    ),
    Map(
        text = "地图", iconInt = 0,
        selectedIconInt = 0
    ),
    GuideDetail(text = "攻略", iconInt = 0, selectedIconInt = 0), AddPost(
        text = "添加帖子",
        iconInt = 0,
        selectedIconInt = 0
    ),
    Login(text = "登录", iconInt = 0, selectedIconInt = 0),
    StoreDetail(text = "店铺详情", iconInt = 0, selectedIconInt = 0),
    FoodDetail(text = "美食详情", iconInt = 0, selectedIconInt = 0),
    Food(text = "美食", iconInt = 0, selectedIconInt = 0);

    companion object {
        fun fromRoute(route: String?): Page =
            when (route?.substringBefore("/")) {
                MainPage.name -> MainPage
                Community.name -> Community
                Me.name -> Me
                Detail.name -> Detail
                Personal.name -> Personal
                FlavourBottle.name -> FlavourBottle
                Guide.name -> Guide
                Store.name -> Store
                Map.name -> Map
                GuideDetail.name -> GuideDetail
                AddPost.name -> AddPost
                Login.name -> Login
                StoreDetail.name->StoreDetail
                FoodDetail.name->FoodDetail
                Food.name->Food
                null -> MainPage
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
    }
}