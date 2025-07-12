package com.example.weatherapp.ui.components.weather

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.ui.theme.Black22
import com.example.weatherapp.ui.theme.Blue46
import com.example.weatherapp.ui.theme.Monteserrat
import coil3.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.domain.models.forecast.ForecastDayModel
import com.example.weatherapp.ui.theme.Gray33
import com.example.weatherapp.ui.util.weatherIconByCode
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherComponent(weatherViewModel: WeatherViewModel, city: String, navController: NavController) {
    val state = weatherViewModel.state.value
    val forecastState = weatherViewModel.forecastState.value
    val isRefreshing = remember { mutableStateOf(false) }
    PullToRefreshBox(
        isRefreshing = isRefreshing.value,
        onRefresh = {
            weatherViewModel.refresh(city = city)
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .background(Black22)
                .fillMaxSize()
        ) {
            item {
                TempComponent(weatherViewModel = weatherViewModel, city = city, state = state, navController = navController)
                Spacer(modifier = Modifier.height(50.dp))
                ForecastLazyRow(forecastWeatherState = forecastState)
                Spacer(modifier = Modifier.height(50.dp))
            }


        }
    }


}

@Composable
private fun TempComponent(weatherViewModel: WeatherViewModel, city: String, state: WeatherState, navController: NavController) {
    if (state.isLoading != true) {
        val backgroundColor =
            weatherIconByCode(state.currentWeather!!.currentWeather.conditionDto.code)?.color
                ?: Blue46
        Column(
            modifier = Modifier
                .background(backgroundColor)
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                modifier = Modifier.fillMaxWidth().height(20.dp).clickable { navController.navigate("CityScreen") }
            ) {
                Image(painter = painterResource(R.drawable.baseline_arrow_back_24), contentDescription = null)
            }
            val tempC = state.currentWeather!!.currentWeather.tempC
            Text(
                modifier = Modifier.padding(top = 3.dp, bottom = 3.dp, start = 5.dp, end = 5.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontFamily = Monteserrat,
                color = Color.White,
                fontSize = 18.sp,
                text = city
            )
            Spacer(modifier = Modifier.height(25.dp))
            Row {
                Image(
                    painter = painterResource(
                        weatherIconByCode(state.currentWeather!!.currentWeather.conditionDto.code)?.drawable
                            ?: R.drawable._276
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(110.dp)
                )
                Column {
                    Text(
                        modifier = Modifier.padding(
                            top = 3.dp,
                            bottom = 3.dp,
                            start = 5.dp,
                            end = 5.dp
                        ),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Monteserrat,
                        color = Color.White,
                        fontSize = 16.sp,
                        text = state.currentWeather.currentWeather.conditionDto.text
                    )
                    Row(verticalAlignment = Alignment.Bottom) {

                        Text(
                            modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            fontFamily = Monteserrat,
                            color = Color.White,
                            fontSize = 48.sp,
                            text = describeTemperature(tempC) + tempC
                        )
                        Text(
                            modifier = Modifier.padding(bottom = 5.dp, start = 5.dp, end = 5.dp),
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            fontFamily = Monteserrat,
                            color = Color.White,
                            fontSize = 28.sp,
                            text = "C°"
                        )
                    }
                    Text(
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Monteserrat,
                        color = Color.White,
                        fontSize = 12.sp,
                        text = "ощущается как ${describeTemperature(tempC) + tempC}"
                    )


                }

            }
            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WindCard(state = state)
                Column() {
                    PressureCard(state = state)
                    Row(modifier = Modifier.padding(top = 10.dp)) {
                        HumidityAndCloudCard(state = state)
                    }
                }

            }

        }

    }
}


@Composable
private fun HumidityAndCloudCard(state: WeatherState) {
    Row(
        modifier = Modifier.width(180.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (state.isLoading == true) {
            CircularProgressIndicator()
        } else {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Black22.copy(alpha = 0.70f))
                    .height(50.dp)
                    .width(85.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(R.drawable.humidity), contentDescription = null)
                    Text(
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Monteserrat,
                        color = Color.White,
                        fontSize = 8.sp,
                        text = "Влажность"
                    )
                }
                Text(
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.ExtraLight,
                    fontFamily = Monteserrat,
                    color = Color.White,
                    fontSize = 14.sp,
                    text = "${state.currentWeather!!.currentWeather.humidity}%"
                )
            }
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Black22.copy(alpha = 0.70f))
                    .height(50.dp)
                    .width(85.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(R.drawable.cloud), contentDescription = null)
                    Text(
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Monteserrat,
                        color = Color.White,
                        fontSize = 8.sp,
                        text = "Облачность"
                    )
                }
                Text(
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.ExtraLight,
                    fontFamily = Monteserrat,
                    color = Color.White,
                    fontSize = 14.sp,
                    text = "${state.currentWeather!!.currentWeather.cloud}%"
                )
            }

        }


    }
}


