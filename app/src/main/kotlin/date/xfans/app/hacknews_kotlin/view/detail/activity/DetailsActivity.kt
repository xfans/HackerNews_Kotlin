package date.xfans.app.hacknews_kotlin.view.detail.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import date.xfans.app.hacknews_kotlin.R
import date.xfans.app.hacknews_kotlin.base.addFragmentToActivity
import date.xfans.app.hacknews_kotlin.base.toast
import date.xfans.app.hacknews_kotlin.view.main.fragment.DetailsFragment
import date.xfans.app.hacknews_kotlin.view.main.fragment.MainFragment
import date.xfans.app.hacknews_kotlin.view.main.presenter.DetailsPresenter
import date.xfans.app.hacknews_kotlin.view.main.presenter.MainPresenter

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        var url = intent.getStringExtra("url")
        title = intent.getStringExtra("title") ?: ""
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        var detailsFragment: DetailsFragment? = supportFragmentManager.findFragmentById(R.id.contentFrame) as DetailsFragment?
        if (detailsFragment == null) {
            detailsFragment = DetailsFragment.newInstance(url)
            addFragmentToActivity(detailsFragment,R.id.contentFrame)
        }
        DetailsPresenter(detailsFragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
