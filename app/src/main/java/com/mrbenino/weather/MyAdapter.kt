package com.mrbenino.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mrbenino.weather.model.BroadlyWeather
import retrofit2.Call

class MyAdapter(private val Dataset: BroadlyWeather) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        var temperature:TextView = view.findViewById(R.id.tv_iteam)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.broadly_iteam, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.temperature.text = Dataset.list[position].main.temp.toString()
    }

    override fun getItemCount() = Dataset.list.size
}