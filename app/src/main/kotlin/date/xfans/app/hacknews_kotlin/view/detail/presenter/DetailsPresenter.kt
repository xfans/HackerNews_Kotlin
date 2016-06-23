package date.xfans.app.hacknews_kotlin.view.main.presenter

import date.xfans.app.hacknews_kotlin.date.Post
import date.xfans.app.hacknews_kotlin.date.source.DataManager
import date.xfans.app.hacknews_kotlin.date.source.DataSource
import date.xfans.app.hacknews_kotlin.date.source.ResultCallBack
import date.xfans.app.hacknews_kotlin.view.detail.contract.DetailsContract
import date.xfans.app.hacknews_kotlin.view.main.contract.MainContract

/**
 * Created by zhu on 2016/6/21.
 */
class DetailsPresenter(view : DetailsContract.View) :DetailsContract.Presenter {


    var mView: DetailsContract.View
    init {
        mView = view
        mView.setPresenter(this)
    }
    override fun start() {

    }

    override fun openUrl(url: String?) {
        if(!url.isNullOrEmpty()){
            mView.openWebView(url!!)
        }
    }
}