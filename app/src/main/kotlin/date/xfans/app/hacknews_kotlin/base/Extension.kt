package date.xfans.app.hacknews_kotlin.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
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

public fun Fragment.toast(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}

public fun Fragment.toast(resId: Int) {
    Toast.makeText(activity, resId, Toast.LENGTH_SHORT).show()
}

fun Any.logD(text: String){
    Log.d(this.javaClass.simpleName,text);
}

fun Any.logE(text: String){
    Log.e(this.javaClass.simpleName,text);
}

inline public fun <reified T: Activity> Activity.start(extras: Bundle) {
    this.startActivity(intent<T>(extras))
}

inline public fun <reified T : Activity> Activity.start() {
    startActivity(intent<T>())
}

inline public fun <reified T: Context> Context.intent(): Intent {
    return Intent(this, T::class.java)
}

inline public fun <reified T: Context> Context.intent(flags: Int): Intent {
    val intent = intent<T>()
    intent.setFlags(flags)
    return intent
}

inline public fun <reified T: Context> Context.intent(extras: Bundle): Intent {
    return intent<T>(extras, 0)
}

inline public fun <reified T: Context> Context.intent(extras: Bundle, flags: Int): Intent {
    val intent = intent<T>(flags)
    intent.putExtras(extras)
    return intent
}

fun AppCompatActivity.addFragmentToActivity(fragment: Fragment, frameId: Int) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.add(frameId, fragment)
    transaction.commit()
}

fun AppCompatActivity.openBrowser(url: String) {
    var intent = Intent()
    intent.setAction("android.intent.action.VIEW")
    var content_url = Uri.parse(url)
    intent.setData(content_url)
    startActivity(intent)
}
