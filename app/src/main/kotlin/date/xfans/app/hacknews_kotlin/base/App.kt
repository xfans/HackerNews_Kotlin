package date.xfans.app.hacknews_kotlin.base

import android.app.Application
import kotlin.properties.Delegates

/**
 * Created by zhu on 2016/6/21.
 */
class App: Application() {
    companion object{
        var instance: App by Delegates.notNull()
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        logD("App onCreate")
    }
}