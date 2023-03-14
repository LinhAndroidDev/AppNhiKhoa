package com.example.appnhikhoa.ui.giaodien.component

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.Gallery
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appnhikhoa.R
import com.example.appnhikhoa.adapter.MessengerAdapter
import com.example.appnhikhoa.model.BaseActivity
import com.example.appnhikhoa.model.Messenger
import kotlinx.android.synthetic.main.activity_dat_lich.*
import kotlinx.android.synthetic.main.activity_nhan_tin.*

@Suppress("DEPRECATION")
class NhanTin : BaseActivity(){
    lateinit var listMessenger : MutableList<Messenger>
    private lateinit var messengerAdapter : MessengerAdapter
    var GALLERY_RED_CODE : Int = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nhan_tin)

        setSupportActionBar(toolBarNhanTin)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolBarNhanTin.setNavigationIcon(R.drawable.back)
        toolBarNhanTin.setNavigationOnClickListener {
            this.finish()
        }

        var linearlayout : LinearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recycleMessenger.layoutManager = linearlayout
        listMessenger = mutableListOf()
        messengerAdapter = MessengerAdapter(listMessenger)
        recycleMessenger.adapter = messengerAdapter

        getImage.setOnClickListener {
            val intent : Intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent,GALLERY_RED_CODE)
        }

        sendMess.setOnClickListener {
            var txtString : String = textNhanTin.text.toString()
            if(TextUtils.isEmpty(txtString)){
                return@setOnClickListener
            }
            listMessenger.add(Messenger(txtString))
            messengerAdapter.notifyDataSetChanged()
            recycleMessenger.scrollToPosition(listMessenger.size - 1)
            textNhanTin.setText("")
        }
    }
}