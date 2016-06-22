package date.xfans.app.hacknews_kotlin.date.source

/**
 * Created by zhu on 2016/6/21.
 */
interface ResultCallBack<T>{
    fun after()
    fun onResponse(response: T?)
    fun onError(message: String)
}