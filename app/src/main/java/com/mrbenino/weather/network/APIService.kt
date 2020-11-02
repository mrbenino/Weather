package com.mrbenino.weather.network

import com.mrbenino.weather.model.BroadlyWeather
import com.mrbenino.weather.model.CurrentWeather
import retrofit2.Call
import retrofit2.http.GET


interface APIService {
    @GET("/data/2.5/weather?q=kyiv&appid=43e63ada628a1cc7cfd7a752fd831999")
    fun currentWeather(): Call<CurrentWeather>

    @GET("/data/2.5/forecast?q=kyiv&appid=43e63ada628a1cc7cfd7a752fd831999")
    fun broadlyWeather(): Call<BroadlyWeather>
}