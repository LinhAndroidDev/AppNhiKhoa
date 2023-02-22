package com.example.appnhikhoa.model

import android.content.Context
import android.content.SharedPreferences

class CheckShowTutorial(val context: Context) {
    var CHECK_SHOW_TUTORIAL : String ="CHECK_SHOW_TUTORIAL"

    fun putBooleanValue(key : String,value : Boolean){
        var sharedPreferences : SharedPreferences = context.getSharedPreferences("CHECK_SHOW_TUTORIAL",0)
        var edit : SharedPreferences.Editor = sharedPreferences.edit()
        edit.putBoolean(key,value)
        edit.apply()
    }

    fun getBooleanValue(key : String) : Boolean{
        var sharedPreferences : SharedPreferences = context.getSharedPreferences("CHECK_SHOW_TUTORIAL",0)
        return sharedPreferences.getBoolean(key,false)
    }
}