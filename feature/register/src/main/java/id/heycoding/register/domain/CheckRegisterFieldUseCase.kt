package id.heycoding.register.domain

import id.heycoding.core.base.BaseUseCase
import id.heycoding.core.exception.FieldErrorException
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.register.R
import id.heycoding.register.utils.RegisterFieldConstants.FIELD_BIRTHDATE
import id.heycoding.register.utils.RegisterFieldConstants.FIELD_EMAIL
import id.heycoding.register.utils.RegisterFieldConstants.FIELD_GENDER
import id.heycoding.register.utils.RegisterFieldConstants.FIELD_PASSWORD
import id.heycoding.register.utils.RegisterFieldConstants.FIELD_USERNAME
import id.heycoding.shared.utils.StringUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by Irfan Nawawi on 12/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
typealias CheckFieldRegisterResult = List<Pair<Int, Int>>

class CheckRegisterFieldUseCase(dispatcher: CoroutineDispatcher) :
    BaseUseCase<RegisterUserUseCase.RegisterParam, CheckFieldRegisterResult>(dispatcher) {
    override suspend fun execute(param: RegisterUserUseCase.RegisterParam?): Flow<ViewResource<CheckFieldRegisterResult>> {
        return flow {
            param?.let { p ->
                val result = mutableListOf<Pair<Int, Int>>()
                checkIsBirthdateValid(p.birtdate)?.let {
                    result.add(it)
                }
                checkIsEmailValid(p.email)?.let {
                    result.add(it)
                }
                checkIsGenderValid(p.gender)?.let {
                    result.add(it)
                }
                checkIsPasswordValid(p.password)?.let {
                    result.add(it)
                }
                checkIsUsernameValid(p.username)?.let {
                    result.add(it)
                }
                if (result.isEmpty()) {
                    emit(ViewResource.Success(result))
                } else {
                    emit(ViewResource.Error(FieldErrorException(result)))
                }
            } ?: throw IllegalStateException("Param Required")
        }
    }

    private fun checkIsBirthdateValid(birthdate: String): Pair<Int, Int>? {
        return if (birthdate.isEmpty()) {
            Pair(FIELD_BIRTHDATE, R.string.error_field_empty)
        } else {
            null
        }
    }

    private fun checkIsEmailValid(email: String): Pair<Int, Int>? {
        return if (email.isEmpty()) {
            Pair(FIELD_EMAIL, R.string.error_field_empty)
        } else if (!StringUtils.isEmailValid(email)) {
            Pair(FIELD_EMAIL, R.string.error_field_email_not_valid)
        } else {
            null
        }
    }

    private fun checkIsGenderValid(gender: String): Pair<Int, Int>? {
        return if (gender.isEmpty()) {
            Pair(FIELD_GENDER, R.string.error_field_empty)
        } else {
            null
        }
    }

    private fun checkIsPasswordValid(password: String): Pair<Int, Int>? {
        return if (password.isEmpty()) {
            Pair(FIELD_PASSWORD, R.string.error_field_empty)
        } else if (password.length < 8) {
            Pair(
                FIELD_PASSWORD,
                R.string.error_field_username_length_below_min
            )
        } else {
            null
        }
    }

    private fun checkIsUsernameValid(username: String): Pair<Int, Int>? {
        return if (username.isEmpty()) {
            Pair(FIELD_USERNAME, R.string.error_field_empty)
        } else if (username.length < 8) {
            Pair(
                FIELD_USERNAME,
                R.string.error_field_username_length_below_min
            )
        } else if (username.contains(" ")) {
            Pair(
                FIELD_USERNAME,
                R.string.error_field_username_not_allowed_character
            )
        } else {
            null
        }
    }
}