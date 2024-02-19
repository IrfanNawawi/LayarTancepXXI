package id.heycoding.detailmovie.data.remote.datasource

import id.heycoding.detailmovie.data.remote.model.response.DetailMovieResponse
import id.heycoding.detailmovie.data.remote.model.response.VideoMovieResponse
import id.heycoding.detailmovie.data.remote.model.response.VideoResponse
import id.heycoding.detailmovie.data.remote.service.DetailMovieFeatureApi


/**
 * Created by Irfan Nawawi on 11/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface DetailMovieDataSource {
    suspend fun fetchDetailMovie(movieId: String): DetailMovieResponse
    suspend fun fetchVideoMovie(movieId: String): VideoMovieResponse
}

class DetailMovieDataSourceImpl(private val service: DetailMovieFeatureApi) :
    DetailMovieDataSource {
    override suspend fun fetchDetailMovie(movieId: String): DetailMovieResponse {
        return service.getDetailMovie(movieId)
    }

    override suspend fun fetchVideoMovie(movieId: String): VideoMovieResponse {
        return service.getVideoMovie(movieId)
    }

}