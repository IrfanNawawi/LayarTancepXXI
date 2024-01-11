package id.heycoding.login.domain

import id.heycoding.core.base.BaseUseCase
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.login.data.repository.LoginRepository
import id.heycoding.shared.data.model.mapper.UserMapper
import id.heycoding.shared.data.model.viewparam.UserViewParam
import id.heycoding.shared.domain.SaveAuthDataUseCase
import id.heycoding.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow


/**
 * Created by Irfan Nawawi on 10/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class LoginUserUseCase(
    private val checkLoginFieldUseCase: CheckLoginFieldUseCase,
    private val saveAuthDataUseCase: SaveAuthDataUseCase,
    private val repository: LoginRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<LoginUserUseCase.Param, UserViewParam?>(dispatcher) {
    override suspend fun execute(param: Param?): Flow<ViewResource<UserViewParam?>> {
        return flow {
            param?.let {
                emit(ViewResource.Loading())
                checkLoginFieldUseCase(param).first().suspendSubscribe(
                    doOnSuccess = { _ ->
                        repository.loginUser(param.email, param.password).collect { loginResult ->
                            loginResult.suspendSubscribe(
                                doOnSuccess = {
                                    val result = loginResult.payload?.data
                                    val token = result?.token
                                    val user = result?.user
                                    if (!token.isNullOrEmpty() && user != null) {
                                        saveAuthDataUseCase(
                                            SaveAuthDataUseCase.Param(true, token, user)
                                        ).collect {
                                            it.suspendSubscribe(
                                                doOnSuccess = {
                                                    emit(ViewResource.Success(UserMapper.toViewParam(user)))
                                                },
                                                doOnError = { error ->
                                                    emit(ViewResource.Error(error.exception))
                                                }
                                            )
                                        }
                                    }
                                },doOnError = { error ->
                                    emit(ViewResource.Error(error.exception))
                                })
                        }
                    },
                    doOnError = { error ->
                        emit(ViewResource.Error(error.exception))
                    }
                )
            } ?: throw IllegalStateException("Param Required")
        }

    }

    data class Param(val email: String, val password: String)
}