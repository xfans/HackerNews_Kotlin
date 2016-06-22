package date.xfans.app.hacknews_kotlin.view.main.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import date.xfans.app.hacknews_kotlin.R
import date.xfans.app.hacknews_kotlin.base.App
import date.xfans.app.hacknews_kotlin.date.Post
import date.xfans.app.hacknews_kotlin.view.DividerItemDecoration
import date.xfans.app.hacknews_kotlin.view.main.adapter.MainAdapter
import date.xfans.app.hacknews_kotlin.view.main.contract.MainContract
import date.xfans.app.hacknews_kotlin.view.main.fragment.MainFragment
import date.xfans.app.hacknews_kotlin.view.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mainFragment1: MainFragment? = supportFragmentManager.findFragmentById(R.id.contentFrame) as MainFragment?
        if (mainFragment1 == null) {
            // Create the fragment
            mainFragment1 = MainFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.contentFrame,mainFragment1)
            transaction.commit()
        }
        MainPresenter(mainFragment1)
    }

}
