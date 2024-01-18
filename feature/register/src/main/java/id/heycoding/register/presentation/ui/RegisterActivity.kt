package id.heycoding.register.presentation.ui

import android.content.Intent
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputLayout
import id.heycoding.core.base.BaseActivity
import id.heycoding.core.exception.FieldErrorException
import id.heycoding.register.databinding.ActivityRegisterBinding
import id.heycoding.register.utils.RegisterFieldConstants
import id.heycoding.shared.router.ActivityRouter
import id.heycoding.shared.utils.DateUtils.showDatePickerDialog
import id.heycoding.shared.utils.GenderUtils
import id.heycoding.shared.utils.ext.subscribe
import org.koin.android.ext.android.inject

class RegisterActivity :
    BaseActivity<ActivityRegisterBinding, RegisterViewModel>(ActivityRegisterBinding::inflate) {
    override val viewModel: RegisterViewModel by inject()
    private val activityRouter: ActivityRouter by inject()

    override fun initView() {
        binding.etBirthdate.setOnClickListener {
            showDatePickerDialog {
                binding.etBirthdate.setText(it)
            }
        }
        binding.tvGender.apply {
            setAdapter(GenderUtils.createGenderListAdapter(this@RegisterActivity))
        }
        binding.btnRegister.setOnClickListener {
            viewModel.registerUser(
                email = binding.etEmail.text?.trim().toString(),
                password = binding.etPassword.text?.trim().toString(),
                username = binding.etUsername.text?.trim().toString(),
                birthdate = binding.etBirthdate.text?.trim().toString(),
                gender = binding.tvGender.text?.trim().toString()
            )
        }
        binding.btnAlreadyHaveAccount.setOnClickListener {
            startActivity(activityRouter.loginActivity(this))
        }
    }

    override fun observeData() {
        viewModel.registerResult.observe(this) { registerResult ->
            resetField()
            registerResult.subscribe(
                doOnSuccess = {
                    showLoading(false)
                    navigateToHome()
                },
                doOnError = {
                    showLoading(false)
                    if (registerResult.exception is FieldErrorException) {
                        handleFieldError(registerResult.exception as FieldErrorException)
                    } else {
                        registerResult.exception?.let { e -> showError(true, e) }
                    }
                },
                doOnLoading = {
                    showLoading(true)
                }
            )
        }
    }

    private fun handleFieldError(exception: FieldErrorException) {
        exception.errorFields.forEach { errorField ->
            if (errorField.first == RegisterFieldConstants.FIELD_BIRTHDATE) {
                binding.etBirthdate.error = getString(errorField.second)
            }
            if (errorField.first == RegisterFieldConstants.FIELD_EMAIL) {
                binding.etEmail.error = getString(errorField.second)
            }
            if (errorField.first == RegisterFieldConstants.FIELD_GENDER) {
                binding.tvGender.error = getString(errorField.second)
            }
            if (errorField.first == RegisterFieldConstants.FIELD_USERNAME) {
                binding.etUsername.error = getString(errorField.second)
            }
            if (errorField.first == RegisterFieldConstants.FIELD_PASSWORD) {
                binding.tilPassword.endIconMode = TextInputLayout.END_ICON_NONE
                binding.etPassword.error = getString(errorField.second)
            }
        }
    }

    private fun showLoading(isShowLoading: Boolean) {
        binding.pbLoading.isVisible = isShowLoading
    }

    private fun resetField() {
        binding.tilBirthdate.isErrorEnabled = false
        binding.tilEmail.isErrorEnabled = false
        binding.tilGender.isErrorEnabled = false
        binding.tilPassword.isErrorEnabled = false
        binding.tilPassword.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
        binding.tilUsername.isErrorEnabled = false
    }

    private fun navigateToHome() {
        startActivity(activityRouter.homeActivity(this).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
        finish()
    }
}