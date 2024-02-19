package id.heycoding.detailmovie.domain

import id.heycoding.core.base.BaseUseCase
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.detailmovie.data.remote.model.mapper.VideoMapper
import id.heycoding.detailmovie.data.remote.model.viewparam.VideoViewParam
import id.heycoding.detailmovie.data.repository.DetailMovieRepository
import id.heycoding.shared.utils.ext.suspendSubscribe
import id.heycoding.shared.utils.mapper.ListMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by Irfan Nawawi on 12/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class GetVideoMovieUseCase(
    private val repository: DetailMovieRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<GetVideoMovieUseCase.Param, List<VideoViewParam>>(dispatcher) {

    override suspend fun execute(param: Param?): Flow<ViewResource<List<VideoViewParam>>> = flow {
        param?.let {
            emit(ViewResource.Loading())
            repository.fetchVideoMovie(param.movieId).collect {
                it.suspendSubscribe(
                    doOnSuccess = { result ->
                        val videos = result.payload?.results
                        if (videos.isNullOrEmpty()) {
                            emit(ViewResource.Empty())
                        } else {
                            emit(ViewResource.Success(ListMapper(VideoMapper).toViewParams(videos)))
                        }
                    },
                    doOnError = { error ->
                        emit(ViewResource.Error(error.exception))
                    }
                )
            }
        }
    }

    data class Param(val movieId: String)
}