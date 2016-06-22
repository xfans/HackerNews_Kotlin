package date.xfans.app.hacknews_kotlin.view.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import date.xfans.app.hacknews_kotlin.R
import date.xfans.app.hacknews_kotlin.base.toast
import date.xfans.app.hacknews_kotlin.date.Post
import kotlinx.android.synthetic.main.item_main.*
import kotlinx.android.synthetic.main.item_main.view.*

/**
 * Created by zhu on 2016/6/22.
 */
class MainAdapter( lists:List<Post>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var mLists: List<Post>

    init {
        mLists = lists
    }

    override fun getItemCount(): Int {
        return mLists.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = mLists[position]

        holder.itemView.title.text = post.title

        holder.itemView.setOnClickListener {

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}