package com.example.kamatechs_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val storageList : ArrayList<Storage>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.storage_item,
            parent,false)
        return MyViewHolder(itemView,mListener)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = storageList[position]

        holder.tvDateTime.text = currentItem.dateTime.toString()
        holder.tvTemp.text = currentItem.temperature.toString()
        holder.tvHumid.text = currentItem.humidity.toString()
        holder.tvRoomTemp.text = currentItem.room_temperature.toString()


    }

    override fun getItemCount(): Int {

        return 1
    }


    class MyViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView){

        val tvDateTime : TextView = itemView.findViewById(R.id.tvDateTime)
        val tvTemp : TextView = itemView.findViewById(R.id.tvTemp)
        val tvHumid : TextView = itemView.findViewById(R.id.tvHumid)
        val tvRoomTemp : TextView = itemView.findViewById(R.id.tvRoomTemp)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}