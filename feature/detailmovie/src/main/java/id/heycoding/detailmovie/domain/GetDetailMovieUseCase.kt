package id.heycoding.detailmovie.domain

import android.util.Log
import id.heycoding.core.base.BaseUseCase
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.detailmovie.data.remote.model.mapper.DetailMovieMapper
import id.heycoding.detailmovie.data.remote.model.viewparam.DetailMovieViewParam
import id.heycoding.detailmovie.data.repository.DetailMovieRepository
import id.heycoding.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by Irfan Nawawi on 12/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class GetDetailMovieUseCase(
    private val repository: DetailMovieRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<GetDetailMovieUseCase.Param, DetailMovieViewParam>(dispatcher) {

    override suspend fun execute(param: Param?): Flow<ViewResource<DetailMovieViewParam>> = flow {
        param?.let {
            emit(ViewResource.Loading())
            repository.fetchDetailMovie(param.movieId).collect {
                it.suspendSubscribe(
                    doOnSuccess = { result ->
                        emit(ViewResource.Success(DetailMovieMapper.toViewParam(result.payload)))
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