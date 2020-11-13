package com.mrbenino.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mrbenino.weather.model.BroadlyWeather
import retrofit2.Call

class MyAdapter(private val Dataset: BroadlyWeather) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        var temperature:TextView = view.findViewById(R.id.tv_iteam_temperature)
        var time:TextView = view.findViewById(R.id.tv_iteam_time)
        var cloud:TextView = view.findViewById(R.id.tv_iteam_cloud)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.broadly_iteam, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.temperature.text = Dataset.list[position].main.temp.toString()
        holder.time.text = Dataset.list[position].dtTxt.toString()
        val icon = Dataset.list[position].weather.getOrNull(0)?.icon.toString()
        Glide.with(holder.view)
            .load("http://openweathermap.org/img/wn/$icon@4x.png")
            .into(holder.view.findViewById(R.id.tv_iteam_icon))
    }

    override fun getItemCount() = Dataset.list.size
}