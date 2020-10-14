package com.mrbenino.weather

import kotlinx.coroutines.*
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
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
//        Glide.with(this)
//            .load("https://openweathermap.org/img/wn/03d@4x.png")
//            .into(findViewById<ImageView>(R.id.imageView2))
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
//            imageView.setImageDrawable(getDrawable(R.drawable.a1))
//            imageView.elevation = 0f

//            Picasso.get().load("http://openweathermap.org/img/wn/04d@4x.png").into(imageView2)
            Log.d(TAG,icon)
//            val pach =
//            Log.d(TAG,pach)
//            Picasso.get()
//                .load(pach)
//                .error(R.drawable.a2)
//                .into(imageView2)


            Glide.with(this@MainActivity)
                .load("http://openweathermap.org/img/wn/03d@4x.png")
                .into(imageView2)
        }

    }
}