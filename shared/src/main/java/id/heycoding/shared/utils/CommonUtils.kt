package id.heycoding.shared.utils

import android.content.Context
import android.content.Intent
import id.heycoding.shared.data.model.viewparam.MovieViewParam
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

    fun shareFilm(context: Context, movieViewParam: MovieViewParam) {
        val shareIntent = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Watch this ! ${movieViewParam.title} ${movieViewParam.posterUrl}")
            type = "text/plain"
        }, null)
        context.startActivity(shareIntent)
    }
}