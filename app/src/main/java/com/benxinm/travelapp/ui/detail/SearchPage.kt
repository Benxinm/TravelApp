import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.benxinm.travelapp.R
import com.benxinm.travelapp.ui.components.HomeSearchBar
import com.benxinm.travelapp.util.noRippleClickable

@Composable
fun SearchPage(/*navController: NavController*/) {
    val list = listOf("1232135","151858","89654","48987351")
    Scaffold(topBar = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription ="返回", modifier = Modifier
                .size(20.dp)
                .noRippleClickable { /*navController.popBackStack()*/ } )
            Text(text = "与美食撞个满杯", fontSize = 20.sp)

        }
    }, modifier = Modifier.systemBarsPadding()) {it
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)) {
            Column {
                HomeSearchBar()
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "历史记录")
                    Icon(painter = painterResource(id = R.drawable.ic_delete_1), modifier = Modifier.size(30.dp), contentDescription ="删除历史记录")
                }
                LazyVerticalGrid(columns =GridCells.Fixed(3)){
                    items(list){
                        Text(text = it)
                    }
                }
            }
        }

    }
}