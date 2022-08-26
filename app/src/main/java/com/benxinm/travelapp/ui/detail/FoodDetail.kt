package com.benxinm.travelapp.ui.main

import androidx.compose.animation.*

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.benxinm.travelapp.R
import com.benxinm.travelapp.data.Collect
import com.benxinm.travelapp.data.Page
import com.benxinm.travelapp.logic.Repository
import com.benxinm.travelapp.ui.components.*
import com.benxinm.travelapp.util.noRippleClickable
import com.benxinm.travelapp.viewModel.UserViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FoodDetail(navController: NavController,userViewModel: UserViewModel) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var tabTopic by remember { mutableStateOf(Topic.First) }
    val pagerState = rememberPagerState()
    val scope= rememberCoroutineScope()
    Box(modifier = Modifier.systemBarsPadding()) {
        Column {
            Row(horizontalArrangement = Arrangement.Start) {
                Icon(painter = painterResource(id = R.drawable.ic_back), modifier = Modifier.size(30.dp).noRippleClickable { navController.popBackStack() }, contentDescription = "")
            }
            Box(modifier = Modifier.padding(horizontal = 8.dp)) {
                Column {
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        SubcomposeAsyncImage(
                            model = R.drawable.m_4,
                            contentDescription = "",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.Crop
                        ) {
                            val painterState = painter.state
                            if (painterState is AsyncImagePainter.State.Loading || painterState is AsyncImagePainter.State.Error) {
                                CircularProgressIndicator()
                            } else {
                                SubcomposeAsyncImageContent()
                            }
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "鱼丸做法", fontSize = 35.sp)
                        var likes by remember {
                            mutableStateOf(0)
                        }
                        var state by remember {
                            mutableStateOf(ScaleButtonState.IDLE)
                        }
                        Spacer(modifier = Modifier.width(20.dp))

                        AnimatedScaleButton(
                            state = state,
                            onToggle = {
                                state =
                                    if (state == ScaleButtonState.IDLE) ScaleButtonState.ACTIVE else ScaleButtonState.IDLE
                            },
                            onClick = { scaleButtonState ->
                                if (scaleButtonState == ScaleButtonState.IDLE) {
                                    Repository.addLike("123", "123").observe(lifecycleOwner) {
                                        if (it.isSuccess) {
                                            likes++
                                        }
                                    }
                                } else {
                                    Repository.cancelLike("456", "456").observe(lifecycleOwner) {
                                        if (it.isSuccess) {
                                            likes--
                                        }
                                    }
                                }
                            },
                            size = 30.dp,
                            activeColor = Color.Red,
                            idleColor = Color.LightGray,
                            idleSource = R.drawable.ic_heart_outlined,
                            activeSource = R.drawable.ic_heart_filleded
                        )
                        var collectState by remember {
                            mutableStateOf(if (userViewModel.collectList.contains(
                                    Collect(R.drawable.m_4,"福州鱼丸",
                                        Page.FoodDetail.name)
                                )) ScaleButtonState.ACTIVE else ScaleButtonState.IDLE)
                        }
                        AnimatedScaleButton(
                            state = collectState,
                            onToggle = {
                                collectState =
                                    if (collectState == ScaleButtonState.IDLE) {
                                        userViewModel.collectList.add(
                                            Collect(R.drawable.m_4,"福州鱼丸",
                                                Page.FoodDetail.name)
                                        )
                                        ScaleButtonState.ACTIVE
                                    }else{
                                        userViewModel.collectList.remove(
                                            Collect(R.drawable.m_4,"福州鱼丸",
                                                Page.FoodDetail.name)
                                        )
                                        ScaleButtonState.IDLE
                                    }
                            },
                            size = 30.dp,
                            activeColor = Color.Yellow,
                            idleColor = Color.LightGray,
                            idleSource = R.drawable.ic_collect01,
                            activeSource = R.drawable.ic_collected02
                        )
                    }
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)) {
                        AnimatedUnderLinerSelector02(
                            backgroundColor = Color.Transparent,
                            tabTopic = tabTopic,
                            onTabSelected = {currentPage->
                                scope.launch {
                                    pagerState.animateScrollToPage( if (currentPage==Topic.First) 0 else 1)
                                }
                                tabTopic=currentPage
                            })
                    }
                    HorizontalPager(count = 2,state=pagerState) {page ->
                        if (page==0){
                            var expanded by remember { mutableStateOf(false) }
                            Box(modifier = Modifier.fillMaxSize()) {
                                Column {
                                    Box(modifier = Modifier.animateContentSize()) {
                                        Surface(elevation = 8.dp) {
                                            Column (modifier = Modifier.verticalScroll(rememberScrollState())){
                                                Text(text ="      福州鱼丸是用鳗鱼肉、鲨鱼肉或用马鲛鱼肉剁蓉,加番薯粉(淀粉)搅拌均匀,再包以猪瘦肉或虾等馅制成的丸状食物。食之滑润清脆,汤汁荤香不腻。\n" +
                                                        "      \t古时候，闽江之畔有个渔民。一天，有位商人搭他" +
                                                        "的船南行经商，船出闽江口，进了大海，正遇台风袭击。" +
                                                        "船入港湾避风时，不幸触礁损坏。修船耽延了时间，粮" +
                                                        "断了，天天以鱼当饭。商人叹道:\"天天有鱼，食之生厌。" +
                                                        "能不能换换别的口味?\"船妇说:\"船上粮已断，唯有薯粉" +
                                                        "一包。\"心灵手巧的船妇便把刚钓到的一条大鳗鱼，去皮" +
                                                        "除刺，把鱼肉剁细，抹上薯粉，制成丸子，煮熟一尝别" +
                                                        "有风味。 事后，这位商人回到福州，便在城里开设一家 "+
                                                        "\"七星小食店\"，特聘这位船妇为厨师，独家经营\"鱼丸汤\"。" +
                                                        "开头，生意并不兴隆。一天，一位上京应考的举子路过" +
                                                        "此店就餐。店主热情款待，捧出鱼丸。举子食后，颇觉" +
                                                        "味道极美，便题赠一诗:\n" +
                                                        "       点点星斗布空稀，玉露甘香游客迷。\n" +
                                                        "       南疆虽有千秋饮，难得七星沁诗脾。\n" +
                                                        "      店主将诗挂在店堂上，宾客齐来品尝。从此生意兴" +
                                                        "隆，小店日日春风。\"七星鱼丸\"也从此得名。" , fontSize = 20.sp, maxLines = if (expanded) Int.MAX_VALUE  else 2)
                                                Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.End) {
                                                    Box(modifier = Modifier.noRippleClickable {
                                                        expanded=!expanded
                                                    }) {
                                                        Row {
                                                            Text(text = if (expanded)"收起" else "展开")
                                                            Icon(painter = painterResource(id = if (expanded) R.drawable.ic_up else R.drawable.ic_down),  modifier = Modifier.size(20.dp), contentDescription ="" )
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }else{
                            Box(modifier = Modifier
                                .fillMaxSize()) {
                                LazyColumn(modifier = Modifier.fillMaxSize()){
                                    item { 
                                        Surface(elevation = 3.dp,modifier = Modifier.fillMaxWidth()) {
                                            Column(modifier = Modifier.padding(10.dp)) {
                                                Text(text = "食材:", fontSize = 25.sp)
                                                Text(text = "草鱼 五花肉 蛋 淀粉 葱油")
                                            }
                                        }
                                    }
                                    item {
                                        Column {
                                            Spacer(modifier = Modifier.height(20.dp))
                                            Surface(elevation = 3.dp,modifier = Modifier.fillMaxWidth()) {
                                                Column(modifier = Modifier.padding(10.dp)) {
                                                    Text(text = "步骤:", fontSize = 25.sp)
                                                    val list= listOf("步骤一、首先准备一条鲜活的草鱼（鱼一定要用活的，这样做出来才有劲道），清洗干净之后，用刀去除鱼皮、鱼头、鱼尾、鱼腩留鱼肉即可，然后把鱼肉放入水中浸泡一个小时左右（浸泡的目地是为了去除血水，这样可以让鱼丸更白）","步骤二、准备一个小型搅碎机，把鱼肉切成小块状放进去，再加入清水（水要漫过鱼肉一大截才行），搅拌成稀泥状倒出来（这个过程打的越细腻越好），接着准备一个容器，加入生姜葱和少许清水浸泡两个小时（生姜葱要用刀拍一下，这样葱姜水味会浓重）","步骤三、然后鱼肉中加入盐、葱姜水、葱花，顺着一个方向不停的搅拌上劲（这一步一定要大开大合的搅拌，而且中途不能停），鱼肉拿起来伸开手掌慢慢脱落就代表上劲了，并且十分的有黏性","步骤四、这个时候可以做鱼丸了，准备一个铁锅，锅里加入冷水不要烧，然后一手拿着一个瓷勺，一手从虎口处挤出一个鱼丸往锅里下，全部放进去之后，用小火慢慢的煮（注意是小火，大火容易煮碎），煮到鱼丸微微翻滚就可以关火了，这时候利用水的温度继续焖十分钟即可出锅啦","步骤五、鱼丸熟之后迅速放到冷水里冲凉，这样做是保证鱼丸迅速冷下去，口感好，并且能达到热胀冷缩的效果，冲凉后的鱼丸放到盐水里泡着，可以长时间保存，拿出来再次煮熟就可以食用啦")
                                                    list.forEach {
                                                        Text(text = it)
                                                        Spacer(modifier = Modifier.height(12.dp))
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
            }
        }
    }
}

