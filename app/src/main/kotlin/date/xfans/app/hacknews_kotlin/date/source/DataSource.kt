package date.xfans.app.hacknews_kotlin.date.source

import date.xfans.app.hacknews_kotlin.date.Post

/**
 * Created by zhu on 2016/6/21.
 */
interface DataSource{
    fun getStories(callback : ResultCallBack<Post>)
}