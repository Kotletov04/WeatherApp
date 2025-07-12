package com.example.weatherapp.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.weatherapp.R


val Monteserrat = FontFamily(
    fonts = listOf(
        Font(
            weight = FontWeight.Bold,
            resId = R.font.montserrat_alternates_bold,
            style = FontStyle.Normal
        ),
        Font(
            weight = FontWeight.ExtraLight,
            resId = R.font.montserrat_alternates_extra_light,
            style = FontStyle.Normal
        )
    )

)