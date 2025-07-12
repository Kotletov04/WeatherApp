package com.example.weatherapp.ui.components.city

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.Black22
import com.example.weatherapp.ui.theme.Gray3A
import com.example.weatherapp.ui.theme.Monteserrat
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.navigation.NavController
import com.example.weatherapp.ui.nav.CityRoute
import com.example.weatherapp.ui.util.ErrorAlert
import kotlinx.coroutines.delay

@Composable
fun CityComponent(cityViewModel: CityViewModel, navController: NavController) {
    val isOpen = remember { mutableStateOf(false) }
    val textFieldIsActive = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    isOpen.value = if (cityViewModel.cityField.value != "") true else false

    val state = cityViewModel.state.value
    Column(modifier = Modifier
        .background(Black22)
        .fillMaxSize()
        .padding(50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            painter = painterResource(R.drawable.background_city_screen), contentDescription = null
        )
        Column(modifier = Modifier.padding(top = 30.dp)) {
            Text(
                modifier = Modifier.padding(10.dp).fillMaxWidth(),
                color = Color.White,
                fontFamily = Monteserrat,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                text = "Выберите ваш город"
            )
            Column {
                TextField(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                        .onFocusChanged {
                            textFieldIsActive.value = it.isFocused
                        },
                    value = cityViewModel.cityField.value,
                    textStyle = TextStyle(
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.ExtraLight,
                        fontFamily = Monteserrat,
                        color = Color.White,
                        fontSize = 14.sp,
                    ),
                    onValueChange = { newText ->
                        cityViewModel.cityField.value = newText
                        cityViewModel.getCity()
                                    },
                    placeholder = {
                        Text(
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.ExtraLight,
                            fontFamily = Monteserrat,
                            color = Color.White,
                            fontSize = 14.sp,
                            text = "Ваш город"
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Gray3A,
                        unfocusedTextColor = Gray3A,
                        disabledIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent
                    ),
                    trailingIcon = {
                        Image(painter = painterResource(R.drawable.search_icon), contentDescription = null)
                    },
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            cityViewModel.getCity()
                        }
                    )
                )
                AnimatedVisibility(visible = isOpen.value) {
                    CityList(cityViewModel = cityViewModel, navController = navController)
                }
            }
        }
    }
    if (state.error!! != "" && !textFieldIsActive.value) {
        LaunchedEffect(key1 = null) {
            delay(5000)
            cityViewModel.clearErrorMessage()
        }
        ErrorAlert(text = state.error)
    }
}


@Composable
private fun CityList(cityViewModel: CityViewModel, navController: NavController) {
    val state = cityViewModel.state.value
    Box(modifier = Modifier.background(Color.White).height(1.dp).fillMaxWidth())
    Spacer(modifier = Modifier.height(10.dp))
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp)
    ) {
        items(state.city) { city ->
            Column(modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(Gray3A)
                .padding(top = 5.dp, bottom = 5.dp)
                .clickable {
                    cityViewModel.saveCity(item = city)
                    navController.navigate(CityRoute(cityName = city.name))
                }) {
                Text(
                    modifier = Modifier.padding(top = 3.dp, bottom = 3.dp, start = 5.dp, end = 5.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.ExtraLight,
                    fontFamily = Monteserrat,
                    color = Color.White,
                    fontSize = 14.sp,
                    text = city.name
                )
                Box(modifier = Modifier.padding(start = 5.dp, end = 5.dp).background(Color.White).height(1.dp).fillMaxWidth())

            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Spacer(modifier = Modifier.height(70.dp))
        }
    }
}