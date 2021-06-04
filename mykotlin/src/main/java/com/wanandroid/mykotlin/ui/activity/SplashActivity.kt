package com.wanandroid.mykotlin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * FileName: SplashActivity
 * Author: ZhouWenGuang
 * Date: 2021/6/4 11:04
 * History:
 */
class SplashActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this@SplashActivity,MainActivity::class.java))
    }

}