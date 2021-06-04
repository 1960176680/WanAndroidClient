package com.wanandroid.mykotlin.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wanandroid.mykotlin.R
import com.wanandroid.mykotlin.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun setLayoutId(): Int=R.layout.activity_main

    override fun cancelRequest() {
        TODO("Not yet implemented")
    }
}

