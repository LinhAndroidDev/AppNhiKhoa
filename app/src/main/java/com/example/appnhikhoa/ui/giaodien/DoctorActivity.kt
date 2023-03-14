package com.example.appnhikhoa.ui.giaodien

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appnhikhoa.R
import com.example.appnhikhoa.adapter.GiamDocAdapter
import com.example.appnhikhoa.adapter.NhanVienAdapter
import com.example.appnhikhoa.api.ApiServer
import com.example.appnhikhoa.model.BaseActivity
import com.example.appnhikhoa.model.GiamDoc
import com.example.appnhikhoa.model.NhanVien
import com.example.appnhikhoa.ui.giaodien.component.DatLich
import com.example.appnhikhoa.ui.giaodien.component.NhanTin
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_doctor.*
import kotlinx.android.synthetic.main.activity_nhan_tin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DoctorActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor)

        var nhanVien:NhanVien = this.intent.getSerializableExtra("nhanvien") as NhanVien

        tenBacsi.text = nhanVien.name_doctor
        Picasso.get().load(nhanVien.image_doctor)
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.errorimage)
            .into(anh)

        setSupportActionBar(toolBarNhanTin)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolBarDoctor.setNavigationIcon(R.drawable.back)
        toolBarDoctor.setNavigationOnClickListener {
            this.finish()
        }

        nhanTin.setOnClickListener {
            var intent: Intent = Intent(this@DoctorActivity, NhanTin::class.java)
            startActivity(intent)
        }
        datLich.setOnClickListener {
            var intent:Intent = Intent(this@DoctorActivity, DatLich::class.java)
            intent.putExtra("nhanvien",nhanVien)
            startActivity(intent)
        }

    }
}