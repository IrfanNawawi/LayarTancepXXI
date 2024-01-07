package id.heycoding.shared.utils

import android.graphics.Color


/**
 * Created by Irfan Nawawi on 07/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
object ColorUtils {
    fun changeAlpha(currentColor: Int, fraction: Double): Int {
        val red: Int = Color.red(currentColor)
        val green: Int = Color.green(currentColor)
        val blue: Int = Color.blue(currentColor)
        val alpha: Int = (Color.alpha(currentColor) * (fraction)).toInt()
        return Color.argb(alpha, red, green, blue)
    }
}