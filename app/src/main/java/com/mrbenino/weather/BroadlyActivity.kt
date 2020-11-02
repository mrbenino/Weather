package com.mrbenino.weather

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mrbenino.weather.model.BroadlyWeather
import com.mrbenino.weather.network.APIService
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory

val TAG2 = "BroadlyActivity"

class BroadlyActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main+job)

    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(APIService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_weather)

        getCurrentData()

        viewManager = LinearLayoutManager(this)


        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter

        }
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getCurrentData(){

        scope.launch {
            val call = api.broadlyWeather()
            val responese: BroadlyWeather = call.await()
            withContext(Dispatchers.Main){
                viewAdapter = MyAdapter(responese)
            }
            Log.d(TAG2, responese.toString())
        }
    }

}
