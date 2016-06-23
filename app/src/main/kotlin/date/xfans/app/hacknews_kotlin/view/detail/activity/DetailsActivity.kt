package date.xfans.app.hacknews_kotlin.view.detail.activity

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import date.xfans.app.hacknews_kotlin.R
import date.xfans.app.hacknews_kotlin.base.addFragmentToActivity
import date.xfans.app.hacknews_kotlin.base.openBrowser
import date.xfans.app.hacknews_kotlin.base.toast
import date.xfans.app.hacknews_kotlin.view.main.fragment.DetailsFragment
import date.xfans.app.hacknews_kotlin.view.main.fragment.MainFragment
import date.xfans.app.hacknews_kotlin.view.main.presenter.DetailsPresenter
import date.xfans.app.hacknews_kotlin.view.main.presenter.MainPresenter

class DetailsActivity : AppCompatActivity() {
    var mUrl:String?
    init {
        mUrl = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        mUrl = intent.getStringExtra("url")
        if(mUrl == null)finish()
        title = intent.getStringExtra("title") ?: ""
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        var detailsFragment: DetailsFragment? = supportFragmentManager.findFragmentById(R.id.contentFrame) as DetailsFragment?
        if (detailsFragment == null) {
            detailsFragment = DetailsFragment.newInstance(mUrl!!)
            addFragmentToActivity(detailsFragment,R.id.contentFrame)
        }
        DetailsPresenter(detailsFragment)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.action_browser ->{
                openBrowser(mUrl!!)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
