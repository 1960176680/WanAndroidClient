package top.jowanxu.wanandroidclient.model

import Constant
import RetrofitHelper
import cancelByActive
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import top.jowanxu.wanandroidclient.bean.HomeListResponse
import top.jowanxu.wanandroidclient.presenter.HomePresenter
import top.jowanxu.wanandroidclient.presenter.SearchPresenter
import tryCatch

class SearchModelImpl : SearchModel, CollectArticleModel {

    /**
     * Search list async
     */
    private var searchListAsync: Deferred<HomeListResponse>? = null
    /**
     * Home list async
     */
    private var likeListAsync: Deferred<HomeListResponse>? = null
    /**
     * Collect Article async
     */
    private var collectArticleAsync: Deferred<HomeListResponse>? = null

    override fun getSearchList(
        onSearchListListener: SearchPresenter.OnSearchListListener,
        page: Int,
        k: String
    ) {
        CoroutineScope(Dispatchers.Main).async {
            tryCatch({
                it.printStackTrace()
                onSearchListListener.getSearchListFailed(it.toString())
            }) {
                searchListAsync = RetrofitHelper.retrofitService.getSearchList(page, k)
                val result = searchListAsync?.await()
                result ?: let {
                    onSearchListListener.getSearchListFailed(Constant.RESULT_NULL)
                    return@async
                }
                onSearchListListener.getSearchListSuccess(result)
            }
        }
    }

    override fun cancelRequest() {
        searchListAsync?.cancelByActive()
    }

    /**
     * get Home List
     * @param onLikeListListener SearchPresenter.OnLikeListListener
     * @param page page
     */
    override fun getLikeList(onLikeListListener: SearchPresenter.OnLikeListListener, page: Int) {
        CoroutineScope(Dispatchers.Main).async {
            tryCatch({
                it.printStackTrace()
                onLikeListListener.getLikeListFailed(it.toString())
            }) {
                likeListAsync?.cancelByActive()
                likeListAsync = RetrofitHelper.retrofitService.getLikeList(page)
                val result = likeListAsync?.await()
                result ?: let {
                    onLikeListListener.getLikeListFailed(Constant.RESULT_NULL)
                    return@async
                }
                onLikeListListener.getLikeListSuccess(result)
            }
        }
    }

    /**
     * cancel HomeList Request
     */
    override fun cancelLikeListRequest() {
        likeListAsync?.cancelByActive()
    }

    /**
     * add or remove collect article
     */
    override fun collectArticle(
        onCollectArticleListener: HomePresenter.OnCollectArticleListener,
        id: Int,
        isAdd: Boolean
    ) {
        CoroutineScope(Dispatchers.Main).async {
            tryCatch({
                it.printStackTrace()
                onCollectArticleListener.collectArticleFailed(it.toString(), isAdd)
            }) {
                collectArticleAsync?.cancelByActive()
                collectArticleAsync = if (isAdd) {
                    RetrofitHelper.retrofitService.addCollectArticle(id)
                } else {
                    RetrofitHelper.retrofitService.removeCollectArticle(id)
                }
                val result = collectArticleAsync?.await()
                result ?: let {
                    onCollectArticleListener.collectArticleFailed(Constant.RESULT_NULL, isAdd)
                    return@async
                }
                onCollectArticleListener.collectArticleSuccess(result, isAdd)
            }
        }
    }

    /**
     * cancel collect article Request
     */
    override fun cancelCollectRequest() {
        collectArticleAsync?.cancelByActive()
    }
}