package date.xfans.app.hacknews_kotlin.view.main.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import date.xfans.app.hacknews_kotlin.R
import date.xfans.app.hacknews_kotlin.base.App
import date.xfans.app.hacknews_kotlin.view.main.contract.MainContract
import date.xfans.app.hacknews_kotlin.view.main.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View{
    var mPresenter: MainContract.Presenter?

    init {
        mPresenter = null
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.mPresenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.start()
    }
}
