package id.heycoding.shared.utils

import android.content.Context
import android.content.Intent
import id.heycoding.styling.ProjectDrawable


/**
 * Created by Irfan Nawawi on 07/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
object CommonUtils {

    fun getWatchListIcon(isUserWatchlist: Boolean): Int {
        return if (isUserWatchlist) ProjectDrawable.ic_check else ProjectDrawable.ic_add
    }

    fun <T, P> shareFilm(context: Context, viewParamTitle: T, viewParamPoster: P) {
        val shareIntent = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Watch this ! $viewParamTitle $viewParamPoster"
            )
            type = "text/plain"
        }, null)
        context.startActivity(shareIntent)
    }
}