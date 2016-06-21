package date.xfans.app.hacknews_kotlin.base

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast

/**
 * Created by zhu on 2016/6/21.
 */
fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, text, duration).show();
}

fun Context.toast(resId: Int, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, resId, duration).show();
}

fun Any.logD(text: String){
    Log.d(this.javaClass.simpleName,text);
}

fun Any.logE(text: String){
    Log.e(this.javaClass.simpleName,text);
}