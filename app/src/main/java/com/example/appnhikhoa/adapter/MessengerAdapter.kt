package com.example.appnhikhoa.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.appnhikhoa.R
import com.example.appnhikhoa.model.Messenger

class MessengerAdapter(val listMessenger : MutableList<Messenger>) : RecyclerView.Adapter<MessengerAdapter.ViewHolderMesseger>() {
    class ViewHolderMesseger(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var messenger : TextView = itemView.findViewById(R.id.txtMessenger)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMesseger {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_messenger,parent,false)
        return ViewHolderMesseger(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderMesseger, position: Int) {
        var messenger : Messenger = listMessenger[position]
        holder.messenger.text = messenger.messenger
    }

    override fun getItemCount(): Int {
        return listMessenger.size
    }
}