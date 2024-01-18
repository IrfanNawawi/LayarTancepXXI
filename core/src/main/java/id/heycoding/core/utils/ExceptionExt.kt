package id.heycoding.core.utils

import android.content.Context
import id.heycoding.core.exception.ApiErrorException
import id.heycoding.core.exception.NoInternetConnectionException


/**
 * Created by Irfan Nawawi on 03/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
fun Context.getErrorMessageByException(exception: Exception): String {
    return when (exception) {
        is NoInternetConnectionException -> getString(id.heycoding.styling.R.string.message_error_no_internet)
        is ApiErrorException -> exception.message.orEmpty()
        else -> getString(id.heycoding.styling.R.string.message_error_unknown)
    }
}