@SuppressLint("DefaultLocale")
@Composable
private fun PressureCard(state: WeatherState) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Black22.copy(alpha = 0.70f))
            .height(70.dp)
            .width(180.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.isLoading == true) {
            CircularProgressIndicator()
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(R.drawable.pressure), contentDescription = null)
                Text(
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Monteserrat,
                    color = Color.White,
                    fontSize = 10.sp,
                    text = "Атмосферное давление"
                )
            }
            Text(
                modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.ExtraLight,
                fontFamily = Monteserrat,
                color = Color.White,
                fontSize = 14.sp,
                text = "${String.format("%.2f", mbarToMmHg(state.currentWeather!!.currentWeather.pressureMb))} мм рт. ст."
            )
        }


    }
}



@SuppressLint("DefaultLocale")
@Composable
private fun WindCard(state: WeatherState) {

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Black22.copy(alpha = 0.70f))
            .height(130.dp)
            .width(160.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.isLoading == true) {
            CircularProgressIndicator()
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(R.drawable.wind_icon), contentDescription = null)
                Text(
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Monteserrat,
                    color = Color.White,
                    fontSize = 10.sp,
                    text = "Скорость ветра"
                )
            }
            Text(
                modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.ExtraLight,
                fontFamily = Monteserrat,
                color = Color.White,
                fontSize = 14.sp,
                text = "${String.format("%.2f", kmhToMs(state.currentWeather!!.currentWeather.windKph))} М/C"
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(R.drawable.wind_fluger), contentDescription = null)
                Text(
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Monteserrat,
                    color = Color.White,
                    fontSize = 10.sp,
                    text = "Направление"
                )
            }
            Text(
                modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.ExtraLight,
                fontFamily = Monteserrat,
                color = Color.White,
                fontSize = 14.sp,
                text = windDirectionFromDegrees(state.currentWeather.currentWeather.windDegree)
            )
        }


    }
}

@Composable
private fun ForecastLazyRow(forecastWeatherState: ForecastWeatherState) {
    if (forecastWeatherState.isLoading == true) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }

    } else {
        val forecastWeatherList = forecastWeatherState.forecastWeather!!.forecastDay

        LazyRow {
            items(forecastWeatherList) { item ->
                Spacer(modifier = Modifier.width(10.dp))
                ForecastRowItem(forecastDay = item)

            }
            item {Spacer(modifier = Modifier.width(10.dp))}
        }
    }

}



@Composable
private fun ForecastRowItem(forecastDay: ForecastDayModel) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .width(160.dp)
            .height(110.dp)
            .background(weatherIconByCode(forecastDay.day.condition.code)?.color ?: Blue46),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.padding(top = 5.dp)) {
            Image(
                painter = painterResource(weatherIconByCode(forecastDay.day.condition.code)?.drawable?: R.drawable._276),
                contentDescription = null,
                modifier = Modifier.size(65.dp))
            Column {
                Text(
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Monteserrat,
                    color = Color.White,
                    fontSize = 10.sp,
                    text = forecastDay.day.condition.text
                )
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Monteserrat,
                        color = Color.White,
                        fontSize = 20.sp,
                        text = forecastDay.day.maxTempC.toString()
                    )
                    Text(
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Monteserrat,
                        color = Color.White,
                        fontSize = 12.sp,
                        text = "C°"
                    )
                }

            }
        }
        Text(
            fontWeight = FontWeight.ExtraLight,
            fontFamily = Monteserrat,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(top = 5.dp)
                .background(Gray33)
                .fillMaxWidth()
                .height(30.dp),
            color = Color.White,
            textAlign = TextAlign.Center,
            text = forecastDay.date.replace(oldChar = '-', newChar = '.')
        )
    }
}

private fun describeTemperature(temp: Double): String {
    return when {
        temp > 0 -> "+"
        temp < 0 -> "-"
        else -> " "
    }
}

private fun kmhToMs(speedKmh: Double): Double {
    return speedKmh / 3.6
}

private fun windDirectionFromDegrees(degrees: Int): String {
    val directions = listOf(
        "Северный",
        "Северо-восточный",
        "Восточный",
        "Юго-восточный",
        "Южный",
        "Юго-западный",
        "Западный",
        "Северо-западный"
    )

    val index = ((degrees % 360) / 45.0).roundToInt() % 8
    return directions[index]
}

private fun mbarToMmHg(mbar: Double): Double {
    return mbar * 0.750063755
}

