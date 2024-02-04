package id.heycoding.shared.utils

import android.text.TextUtils
import android.util.Patterns


/**
 * Created by Irfan Nawawi on 07/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
object StringUtils {
    fun isEmailValid(input: CharSequence?): Boolean {
        return if (TextUtils.isEmpty(input)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(input).matches()
        }
    }
}