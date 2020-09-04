package com.dev.fnlibs.Bluetooth

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.fnlibs.R

class AdapterDevices(val context: Context, val dataList: List<btDataClass>) : RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        return MyViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val list: btDataClass = dataList[position]

        holder.devName?.text = list.deviceName
        holder.devAddress?.text = list.deviceIP
    }

    override fun getItemCount() = dataList.size
}

class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    :RecyclerView.ViewHolder(inflater.inflate(R.layout.bluetooth_device_list, parent, false))  {

    var devName: TextView? = null
    var devAddress: TextView? = null

    init {
        devName = itemView.findViewById(R.id.deviceName)
        devAddress = itemView.findViewById(R.id.deviceIP)
    }
}
