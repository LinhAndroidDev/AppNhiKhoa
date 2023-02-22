package com.example.appnhikhoa.adapter

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.appnhikhoa.R
import com.example.appnhikhoa.model.Time
import com.example.appnhikhoa.ui.component.DatLich
import kotlinx.android.synthetic.main.layout_inforpatient.*

class TimeAdapter(val listTime: List<Time>,val context: Context)
    : RecyclerView.Adapter<TimeAdapter.TimeViewHolder>() {

    var currentPositive : Int = -1

        class TimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var timeView : TextView = itemView.findViewById(R.id.time)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_time,parent,false)
        return TimeViewHolder(itemView)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        var time: Time = listTime[position]
        holder.timeView.text = time.getTime()
        holder.itemView.setOnClickListener {
            var dialog : Dialog = Dialog(context)
            dialog.setContentView(R.layout.layout_inforpatient)
            dialog.show()
            currentPositive = position
            notifyDataSetChanged()
        }

        if(currentPositive == position){
            holder.timeView.setBackgroundResource(R.drawable.select)
            holder.timeView.setTextColor(Color.WHITE)
        }else{
            holder.timeView.setBackgroundResource(R.drawable.un_select)
            holder.timeView.setTextColor(Color.BLACK)
        }
    }

    override fun getItemCount(): Int {
        return listTime.size
    }
}