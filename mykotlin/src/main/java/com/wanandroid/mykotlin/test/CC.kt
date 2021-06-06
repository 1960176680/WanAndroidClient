package com.wanandroid.mykotlin.test

class CC {
    fun getA(l:AA){

    }
    fun getB(){
       getA(object: AA {
           override fun dd(a: String) {
               TODO("Not yet implemented")
           }

           override fun cc(a: String) {
               TODO("Not yet implemented")
           }

       })
    }
}