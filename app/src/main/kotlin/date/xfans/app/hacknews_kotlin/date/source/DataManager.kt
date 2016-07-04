package date.xfans.app.hacknews_kotlin.date.source

import android.util.Log
import date.xfans.app.hacknews_kotlin.base.App
import date.xfans.app.hacknews_kotlin.base.logD
import date.xfans.app.hacknews_kotlin.date.Post
import date.xfans.app.hacknews_kotlin.date.source.remote.HackerNewsService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.IOException
import java.util.*
import kotlin.properties.Delegates

/**
 * Created by zhu on 2016/6/21.
 */
object DataManager: DataSource{

    val posts = HashMap<Long,Post>()
    val failedList = ArrayList<Int>()
    val BASE = "https://hacker-news.firebaseio.com/"
    var hackerNewsService:HackerNewsService
    init {

        val interceptor = Interceptor { chain ->
            val newRequest = chain.request().newBuilder().build()
            chain.proceed(newRequest)
        }
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.level = HttpLoggingInterceptor.Level.BODY
        val defaultHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(logging).build()
        val mRetrofit = Retrofit.Builder().baseUrl(BASE).client(defaultHttpClient).addConverterFactory(GsonConverterFactory.create()).build()
        hackerNewsService = mRetrofit.create(HackerNewsService::class.java);
    }

    override fun getStories(callback : ResultCallBack<Post>) {
        hackerNewsService.getTopStories().enqueue(object : Callback<List<Long>> {
            override fun onResponse(call: Call<List<Long>>?, response: retrofit2.Response<List<Long>>?) {
                var list: List<Long>? = response?.body()?.take(20) //test get 20
                index = 0
                posts.clear()
                failedList.clear()
                list?.forEachIndexed { i, v
                    ->
                    hackerNewsService.getStoryItem(v.toString()).enqueue(object : Callback<Post>{
                    override fun onFailure(call: Call<Post>?, t: Throwable?) {
                        failedList.add(i)
                    }

                    override fun onResponse(call: Call<Post>, response: retrofit2.Response<Post>) {
                        posts.put(v,response.body())
                        logD(response.body().toString())
                        sendResponse(list, callback);
                    }

                }) }

            }

            override fun onFailure(call: Call<List<Long>>?, t: Throwable?) {
                var msg = t?.message
                if(msg == null) {
                    msg = "error"
                }
                callback.onError(msg)
            }

        })
    }

    /**
     * fix Response sort
     */
    var index = 0
    fun sendResponse(list : List<Long>, callback : ResultCallBack<Post>){
        synchronized(hackerNewsService){
            if(failedList.contains(index)){
                index ++
            }
            if(index < list.size) {
                var post = posts.get(list[index])
                if (post != null) {
                    callback.onResponse(post)
                    index++
                    sendResponse(list, callback);
                }
            }
        }

    }
}