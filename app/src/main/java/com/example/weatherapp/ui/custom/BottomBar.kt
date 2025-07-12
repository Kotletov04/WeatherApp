package com.example.currencyconverter.ui.component.custom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.Black1B
import com.example.weatherapp.ui.theme.Blue46
import com.example.weatherapp.ui.theme.Gray48


@Composable
fun BottomBar(color: Color = Black1B, itemsSize: Dp = 50.dp, navController: NavController) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        Row(
            modifier = Modifier
                .background(color = color)
                .fillMaxWidth()
                .height(125.dp)
                .padding(top = 5.dp),
            horizontalArrangement = Arrangement.SpaceAround) {
            BottomBarIconItem(
                itemsSize = itemsSize, itemIcon = R.drawable.outline_nest_farsight_weather_24,
                navController = navController
            )
            BottomBarIconItemCity(
                itemsSize = itemsSize,
                itemIcon = R.drawable.baseline_location_city_24,
                navController = navController,
            )
        }
        /*Box(modifier = Modifier.height(1.dp).fillMaxWidth().background(brush = Brush.linearGradient(
            colors = listOf(PinkDD, Yellow26),
            start = Offset(-120f, 0f),
            end = Offset(1000f, 0f)
        )))*/
    }

}

@Composable
private fun BottomBarIconItem(itemsSize: Dp, itemIcon: Int, navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Box(modifier = Modifier
        .padding(5.dp)
        .clip(RoundedCornerShape(50.dp))
        .background(if (currentRoute == "WeatherScreen") Blue46 else Gray48 )
        .size(itemsSize)
        .padding(10.dp)
        .clickable { navController.navigate("WeatherScreen") },
        contentAlignment = Alignment.Center) {
        Image(painter = painterResource(itemIcon), contentDescription = null)
    }
}


@Composable
private fun BottomBarIconItemCity(itemsSize: Dp, itemIcon: Int, navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Box(modifier = Modifier
        .padding(5.dp)
        .clip(RoundedCornerShape(50.dp))
        .background(if (currentRoute == "CityHistoryScreen") Blue46 else Gray48 )
        .size(itemsSize)
        .padding(10.dp)
        .clickable { navController.navigate("CityHistoryScreen") },
        contentAlignment = Alignment.Center) {
        Image(painter = painterResource(itemIcon), contentDescription = null)
    }
}