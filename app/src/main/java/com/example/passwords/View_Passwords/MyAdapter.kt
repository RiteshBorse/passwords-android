package com.example.passwords.View_Passwords

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.passwords.DB_Passwords.Password
import com.example.passwords.R

class MyAdapter(private val dataList : List<Password>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>()
{
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val textView : TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val dataItem = dataList[position]
        holder.textView.text = dataItem.platform
    }
}