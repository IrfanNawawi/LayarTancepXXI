package id.heycoding.home.domain

import id.heycoding.core.base.BaseUseCase
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.home.data.repository.HomeRepository
import id.heycoding.home.presentation.viewparam.homeitem.HomeUiItem
import id.heycoding.shared.data.model.mapper.MovieMapper
import id.heycoding.shared.data.model.viewparam.MovieViewParam
import id.heycoding.shared.utils.ext.suspendSubscribe
import id.heycoding.shared.utils.mapper.ListMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by Irfan Nawawi on 17/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class GetWatchlistUseCase(private val repository: HomeRepository, dispatcher: CoroutineDispatcher) :
    BaseUseCase<Nothing, List<MovieViewParam>>(dispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<List<MovieViewParam>>> = flow {
        emit(ViewResource.Loading())
        repository.fetchWatchList().collect {
            it.suspendSubscribe(
                doOnSuccess = { result ->
                    val movies = result.payload?.data
                    if (movies.isNullOrEmpty()) emit(ViewResource.Empty()) else
                        emit(ViewResource.Success(ListMapper(MovieMapper).toViewParams(movies)))
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }

}