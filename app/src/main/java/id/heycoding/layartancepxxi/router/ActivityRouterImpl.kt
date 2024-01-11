package id.heycoding.layartancepxxi.router

import android.content.Context
import android.content.Intent
import id.heycoding.home.presentation.ui.HomeActivity
import id.heycoding.login.presentation.LoginActivity
import id.heycoding.shared.router.ActivityRouter


/**
 * Created by Irfan Nawawi on 09/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class ActivityRouterImpl: ActivityRouter {
    override fun loginActivity(context: Context): Intent {
        return Intent(context, LoginActivity::class.java)
    }

    override fun homeActivity(context: Context): Intent {
        return Intent(context, HomeActivity::class.java)
    }
}