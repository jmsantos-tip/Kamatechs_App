package com.example.kamatechs_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val storageList : ArrayList<Storage>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.storage_item,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = storageList[position]

        holder.humidity.text = currentitem.humidity.toString()
        holder.temperature.text = currentitem.temperature.toString()


    }

    override fun getItemCount(): Int {

        return storageList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){


        val humidity : TextView = itemView.findViewById(R.id.humValue)
        val temperature : TextView = itemView.findViewById(R.id.tempValue)

    }

}