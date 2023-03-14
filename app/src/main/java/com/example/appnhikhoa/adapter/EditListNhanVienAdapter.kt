package com.example.appnhikhoa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.appnhikhoa.R
import com.example.appnhikhoa.model.NhanVien
import com.squareup.picasso.Picasso

class EditListNhanVienAdapter(val listNhanVien : List<NhanVien>,val context : Context)
    : RecyclerView.Adapter<EditListNhanVienAdapter.ViewholderNhanVien>() {

    class ViewholderNhanVien(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageDoctor : ImageView = itemView.findViewById(R.id.imageDoctor)
        val txtNameDoctor : TextView = itemView.findViewById(R.id.txtNameDoctor)
        val txtPhoneDoctor : TextView = itemView.findViewById(R.id.txtPhoneDoctor)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): EditListNhanVienAdapter.ViewholderNhanVien {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_nhanvien_edit,parent,false)
        return ViewholderNhanVien(itemView)
    }

    override fun onBindViewHolder(
        holder: EditListNhanVienAdapter.ViewholderNhanVien,
        position: Int,
    ) {
        val nhanVien : NhanVien = listNhanVien[position]
        Picasso.get().load(nhanVien.image_doctor)
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.errorimage)
            .into(holder.imageDoctor)
        holder.txtNameDoctor.text = nhanVien.name_doctor
        holder.txtPhoneDoctor.text = nhanVien.phone
    }

    override fun getItemCount(): Int {
        return listNhanVien.size
    }
}