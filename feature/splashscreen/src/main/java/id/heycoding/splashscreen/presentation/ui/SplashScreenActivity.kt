package id.heycoding.splashscreen.presentation.ui

import android.content.Intent
import android.widget.Toast
import id.heycoding.core.base.BaseActivity
import id.heycoding.shared.router.ActivityRouter
import id.heycoding.shared.utils.ext.subscribe
import id.heycoding.splashscreen.databinding.ActivitySplashScreenBinding
import org.koin.android.ext.android.inject

class SplashScreenActivity :
    BaseActivity<ActivitySplashScreenBinding, SplashScreenViewModel>(ActivitySplashScreenBinding::inflate) {
    override val viewModel: SplashScreenViewModel by inject()
    private val activityRouter: ActivityRouter by inject()

    override fun initView() {
        viewModel.syncUser()
    }

    override fun observeData() {
        viewModel.syncResult.observe(this) {
            it.subscribe(
                doOnSuccess = { response ->
                    if (response.payload?.first == true) {
                        navigateToHome()
                    } else {
                        navigateToLogin()
                    }
                },
                doOnError = { error ->
                    Toast.makeText(this, error.exception?.message, Toast.LENGTH_SHORT).show()
                }
            )
        }
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