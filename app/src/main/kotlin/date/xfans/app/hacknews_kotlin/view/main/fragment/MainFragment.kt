package date.xfans.app.hacknews_kotlin.view.main.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import date.xfans.app.hacknews_kotlin.R
import date.xfans.app.hacknews_kotlin.base.start
import date.xfans.app.hacknews_kotlin.date.Post
import date.xfans.app.hacknews_kotlin.view.DividerItemDecoration
import date.xfans.app.hacknews_kotlin.view.detail.activity.DetailsActivity
import date.xfans.app.hacknews_kotlin.view.main.adapter.MainAdapter
import date.xfans.app.hacknews_kotlin.view.main.contract.MainContract
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*
import kotlin.properties.Delegates

/**
 * Created by zhu on 2016/6/22.
 */
class MainFragment : Fragment(), MainContract.View {


    var mPresenter: MainContract.Presenter by Delegates.notNull()
    var mAdapter: MainAdapter?
    var mStories = ArrayList<Post>()

    companion object {
        fun newInstance() = MainFragment()
    }

    init {
        mAdapter = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        return root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        mAdapter = MainAdapter(mStories) {
            mPresenter.openDetails(it)
        }

        recyclerView1.layoutManager = LinearLayoutManager(activity)
        recyclerView1.adapter = mAdapter
        recyclerView1.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL_LIST));
        mPresenter.start()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.mPresenter = presenter
    }

    override fun addItem(post: Post) {
        mStories.add(post)
        mAdapter?.notifyItemChanged(mStories.size - 1)
    }

    override fun showTaskDetailsUi(title: String, url: String) {
        var ex = Bundle()
        ex.putString("url", url)
        ex.putString("title", title)
        activity.start<DetailsActivity>(ex)
    }

}