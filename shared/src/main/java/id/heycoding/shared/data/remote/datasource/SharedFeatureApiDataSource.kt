package id.heycoding.shared.data.remote.datasource

import id.heycoding.shared.data.remote.model.request.DetailMovieRequest
import id.heycoding.shared.data.remote.model.response.BaseResponse
import id.heycoding.shared.data.remote.model.response.VideoMovieResponse
import id.heycoding.shared.data.remote.services.SharedFeatureApi


/**
 * Created by Irfan Nawawi on 06/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface SharedFeatureApiDataSource {
    suspend fun addWatchlist(request: DetailMovieRequest): BaseResponse<Any>
    suspend fun removeWatchlist(request: DetailMovieRequest): BaseResponse<Any>
    suspend fun fetchVideoMovie(movieId: String): VideoMovieResponse
}

class SharedFeatureApiDataSourceImpl(private val sharedFeatureApi: SharedFeatureApi) :
    SharedFeatureApiDataSource {
    override suspend fun addWatchlist(request: DetailMovieRequest): BaseResponse<Any> {
        return sharedFeatureApi.addWatchlist(request)
    }

    override suspend fun removeWatchlist(request: DetailMovieRequest): BaseResponse<Any> {
        return sharedFeatureApi.removeWatchlist(request)
    }

    override suspend fun fetchVideoMovie(movieId: String): VideoMovieResponse {
        return sharedFeatureApi.getVideoMovie(movieId)
    }

}