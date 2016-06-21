package date.xfans.app.hacknews_kotlin.view.main.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import date.xfans.app.hacknews_kotlin.R
import date.xfans.app.hacknews_kotlin.view.main.contract.MainContract

class MainActivity : AppCompatActivity(), MainContract.View{
    var mPesenter: MainContract.Presenter?

    init {
        mPesenter = null
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.mPesenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
