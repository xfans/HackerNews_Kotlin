package date.xfans.app.hacknews_kotlin.date.source

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
import kotlin.properties.Delegates

/**
 * Created by zhu on 2016/6/21.
 */
object DataManager: DataSource{

    val BASE = "https://hacker-news.firebaseio.com/"
    var hackerNewsService:HackerNewsService;
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
                var list: List<Long>? = response?.body()

                list?.forEachIndexed { i, v
                    -> hackerNewsService.getStoryItem(v.toString()).enqueue(object : Callback<Post>{
                    override fun onFailure(call: Call<Post>?, t: Throwable?) {

                    }

                    override fun onResponse(call: Call<Post>?, response: retrofit2.Response<Post>?) {
                        logD(response?.body().toString())
                        callback.onResponse(response?.body())
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



}