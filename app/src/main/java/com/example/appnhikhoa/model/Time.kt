package com.example.appnhikhoa.model

class Time {
    private var time : String = ""

    constructor(){
    }

    constructor(tiMe: String){
        this.time = tiMe
    }

    fun setTime(tiMe : String){
        time = tiMe
    }

    fun getTime() : String{
        return time
    }
}