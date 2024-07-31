package id.heycoding.splashscreen.presentation.ui

import android.content.Intent
import android.os.Handler
import android.view.View
import android.widget.Toast
import id.heycoding.core.base.BaseActivity
import id.heycoding.shared.router.ActivityRouter
import id.heycoding.shared.utils.ext.subscribe
import id.heycoding.splashscreen.databinding.ActivitySplashScreenBinding
import org.koin.android.ext.android.inject

class SplashScreenActivity :
    BaseActivity<ActivitySplashScreenBinding, Nothing>(ActivitySplashScreenBinding::inflate) {
    private val activityRouter: ActivityRouter by inject()
    override val viewModel: Nothing
        get() = TODO("Not yet implemented")

    override fun initView() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        Handler().postDelayed({
            navigateToLogin()
        }, 5000)
    }

    private fun navigateToHome() {
        startActivity(activityRouter.homeActivity(this).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
        finish()
    }

    private fun navigateToLogin() {
        startActivity(activityRouter.loginActivity(this).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
        finish()
    }

}