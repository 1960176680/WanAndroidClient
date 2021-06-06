package com.wanandroid.mykotlin.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.widget.AppCompatButton
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.wanandroid.mykotlin.R
import com.wanandroid.mykotlin.base.BaseActivity
import com.wanandroid.mykotlin.base.Preference
import com.wanandroid.mykotlin.constant.Constant
import com.wanandroid.mykotlin.ui.fragment.CommonUseFragment
import com.wanandroid.mykotlin.ui.fragment.HomeFragment
import com.wanandroid.mykotlin.ui.fragment.TypeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private var lastTime: Long = 0
    private var currentIndex = 0
    private var homeFragment: HomeFragment? = null
    private var typeFragment: TypeFragment? = null
    private var commonUseFragment: CommonUseFragment? = null
    private val fragmentManager by lazy {
        supportFragmentManager
    }
    /**
     * check login for SharedPreferences
     */
    private val isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)
    /**
     * local username
     */
    private val username: String by Preference(Constant.USERNAME_KEY, "")

    /**
     * login username
     */
    private lateinit var navigationViewUsername: TextView

    /**
     * login or logout
     */
    private lateinit var navigationViewLogout: AppCompatButton
    override fun setLayoutId(): Int=R.layout.activity_main
    override fun initImmersionBar() {
        super.initImmersionBar()
        immersionBar.titleBar(R.id.toolbar).init()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.run {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }
        bottomNavigation.run {
            setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
            selectedItemId = R.id.navigation_home
        }





    }



    override fun cancelRequest() {
        TODO("Not yet implemented")
    }

    /**
     * NavigationItemSelect监听
     */
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            setFragment(item.itemId)
            return@OnNavigationItemSelectedListener when (item.itemId) {
                R.id.navigation_home -> {
                    if (currentIndex == R.id.navigation_home) {
                        homeFragment?.smoothScrollToPosition()
                    }
                    currentIndex = R.id.navigation_home
                    true
                }
                R.id.navigation_type -> {
                    if (currentIndex == R.id.navigation_type) {
                        typeFragment?.smoothScrollToPosition()
                    }
                    currentIndex = R.id.navigation_type
                    true
                }
                else -> {
                    false
                }
            }
        }
    /**
     * 显示对应Fragment
     */
    private fun setFragment(index: Int) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        fragmentManager.beginTransaction().apply {
            homeFragment ?: let {
                HomeFragment().let {
                    homeFragment = it
                    add(R.id.content, it)
                }
            }
            typeFragment ?: let {
                TypeFragment().let {
                    typeFragment = it
                    add(R.id.content, it)
                }
            }
            commonUseFragment ?: let {
                CommonUseFragment().let {
                    commonUseFragment = it
                    add(R.id.content, it)
                }
            }
            hideFragment(this)
            when (index) {
                R.id.navigation_home -> {
                    toolbar.title = getString(R.string.app_name)
                    homeFragment?.let {
                        this.show(it)
                    }
                }
                R.id.navigation_type -> {
                    toolbar.title = getString(R.string.title_dashboard)
                    typeFragment?.let {
                        this.show(it)
                    }
                }
                R.id.menuHot -> {
                    toolbar.title = getString(R.string.hot_title)
                    commonUseFragment?.let {
                        this.show(it)
                    }
                }
            }
        }.commit()
    }


}













































































































































