package com.mrbenino.weather

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mrbenino.weather.model.Weather
import com.mrbenino.weather.network.APIService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.awaitResponse
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
    private fun getCurrentData(){

        scope.launch {
            val call = api.currentWeather()
            val responese = call.await()
            Log.d(TAG, responese.toString())
            textView.text = responese.name
            val temp = responese.main.temp.toInt()
            textView2.text = (temp - 273.15).toInt().toString()
            textView3.text = responese.weather.getOrNull(0)?.main.toString()
        }
    }
}