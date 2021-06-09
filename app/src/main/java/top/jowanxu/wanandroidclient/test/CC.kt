package com.wanandroid.mykotlin.test

import top.jowanxu.wanandroidclient.test.AA

class CC {
    fun getA(l: AA){

    }
    fun getB(){
       getA(object : AA {
           override fun dd(a: String) {

           }
       })



    }

}
