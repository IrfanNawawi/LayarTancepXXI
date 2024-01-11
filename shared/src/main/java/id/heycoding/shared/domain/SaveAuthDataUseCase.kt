package id.heycoding.shared.domain

import id.heycoding.core.base.BaseUseCase
import id.heycoding.core.wrapper.DataResource
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.shared.data.model.response.UserResponse
import id.heycoding.shared.data.repository.UserPreferenceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow


/**
 * Created by Irfan Nawawi on 10/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class SaveAuthDataUseCase(
    private val repository: UserPreferenceRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<SaveAuthDataUseCase.Param, Boolean>(dispatcher) {
    override suspend fun execute(param: Param?): Flow<ViewResource<Boolean>> = flow {
        param?.let {
            val saveLoginStatus = repository.updateUserLoginStatus(param.isLoggedIn).first()
            val saveToken = repository.updateUserToken(param.authToken).first()
            val saveUser = repository.setCurrentUser(param.user).first()

            if (saveUser is DataResource.Success && saveToken is DataResource.Success && saveLoginStatus is DataResource.Success) {
                emit(ViewResource.Success(true))
            } else {
                emit(ViewResource.Error(IllegalStateException("Failed to save local data")))
            }
        }
    }

    data class Param(val isLoggedIn: Boolean, val authToken: String, val user: UserResponse)
}