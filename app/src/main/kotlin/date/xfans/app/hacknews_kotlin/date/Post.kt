package date.xfans.app.hacknews_kotlin.date

import java.util.*

/**
 * Created by zhu on 2016/6/22.
 */
data class Post(
    var id: Long,
    var by: String,
    var time: Long,
    var kids: ArrayList<Long>,
    var url: String,
    var score: Long,
    var title: String,
    var text: String
)