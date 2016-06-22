package date.xfans.app.hacknews_kotlin.date.source.remote

import date.xfans.app.hacknews_kotlin.date.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by zhu on 2016/6/22.
 */
interface  HackerNewsService{

    @GET("/v0/topstories.json")
    fun getTopStories(): Call<List<Long>>

    @GET("/v0/item/{itemId}.json")
    fun getStoryItem(@Path("itemId") itemId: String): Call<Post>
}