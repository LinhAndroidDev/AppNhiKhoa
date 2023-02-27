package com.example.appnhikhoa.ui.giaodien

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appnhikhoa.R
import com.example.appnhikhoa.adapter.GiamDocAdapter
import com.example.appnhikhoa.adapter.NhanVienAdapter
import com.example.appnhikhoa.api.ApiServer
import com.example.appnhikhoa.model.GiamDoc
import com.example.appnhikhoa.model.NhanVien
import com.example.appnhikhoa.ui.component.DatLich
import com.example.appnhikhoa.ui.component.NhanTin
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment__bacsi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_Bacsi.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_Bacsi : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__bacsi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getList()

        getData()
    }

    private fun getList() {
        var gridLayoutManager : GridLayoutManager = GridLayoutManager(requireActivity(),2)
        recycleViewListNhanVien.layoutManager = gridLayoutManager

        var linearLayoutManager : LinearLayoutManager = LinearLayoutManager(requireActivity(),
            LinearLayoutManager.HORIZONTAL,false)
        recycleViewListGiamDoc.layoutManager = linearLayoutManager
    }

    private fun getData() {
        var gson : Gson = GsonBuilder().create()

        var retrofit : ApiServer = Retrofit.Builder()
            .baseUrl("https://firebasestorage.googleapis.com/v0/b/realtime-64f58.appspot.com/o/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServer::class.java)

        retrofit.getListNhanVien("media","bf949008-aca8-4ec8-acb7-e2d578513344")
            .enqueue(object : Callback<List<NhanVien>> {
                override fun onResponse(
                    call: Call<List<NhanVien>>,
                    response: Response<List<NhanVien>>,
                ) {
                    var listNhanVien = mutableListOf<NhanVien>()
                    if(recycleViewListNhanVien != null) {
                        listNhanVien = response.body() as MutableList<NhanVien>
                        val listNhanVienAdapter : NhanVienAdapter = NhanVienAdapter(listNhanVien,requireActivity())
                        recycleViewListNhanVien.adapter = listNhanVienAdapter
                    }
                }

                override fun onFailure(call: Call<List<NhanVien>>, t: Throwable) {
                    Toast.makeText(requireActivity(),"Error", Toast.LENGTH_SHORT).show()
                }

            })

        retrofit.getListGiamDoc("media","9a3ecbd1-c8c4-43bd-a3d1-4dd2d37267658")
            .enqueue(object : Callback<List<GiamDoc>> {
                override fun onResponse(call: Call<List<GiamDoc>>, response: Response<List<GiamDoc>>) {
                    var listGiamDoc = mutableListOf<GiamDoc>()
                    if(recycleViewListGiamDoc != null) {
                        listGiamDoc = response.body() as MutableList<GiamDoc>
                        val listGiamDocAdapter : GiamDocAdapter = GiamDocAdapter(listGiamDoc,requireActivity())
                        recycleViewListGiamDoc.adapter = listGiamDocAdapter
                        loadDoctor.visibility = View.INVISIBLE
                    }
                }

                override fun onFailure(call: Call<List<GiamDoc>>, t: Throwable) {
                    Toast.makeText(requireActivity(),"Error", Toast.LENGTH_SHORT).show()
                    loadDoctor.visibility = View.INVISIBLE
                }

            })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment_Bacsi.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_Bacsi().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}