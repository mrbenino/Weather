package com.mrbenino.weather.model

import com.google.gson.annotations.SerializedName

data class WeatherList (
    val dt:Int,
    val main:MainBroadly,
    val weather:List<Weather>,
    val clouds:Clouds,
    val wind:Wind,
    val visibility:Int,
    val pop:Float,
    val sys:SysBroadly,
    @SerializedName("dt_txt")
    val dtTxt:String
)