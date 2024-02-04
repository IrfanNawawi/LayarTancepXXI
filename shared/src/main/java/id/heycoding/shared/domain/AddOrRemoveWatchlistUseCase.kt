package id.heycoding.shared.domain

import id.heycoding.core.base.BaseUseCase
import id.heycoding.core.wrapper.DataResource
import id.heycoding.core.wrapper.ViewResource
import id.heycoding.shared.data.model.viewparam.MovieViewParam
import id.heycoding.shared.data.repository.SharedApiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart


/**
 * Created by Irfan Nawawi on 21/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class AddOrRemoveWatchlistUseCase(
    private val repository: SharedApiRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<AddOrRemoveWatchlistUseCase.Param, MovieViewParam>(dispatcher) {
    override suspend fun execute(param: Param?): Flow<ViewResource<MovieViewParam>> {
        param?.let {
            val movie = param.movie
            val movieId = param.movie.id
            val action =
                if (param.movie.isUserWatchlist)
                    repository.removeWatchlist(movieId.toString())
                else
                    repository.addWatchlist(movieId.toString())
            return action.map { result ->
                when (result) {
                    is DataResource.Success -> ViewResource.Success(movie.apply {
                        isUserWatchlist = isUserWatchlist.not()
                    })

                    is DataResource.Error -> ViewResource.Error(result.exception)
                }
            }.onStart { emit(ViewResource.Loading()) }
        } ?: throw IllegalStateException("Param Required")
    }

    data class Param(val movie: MovieViewParam)
}