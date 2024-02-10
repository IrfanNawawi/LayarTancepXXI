package id.heycoding.home.domain

import id.heycoding.core.base.BaseUseCase
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.home.data.repository.HomeRepository
import id.heycoding.home.data.network.model.viewparam.homeitem.HomeUiItem
import id.heycoding.shared.data.model.mapper.MovieMapper
import id.heycoding.shared.data.model.viewparam.MovieViewParam
import id.heycoding.shared.utils.Const
import id.heycoding.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by Irfan Nawawi on 17/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class GetUpcomingMovieUseCase(private val repository: HomeRepository, dispatcher: CoroutineDispatcher) :
    BaseUseCase<Nothing, List<HomeUiItem>>(dispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<List<HomeUiItem>>> = flow {
        emit(ViewResource.Loading())
        repository.fetchUpcomingMovie().collect {
            it.suspendSubscribe(
                doOnSuccess = { result ->
                    val data = mutableListOf<HomeUiItem>()
                    result.payload?.data?.let { homeData ->
                        homeData.forEach { movie->
                            data.add(HomeUiItem.UpcomingSectionItem(Const.UPCOMING_TITLE, MovieMapper.toViewParam(movie)))
                        }
                    }
                    emit(ViewResource.Success(data))
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }

}