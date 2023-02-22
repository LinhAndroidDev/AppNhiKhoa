package com.example.appnhikhoa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appnhikhoa.R
import com.example.appnhikhoa.model.GiamDoc
import com.squareup.picasso.Picasso

class GiamDocAdapter(val listGiamDoc: MutableList<GiamDoc>,val context: Context) : RecyclerView.Adapter<GiamDocAdapter.ViewHolderGiamDoc>() {
    class ViewHolderGiamDoc(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var anhGiamDoc : ImageView = itemView.findViewById(R.id.anhGiamDoc)
        var tenGiamDoc : TextView = itemView.findViewById(R.id.nameGiamDoc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGiamDoc {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_giamdoc,parent,false)
        return ViewHolderGiamDoc(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderGiamDoc, position: Int) {
        var giamDoc : GiamDoc = listGiamDoc[position]
        Picasso.get().load(giamDoc.image_doctor)
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.errorimage)
            .into(holder.anhGiamDoc)
        holder.tenGiamDoc.text = giamDoc.name_doctor
    }

    override fun getItemCount(): Int {
        return listGiamDoc.size
    }
}