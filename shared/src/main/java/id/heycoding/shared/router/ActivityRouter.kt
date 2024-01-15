package id.heycoding.shared.router

import android.content.Context
import android.content.Intent


/**
 * Created by Irfan Nawawi on 09/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface ActivityRouter {
    fun loginActivity(context: Context): Intent
    fun homeActivity(context: Context): Intent
    fun registerActivity(context: Context): Intent
}