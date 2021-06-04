package com.wanandroid.mykotlin.base

import android.app.Application
import android.content.ComponentCallbacks2
import com.bumptech.glide.Glide
import com.wanandroid.mykotlin.BuildConfig

/**
 * FileName: BaseApplication
 * Author: ZhouWenGuang
 * Date: 2021/6/4 16:16
 * History:
 */
class BaseApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
//            LeakCanary.install(this)
        }
        Preference.setContext(applicationContext)
    }
    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        // clear Glide cache
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory()
        }
        // trim memory
        Glide.get(this).trimMemory(level)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        // low memory clear Glide cache
        Glide.get(this).clearMemory()
    }
}