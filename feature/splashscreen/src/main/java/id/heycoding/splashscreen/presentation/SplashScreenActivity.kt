package id.heycoding.splashscreen.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import id.heycoding.core.base.BaseActivity
import id.heycoding.shared.utils.ext.subscribe
import id.heycoding.splashscreen.R
import id.heycoding.splashscreen.databinding.ActivitySplashScreenBinding
import org.koin.android.ext.android.inject

class SplashScreenActivity :
    BaseActivity<ActivitySplashScreenBinding, SplashScreenViewModel>(ActivitySplashScreenBinding::inflate) {
    override val viewModel: SplashScreenViewModel by inject()

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
        Toast.makeText(this, "Navigate to home", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToLogin() {
        Toast.makeText(this, "Navigate to login", Toast.LENGTH_SHORT).show()
    }

}