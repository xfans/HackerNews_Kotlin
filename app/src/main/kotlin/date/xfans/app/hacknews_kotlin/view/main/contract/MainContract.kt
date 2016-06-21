package date.xfans.app.hacknews_kotlin.view.main.contract

import date.xfans.app.hacknews_kotlin.base.BasePresenter
import date.xfans.app.hacknews_kotlin.base.BaseView

/**
 * Created by zhu on 2016/6/21.
 */
interface MainContract{
    interface View: BaseView<Presenter>{

    }
    interface Presenter: BasePresenter{

    }
}