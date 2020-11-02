package com.mrbenino.weather.model

data class BroadlyWeather (
    val cod:String,
    val message:Int,
    val cnt: Int,
    val list:List<WeatherList>
)