package com.mrbenino.weather

//import com.mrbenino.weather.network.APIService
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mrbenino.weather.network.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory


//                   TO DO ☐☑
//  ☐ получения погоды сегодня
//  ☐ делаем UI
//    api key = 43e63ada628a1cc7cfd7a752fd831999

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
            val responese = call.awaitResponse()
            Log.d(TAG, responese.toString())
//            val data = api.currentWeather().await()
//            Log.d(TAG, data.name)
        }
        }
    }

