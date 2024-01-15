package id.heycoding.register.domain

import id.heycoding.core.base.BaseUseCase
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.register.data.repository.RegisterRepository
import id.heycoding.shared.data.model.mapper.UserMapper
import id.heycoding.shared.data.model.viewparam.UserViewParam
import id.heycoding.shared.domain.SaveAuthDataUseCase
import id.heycoding.shared.utils.GenderUtils
import id.heycoding.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow


/**
 * Created by Irfan Nawawi on 12/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class RegisterUserUseCase(
    private val checkRegisterFieldUseCase: CheckRegisterFieldUseCase,
    private val saveAuthDataUseCase: SaveAuthDataUseCase,
    private val repository: RegisterRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<RegisterUserUseCase.RegisterParam, UserViewParam?>(dispatcher){
    override suspend fun execute(param: RegisterParam?): Flow<ViewResource<UserViewParam?>> {
        return flow {
            mutateParam(param)?.let { p ->
                emit(ViewResource.Loading())
                checkRegisterFieldUseCase(p).first().suspendSubscribe(
                    doOnSuccess = { _ ->
                        repository.registerUser(p.birtdate, p.email, p.gender, p.password, p.username).collect {
                            registerResult ->
                            registerResult.suspendSubscribe(
                                doOnSuccess = {
                                    val result = registerResult.payload?.data
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
                                }, doOnError = { error ->
                                    emit(ViewResource.Error(error.exception))
                                })
                        }
                    },
                    doOnError = { error ->
                        emit(ViewResource.Error(error.exception))
                    }
                )
            }
        }
    }

    private fun mutateParam(param: RegisterParam?) = param?.apply {
        this.gender = GenderUtils.parseGender(this.gender)
    }

    data class RegisterParam(
        val birtdate: String,
        val email: String,
        var gender: String,
        val password: String,
        val username: String
    )
}