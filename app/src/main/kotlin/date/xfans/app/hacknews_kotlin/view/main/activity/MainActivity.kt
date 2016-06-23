package date.xfans.app.hacknews_kotlin.view.main.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import date.xfans.app.hacknews_kotlin.R
import date.xfans.app.hacknews_kotlin.base.addFragmentToActivity
import date.xfans.app.hacknews_kotlin.view.main.fragment.MainFragment
import date.xfans.app.hacknews_kotlin.view.main.presenter.MainPresenter

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        var mainFragment1: MainFragment? = supportFragmentManager.findFragmentById(R.id.contentFrame) as MainFragment?
        if (mainFragment1 == null) {
            mainFragment1 = MainFragment.newInstance()
            addFragmentToActivity(mainFragment1,R.id.contentFrame)
        }
        MainPresenter(mainFragment1)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
                R.id.action_github -> {
                    var intent = Intent()
                    intent.setAction("android.intent.action.VIEW")
                    var content_url = Uri.parse("https://github.com/xfans/HackerNews_Kotlin")
                    intent.setData(content_url)
                    startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
