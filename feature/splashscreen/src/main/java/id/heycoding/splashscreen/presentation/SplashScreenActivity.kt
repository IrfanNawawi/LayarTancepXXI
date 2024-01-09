package id.heycoding.splashscreen.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import id.heycoding.core.base.BaseActivity
import id.heycoding.shared.router.ActivityRouter
import id.heycoding.shared.utils.ext.subscribe
import id.heycoding.splashscreen.R
import id.heycoding.splashscreen.databinding.ActivitySplashScreenBinding
import org.koin.android.ext.android.inject

class SplashScreenActivity :
    BaseActivity<ActivitySplashScreenBinding, SplashScreenViewModel>(ActivitySplashScreenBinding::inflate) {

    override val viewModel: SplashScreenViewModel by inject()
    val activityRouter: ActivityRouter by inject()

    override fun initView() {
        Log.d("Splash Screen", "Masuk sini")
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
        Toast.makeText(this, "Navigate to Home", Toast.LENGTH_SHORT).show()
        startActivity(activityRouter.homeActivity(this).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
        finish()
    }

    private fun navigateToLogin() {
        Toast.makeText(this, "Navigate to Login", Toast.LENGTH_SHORT).show()
        startActivity(activityRouter.loginActivity(this).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
        finish()
    }

}