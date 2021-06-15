package top.jowanxu.wanandroidclient.model

import Constant
import RetrofitHelper
import cancelByActive
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import top.jowanxu.wanandroidclient.bean.ArticleListResponse
import top.jowanxu.wanandroidclient.presenter.TypeArticlePresenter
import tryCatch

class TypeArticleModelImpl : TypeArticleModel {

    /**
     * Type Article list list async
     */
    private var typeArticleListAsync: Deferred<ArticleListResponse>? = null

    /**
     * get Type Article list
     * @param onTypeArticleListListener TypeArticlePresenter.OnTypeArticleListListener
     * @param page page
     * @param cid cid
     */
    override fun getTypeArticleList(
        onTypeArticleListListener: TypeArticlePresenter.OnTypeArticleListListener,
        page: Int,
        cid: Int
    ) {
        CoroutineScope(Dispatchers.Main).async {
            tryCatch({
                it.printStackTrace()
                onTypeArticleListListener.getTypeArticleListFailed(it.toString())
            }) {
                typeArticleListAsync?.cancelByActive()
                typeArticleListAsync = RetrofitHelper.retrofitService.getArticleList(page, cid)
                val result = typeArticleListAsync?.await()
                result ?: let {
                    onTypeArticleListListener.getTypeArticleListFailed(Constant.RESULT_NULL)
                    return@async
                }
                onTypeArticleListListener.getTypeArticleListSuccess(result)
            }
        }
    }

    /**
     * cancel request
     */
    override fun cancelRequest() {
        typeArticleListAsync?.cancelByActive()
    }
}