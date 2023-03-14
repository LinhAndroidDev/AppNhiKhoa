package com.example.appnhikhoa.ui.giaodien.component

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appnhikhoa.R
import com.example.appnhikhoa.model.Time
import com.example.appnhikhoa.adapter.TimeAdapter
import com.example.appnhikhoa.model.BaseActivity
import com.example.appnhikhoa.model.NhanVien
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_dat_lich.*
import kotlinx.android.synthetic.main.layout_time.*

class DatLich : BaseActivity() {
    var STT : Int = 1
    lateinit var mData : DatabaseReference
    lateinit var listTime: ArrayList<Time>
    lateinit var timeAdapter: TimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dat_lich)

        reduce.visibility = View.GONE

        setSupportActionBar(toolBarDatLich)
        toolBarDatLich.setNavigationIcon(R.drawable.back)
        toolBarDatLich.setNavigationOnClickListener{
            this.finish()
        }

        if(intent.getSerializableExtra("nhanvien") != null) {
            var nhanVien: NhanVien = intent.getSerializableExtra("nhanvien") as NhanVien

            tenDatLich.text = nhanVien.name_doctor
            Picasso.get().load(nhanVien.image_doctor)
                .placeholder(R.drawable.loadimage)
                .error(R.drawable.errorimage)
                .into(avartarDatLich)
            phoneDatLich.text = nhanVien.phone
        }

        mData = FirebaseDatabase.getInstance().reference
        var gridLayoutManager:GridLayoutManager = GridLayoutManager(this@DatLich,3)
        recycleViewDatLich.layoutManager = gridLayoutManager

        listTime = ArrayList()

        loadTime.visibility = View.VISIBLE

        getData()

        nextData()
    }

    private fun nextData() {
        increase.setOnClickListener {
            STT++
            listTime.clear()
            getData()
            reduce.visibility = View.VISIBLE
//            if(STT == MAX){
//                increase.visibility = View.GONE
//            }
        }

        reduce.setOnClickListener {
            STT--
            listTime.clear()
            getData()
            increase.visibility = View.VISIBLE
            if(STT == listTime.size){
                reduce.visibility = View.GONE
            }
        }
    }

    private fun getData() {
        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                val time = dataSnapshot.getValue(Time::class.java)
                listTime.add(time!!)
                timeAdapter = TimeAdapter(listTime,this@DatLich)
                timeAdapter.notifyDataSetChanged()
                recycleViewDatLich.adapter = timeAdapter
                loadTime.visibility = View.INVISIBLE
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {

            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }

        mData.child("Time").child(STT.toString()).child("hours").addChildEventListener(childEventListener)

        val valueEvent : ValueEventListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                textTime.text = snapshot.value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        mData.child("Time").child(STT.toString()).child("time").addValueEventListener(valueEvent)
    }
}