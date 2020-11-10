package na.severinchik.iba_kotlin_scnd_lesson

import android.content.Context

fun Context.dpTopx(dp: Int): Float {
    return dp.toFloat() * this.resources.displayMetrics.density
}