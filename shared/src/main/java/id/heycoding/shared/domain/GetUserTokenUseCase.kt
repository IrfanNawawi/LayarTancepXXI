package id.heycoding.shared.domain

import id.heycoding.core.base.BaseUseCase
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.shared.data.repository.UserPreferenceRepository
import id.heycoding.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


/**
 * Created by Irfan Nawawi on 06/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class GetUserTokenUseCase(
    private val repository: UserPreferenceRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, String>(dispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<String>> {
        return flow {
            repository.getUserToken().collect {
                it.suspendSubscribe(doOnSuccess = { result ->
                    emit(ViewResource.Success(result.payload.orEmpty()))
                }, doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                })
            }
        }
    }
}