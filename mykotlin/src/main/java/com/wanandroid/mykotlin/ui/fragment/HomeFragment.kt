package com.wanandroid.mykotlin.ui.fragment

import android.view.View
import com.wanandroid.mykotlin.base.BaseFragment
import com.wanandroid.mykotlin.presenter.HomeFragmentPresenterImpl
import com.wanandroid.mykotlin.view.CollectArticleView
import com.wanandroid.mykotlin.view.HomeFragmentView
import top.jowanxu.wanandroidclient.bean.Datas
import top.jowanxu.wanandroidclient.bean.HomeListResponse

class HomeFragment : BaseFragment(), HomeFragmentView, CollectArticleView {
    companion object {
        private const val BANNER_TIME = 5000L
    }

    /**
     * mainView
     */
    private var mainView: View? = null
    /**
     * Data List
     */
    private val datas = mutableListOf<Datas>()
    /**
     * presenter
     */
    private val homeFragmentPresenter: HomeFragmentPresenterImpl by lazy {
        HomeFragmentPresenterImpl(this, this)
    }
}