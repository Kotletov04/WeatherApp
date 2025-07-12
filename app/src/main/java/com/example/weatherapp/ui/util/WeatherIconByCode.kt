package com.example.weatherapp.ui.util

import androidx.compose.ui.graphics.Color
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.Blue46
import com.example.weatherapp.ui.theme.Gray48

data class WeatherDescription(val drawable: Int, val color: Color)

fun weatherIconByCode(code: Int): WeatherDescription? {
    val iconMap = mapOf(
        1000 to WeatherDescription(drawable = R.drawable._275, color = Blue46),
        1003 to WeatherDescription(R.drawable._278, color = Blue46),
        1006 to WeatherDescription(R.drawable._276, color = Blue46),
        1009 to WeatherDescription(R.drawable._009_1153, color = Gray48),
        1030 to WeatherDescription(R.drawable._030_1147, color = Blue46),
        1063 to WeatherDescription(R.drawable._063_1192, color = Blue46),
        1066 to WeatherDescription(R.drawable._066_1258, color = Blue46),
        1069 to WeatherDescription(R.drawable._069_1264, color = Gray48),
        1072 to WeatherDescription(R.drawable._072_1198, color = Gray48),
        1087 to WeatherDescription(R.drawable._274, color = Gray48),
        1114 to WeatherDescription(R.drawable._066_1258, color = Blue46),
        1117 to WeatherDescription(R.drawable._066_1258, color = Blue46),
        1135 to WeatherDescription(R.drawable._030_1147, color = Blue46),
        1147 to WeatherDescription(R.drawable._030_1147, color = Blue46),
        1150 to WeatherDescription(R.drawable._063_1192, color = Blue46),
        1153 to WeatherDescription(R.drawable._009_1153, color = Gray48),
        1168 to WeatherDescription(R.drawable._072_1198, color = Gray48),
        1171 to WeatherDescription(R.drawable._072_1198, color = Gray48),
        1180 to WeatherDescription(R.drawable._063_1192, color = Blue46),
        1183 to WeatherDescription(R.drawable._063_1192, color = Blue46),
        1186 to WeatherDescription(R.drawable._063_1192, color = Blue46),
        1189 to WeatherDescription(R.drawable._277, color = Gray48),
        1192 to WeatherDescription(R.drawable._063_1192, color = Blue46),
        1195 to WeatherDescription(R.drawable._186_1246, color = Gray48),
        1198 to WeatherDescription(R.drawable._072_1198, color = Gray48),
        1201 to WeatherDescription(R.drawable._072_1198, color = Gray48),
        1204 to WeatherDescription(R.drawable._069_1264, color = Gray48),
        1207 to WeatherDescription(R.drawable._069_1264, color = Gray48),
        1210 to WeatherDescription(R.drawable._066_1258, color = Blue46),
        1213 to WeatherDescription(R.drawable._066_1258, color = Blue46),
        1216 to WeatherDescription(R.drawable._066_1258, color = Blue46),
        1219 to WeatherDescription(R.drawable._066_1258, color = Blue46),
        1222 to WeatherDescription(R.drawable._066_1258, color = Blue46),
        1225 to WeatherDescription(R.drawable._066_1258, color = Blue46),
        1237 to WeatherDescription(R.drawable._072_1198, color = Gray48),
        1240 to WeatherDescription(R.drawable._186_1246, color = Gray48),
        1243 to WeatherDescription(R.drawable._186_1246, color = Gray48),
        1246 to WeatherDescription(R.drawable._186_1246, color = Gray48),
        1249 to WeatherDescription(R.drawable._069_1264, color = Gray48),
        1252 to WeatherDescription(R.drawable._069_1264, color = Gray48),
        1255 to WeatherDescription(R.drawable._066_1258, color = Blue46),
        1258 to WeatherDescription(R.drawable._066_1258, color = Blue46),
        1261 to WeatherDescription(R.drawable._072_1198, color = Gray48),
        1264 to WeatherDescription(R.drawable._072_1198, color = Gray48),
        1273 to WeatherDescription(R.drawable._273_1282, color = Gray48),
        1276 to WeatherDescription(R.drawable._273_1282, color = Gray48),
        1279 to WeatherDescription(R.drawable._274, color = Gray48),
        1282 to WeatherDescription(R.drawable._274, color = Gray48),

    )
    return iconMap[code]
}