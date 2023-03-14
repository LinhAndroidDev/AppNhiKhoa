package com.example.appnhikhoa.ui.doctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appnhikhoa.R
import com.example.appnhikhoa.adapter.EditListNhanVienAdapter
import com.example.appnhikhoa.adapter.NhanVienAdapter
import com.example.appnhikhoa.api.ApiServer
import com.example.appnhikhoa.model.NhanVien
import com.example.appnhikhoa.model.TranslateAnimation
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment__bacsi.*
import kotlinx.android.synthetic.main.fragment_nhan_vien.*
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
 * Use the [FragmentNhanVien.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentNhanVien : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var editListNhanVienAdapter : EditListNhanVienAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager : LinearLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        editListNhanVien.layoutManager = linearLayoutManager

        editListNhanVien.setOnTouchListener(TranslateAnimation(requireActivity(),addDoctor))

        getData()
    }

    private fun getData() {
        var gson: Gson = GsonBuilder().create()

        val retrofit: ApiServer = Retrofit.Builder()
            .baseUrl("http://192.168.1.5/appnhikhoa/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServer::class.java)

        retrofit.getListNhanVien()
            .enqueue(object : Callback<List<NhanVien>> {
                override fun onResponse(
                    call: Call<List<NhanVien>>,
                    response: Response<List<NhanVien>>,
                ) {
                    var listNhanVien = mutableListOf<NhanVien>()
                    if (editListNhanVien != null) {
                        listNhanVien = response.body() as MutableList<NhanVien>
                        editListNhanVienAdapter = EditListNhanVienAdapter(listNhanVien, requireActivity())
                        editListNhanVien.adapter = editListNhanVienAdapter
                    }
                }

                override fun onFailure(call: Call<List<NhanVien>>, t: Throwable) {
                    Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show()
                }

            })
    }

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nhan_vien, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentNhanVien.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentNhanVien().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}