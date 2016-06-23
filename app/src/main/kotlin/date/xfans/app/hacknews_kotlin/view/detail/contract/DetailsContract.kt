package date.xfans.app.hacknews_kotlin.view.detail.contract

import date.xfans.app.hacknews_kotlin.base.BasePresenter
import date.xfans.app.hacknews_kotlin.base.BaseView

/**
 * Created by zhu on 2016/6/23.
 */
interface DetailsContract{
    interface View: BaseView<Presenter> {
        fun openWebView(url: String)
        fun showLoading(flag: Boolean)
    }
    interface Presenter: BasePresenter {
        fun openUrl(url: String?)
        fun openUrlFinished()
    }
}