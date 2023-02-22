package com.example.appnhikhoa.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appnhikhoa.R
import com.example.appnhikhoa.model.NhanVien
import com.example.appnhikhoa.ui.giaodien.DoctorActivity
import com.example.appnhikhoa.ui.giaodien.GiaoDien
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_nhanvien.view.*

class NhanVienAdapter(val listNhanvien : MutableList<NhanVien>,val context: Context) : RecyclerView.Adapter<NhanVienAdapter.ViewHolderNhanVien>() {

    class ViewHolderNhanVien(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var anhDoctor : ImageView = itemView.findViewById(R.id.anhDoctor)
        var tenDoctor : TextView = itemView.findViewById(R.id.tenDoctor)
        var chucvuDoctor : TextView = itemView.findViewById(R.id.chucvuDoctor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNhanVien {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_nhanvien,parent,false)
        return ViewHolderNhanVien(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderNhanVien, position: Int) {
        var nhanvien : NhanVien = listNhanvien[position]
        Picasso.get().load(nhanvien.image_doctor)
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.errorimage)
            .into(holder.anhDoctor)
        holder.tenDoctor.text = nhanvien.name_doctor
        holder.chucvuDoctor.text = nhanvien.position

        holder.itemView.setTimeEmployee.setOnClickListener {
            var intent : Intent = Intent(context,DoctorActivity::class.java)
            intent.putExtra("nhanvien",nhanvien)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listNhanvien.size
    }
}