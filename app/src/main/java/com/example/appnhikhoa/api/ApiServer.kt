package com.example.appnhikhoa.api

import com.example.appnhikhoa.model.GiamDoc
import com.example.appnhikhoa.model.NhanVien
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServer {
    //https://firebasestorage.googleapis.com/v0/b/realtime-64f58.appspot.com/o/listnhanvien.json?alt=media&token=bf949008-aca8-4ec8-acb7-e2d578513344
    //https://firebasestorage.googleapis.com/v0/b/realtime-64f58.appspot.com/o/listgiamdoc.json?alt=media&token=9a3ecbd1-c8c4-43bd-a3d1-4dd2d3726765
    //https://firebasestorage.googleapis.com/v0/b/realtime-64f58.appspot.com/o/listgiamdocUpdate.json?alt=media&token=547b55ec-2252-4db3-99b8-25a4aaf9b0e1
    //https://firebasestorage.googleapis.com/v0/b/realtime-64f58.appspot.com/o/listnhanvienUpdate.json?alt=media&token=0b45855b-6337-4131-bcce-ef5df9dae2a1

    @GET("listnhanvien.json")
    fun getListNhanVien(@Query("alt") alt : String,@Query("token") token : String):Call<List<NhanVien>>

    @GET("listgiamdoc.json")
    fun getListGiamDoc(@Query("alt") alt : String,@Query("token") token : String):Call<List<GiamDoc>>
}