package date.xfans.app.hacknews_kotlin.view.main.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import date.xfans.app.hacknews_kotlin.R
import date.xfans.app.hacknews_kotlin.view.detail.contract.DetailsContract
import kotlinx.android.synthetic.main.fragment_details.*
import kotlin.properties.Delegates

/**
 * Created by zhu on 2016/6/22.
 */
class DetailsFragment : Fragment(), DetailsContract.View{

    var mPresenter: DetailsContract.Presenter by Delegates.notNull()

    companion object {
        fun newInstance(url: String): DetailsFragment{
            val arguments = Bundle()
            arguments.putString("url", url)
            val fragment = DetailsFragment()
            fragment.setArguments(arguments)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_details, container, false)
        return root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        var settings = webView.getSettings()
        settings.javaScriptEnabled = true
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        settings.loadWithOverviewMode = true
        webView.setWebViewClient(object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return super.shouldOverrideUrlLoading(view, url)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                mPresenter.openUrlFinished()
            }
        })
        var url = arguments.getString("url")
        mPresenter.openUrl(url)
        swipeRefreshLayout.setOnRefreshListener {
            mPresenter.openUrl(url)
        }
    }

    override fun onResume() {
        super.onResume()

    }
    override fun setPresenter(presenter: DetailsContract.Presenter) {
        this.mPresenter = presenter
    }
    override fun openWebView(url: String) {
        webView.loadUrl(url)
    }

    override fun showLoading(flag: Boolean) {
        if(swipeRefreshLayout.isRefreshing == flag)return
        swipeRefreshLayout.post(Runnable {
            swipeRefreshLayout.setRefreshing(flag)
        })
    }
}