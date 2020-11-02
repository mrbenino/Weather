package com.mrbenino.weather

import kotlinx.coroutines.*
import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationSettingsRequest
import com.mrbenino.weather.network.APIService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.openweathermap.org"
val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main+job)

    val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCurrentData()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getCurrentData(){

       scope.launch {
            val call = api.currentWeather()
            val responese = call.await()
            Log.d(TAG, responese.toString())
            textView.text = responese.name
            val temp = responese.main.temp.toInt()
            textView2.text = (temp - 273.15).toInt().toString()
            textView3.text = responese.weather.getOrNull(0)?.main.toString()
            val icon = responese.weather.getOrNull(0)?.icon.toString()
            Log.d(TAG,icon)

            Glide.with(this@MainActivity)
                .load("http://openweathermap.org/img/wn/$icon@4x.png")
                .into(imageView2)
        }
    }

    fun onBroadleWeather(view: View){
        val BroadleWeatherActivity = Intent(this,BroadlyActivity::class.java)
        startActivity(BroadleWeatherActivity)
    }

}