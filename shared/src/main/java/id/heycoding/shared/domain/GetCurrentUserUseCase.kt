package id.heycoding.shared.domain

import id.heycoding.core.base.BaseUseCase
import id.heycoding.core.wrapper.DataResource
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.shared.data.model.mapper.UserMapper
import id.heycoding.shared.data.model.viewparam.UserViewParam
import id.heycoding.shared.data.repository.UserPreferenceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


/**
 * Created by Irfan Nawawi on 17/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class GetCurrentUserUseCase(
    private val repository: UserPreferenceRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, UserViewParam>(dispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<UserViewParam>> =
        repository.getCurrentUser().map {
            when (it) {
                is DataResource.Success -> {
                    ViewResource.Success(UserMapper.toViewParam(it.payload))
                }

                is DataResource.Error -> {
                    ViewResource.Error(it.exception)
                }
            }
        }
}