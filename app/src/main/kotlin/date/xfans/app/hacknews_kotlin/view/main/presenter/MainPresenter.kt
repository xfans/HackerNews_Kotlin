package date.xfans.app.hacknews_kotlin.view.main.presenter

import date.xfans.app.hacknews_kotlin.date.Post
import date.xfans.app.hacknews_kotlin.date.source.DataManager
import date.xfans.app.hacknews_kotlin.date.source.DataSource
import date.xfans.app.hacknews_kotlin.date.source.ResultCallBack
import date.xfans.app.hacknews_kotlin.view.main.contract.MainContract

/**
 * Created by zhu on 2016/6/21.
 */
class MainPresenter(view : MainContract.View) :MainContract.Presenter {
    var mView: MainContract.View
    init {
        mView = view
        mView.setPresenter(this)
    }
    override fun start() {
        getStories()
    }

    private fun getStories() {
        DataManager.getStories(object : ResultCallBack<Post>{
            override fun after() {

            }

            override fun onResponse(response: Post?) {
                mView.addItem(response!!)
            }

            override fun onError(message: String) {

            }

        })
    }

}