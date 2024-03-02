package id.heycoding.shared.domain

import id.heycoding.core.base.BaseUseCase
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.shared.data.local.model.mapper.MovieWatchlistMapper
import id.heycoding.shared.data.remote.model.viewparam.MovieViewParam
import id.heycoding.shared.data.repository.SharedLocalRepository
import id.heycoding.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow


/**
 * Created by Irfan Nawawi on 01/03/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class AddWatchlistMovieUseCase(
    private val repository: SharedLocalRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<AddWatchlistMovieUseCase.Param, Boolean>(dispatcher) {

    override suspend fun execute(param: Param?): Flow<ViewResource<Boolean>> {
        return flow {
            param?.let {
                repository.addWatchlist(MovieWatchlistMapper.toDataObject(param.movieViewParam))
                    .collect {
                        it.suspendSubscribe(
                            doOnSuccess = {
                                emit(ViewResource.Success(true))

                            }, doOnError = {
                                emit(ViewResource.Error(IllegalStateException("Failed when save local data")))
                            })
                    }
            }
        }
    }

    data class Param(val movieViewParam: MovieViewParam)
}