package id.heycoding.login.presentation.ui

import android.content.Intent
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputLayout
import id.heycoding.core.base.BaseActivity
import id.heycoding.core.exception.FieldErrorException
import id.heycoding.login.databinding.ActivityLoginBinding
import id.heycoding.shared.router.ActivityRouter
import id.heycoding.shared.utils.ext.listen
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity :
    BaseActivity<ActivityLoginBinding, LoginViewModel>(ActivityLoginBinding::inflate) {
    override val viewModel: LoginViewModel by viewModel()
    private val activityRouter: ActivityRouter by inject()

    override fun initView() {
        binding.btnLogin.setOnClickListener {
            navigateToHome()
//            viewModel.loginUser(
//                binding.etEmail.text?.trim().toString(),
//                binding.etPassword.text?.trim().toString(),
//            )
        }
        binding.btnAlreadyHaveAccount.setOnClickListener {
            startActivity(activityRouter.registerActivity(this))
        }
        binding.etPassword.listen(beforeTextChanged = {
            binding.tilPassword.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
        })
    }

    override fun observeData() {
//        viewModel.loginResult.observe(this) { loginResult ->
//            resetField()
//            loginResult.subscribe(
//                doOnSuccess = {
//                    showLoading(false)
//                    navigateToHome()
//                },
//                doOnError = {
//                    showLoading(false)
//                    if (loginResult.exception is FieldErrorException) {
//                        handleFieldError(loginResult.exception as FieldErrorException)
//                    } else {
//                        loginResult.exception?.let { e -> showError(true, e) }
//                    }
//                },
//                doOnLoading = {
//                    showLoading(true)
//                }
//            )
//        }
    }

    private fun handleFieldError(exception: FieldErrorException) {
//        exception.errorFields.forEach { errorField ->
//            if (errorField.first == LoginFieldConstants.FIELD_EMAIL) {
//                binding.etEmail.error = getString(errorField.second)
//            }
//            if (errorField.first == LoginFieldConstants.FIELD_PASSWORD) {
//                binding.tilPassword.endIconMode = TextInputLayout.END_ICON_NONE
//                binding.etPassword.error = getString(errorField.second)
//            }
//        }
    }

    private fun showLoading(isShowLoading: Boolean) {
        binding.pbLoading.isVisible = isShowLoading
    }

    private fun resetField() {
        binding.tilEmail.isErrorEnabled = false
        binding.tilPassword.isErrorEnabled = false
        binding.tilPassword.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
    }

    private fun navigateToHome() {
        startActivity(activityRouter.homeActivity(this).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
        finish()
    }
}