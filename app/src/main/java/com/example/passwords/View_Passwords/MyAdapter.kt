package com.example.passwords.View_Passwords

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.passwords.DB_Passwords.Password
import com.example.passwords.R
import com.example.passwords.save_password2

class MyAdapter(private val savedPass : ArrayList<Password>):RecyclerView.Adapter<MyAdapter.MyViewHolder>()
{
    var onItemClick : ((Password) -> Unit)? = null
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return savedPass.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pass = savedPass[position]
        holder.textView.text = pass.platform
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(pass)
        }

    }
}