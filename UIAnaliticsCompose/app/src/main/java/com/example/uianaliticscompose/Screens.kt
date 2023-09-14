@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.uianaliticscompose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uianaliticscompose.ui.theme.LightGreen

@Composable
fun HomeScreen(navController: NavController, destinationList:List<Destination>){
    LazyColumn(modifier = Modifier
        .background(LightGreen)
        .testTag("item_list")
        .fillMaxSize()){
        items(destinationList){ destination ->
            Log.d("TAG", "HomeScreen: destination: $destination")
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .clickable {
                        navController.navigate("detail/${destination.id}")
                    }
            ) {
                Column(
                    Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                ) {
                    Text(
                        text = destination.name,
                        fontSize=22.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(
                        text = destination.shortDescription,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                Image(
                    painter = painterResource(id = destination.imageRes) ,
                    contentDescription = null,
                    Modifier
                        .padding(15.dp, 15.dp, 5.dp, 15.dp)
                        .width(100.dp)
                        .height(100.dp)
                )
                if(destination.isVisited)
                    Image(
                        painter = painterResource(id = R.drawable.baseline_airplane_24) ,
                        contentDescription = null,
                        Modifier
                            .width(20.dp)
                            .height(20.dp)
                            .align(Alignment.CenterVertically)
                    )
            }
        }
    }
}


@Composable
fun DetailScreen(navController: NavController){
    val destinationId = navController.currentBackStackEntry?.arguments?.getInt("selectedDestination")

    val destination = AppState.destinationList.firstOrNull{it.id == destinationId}
    var checkedIsVisited by remember {
        mutableStateOf(destination?.isVisited)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = painterResource(id = R.drawable.image1),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(5.dp)
        )

        Text(
            text = destination?.name ?: "",
            style = TextStyle(fontWeight = FontWeight.Bold),
            fontSize = 18.sp,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = destination?.country ?: "",
            fontSize = 16.sp,
        )
        Row (verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Have visited",
                modifier = Modifier.padding(5.dp)
            )
            Switch(checked = checkedIsVisited ?: false, onCheckedChange = {checked->
                checkedIsVisited = checked
                AppState.destinationList.firstOrNull()?.let { it.isVisited = checked }
            })
        }

        Text(
            text = destination?.shortDescription ?: "",
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = destination?.description ?: "",
            modifier = Modifier.padding(5.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDestinationScreen(navController: NavController){

    var name by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var shortDesc by remember { mutableStateOf("") }
    var longDesc by remember { mutableStateOf("") }
    var imageRes by remember { mutableStateOf(getRandomImage()) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(5.dp)
            )

        TextField(value = name, onValueChange ={name = it}, placeholder={ Text("Name")}, modifier = Modifier.padding(5.dp))
        TextField(value = country, onValueChange ={country = it},placeholder={ Text("Country")}, modifier = Modifier.padding(5.dp))
        TextField(value = shortDesc, onValueChange ={shortDesc = it},placeholder={ Text("Short Description")}, modifier = Modifier.padding(5.dp))
        TextField(value = longDesc, onValueChange ={longDesc = it},placeholder={ Text("Long Description")}, modifier = Modifier.padding(5.dp))

        Button(onClick = {
            AppState.destinationList.add(Destination(
                id = (Math.random()*100).toInt(),
                name = name,
                country = country,
                shortDescription = shortDesc,
                description = longDesc,
                isVisited = false,
                imageRes = imageRes
            ))
            navController.popBackStack()
        }
        ) {
            Text(text = "Add")
        }
    }

}

private fun getRandomImage(): Int {
    return listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5,
        R.drawable.image6,
        R.drawable.image7,
        R.drawable.image8,
    ).random()
}

