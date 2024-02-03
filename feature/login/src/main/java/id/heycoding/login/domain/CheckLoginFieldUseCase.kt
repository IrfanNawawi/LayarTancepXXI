package id.heycoding.login.domain

import id.heycoding.core.base.BaseUseCase
import id.heycoding.core.exception.FieldErrorException
import id.heycoding.core.wrapper.ViewResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import id.heycoding.login.R
import id.heycoding.login.utils.LoginFieldConstants.FIELD_EMAIL
import id.heycoding.login.utils.LoginFieldConstants.FIELD_PASSWORD
import id.heycoding.shared.utils.StringUtils
import id.heycoding.styling.ProjectString


/**
 * Created by Irfan Nawawi on 10/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
typealias CheckFieldLoginResult = List<Pair<Int, Int>>

class CheckLoginFieldUseCase(dispatcher: CoroutineDispatcher) :
    BaseUseCase<LoginUserUseCase.LoginParam, CheckFieldLoginResult>(dispatcher) {
    override suspend fun execute(loginParam: LoginUserUseCase.LoginParam?): Flow<ViewResource<CheckFieldLoginResult>> {
        return flow {
            loginParam?.let { p ->
                val result = mutableListOf<Pair<Int, Int>>()
                checkIsEmailValid(p.email)?.let {
                    result.add(it)
                }
                checkIsPasswordValid(p.password)?.let {
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

    private fun checkIsPasswordValid(password: String): Pair<Int, Int>? {
        return if (password.isEmpty()) {
            Pair(FIELD_PASSWORD, R.string.error_field_password)
        } else {
            null
        }
    }

    private fun checkIsEmailValid(email: String): Pair<Int, Int>? {
        return if (email.isEmpty()) {
            Pair(FIELD_EMAIL, R.string.error_field_email)
        } else if (!StringUtils.isEmailValid(email)) {
            Pair(FIELD_EMAIL, R.string.error_field_email_not_valid)
        } else {
            null
        }
    }
}