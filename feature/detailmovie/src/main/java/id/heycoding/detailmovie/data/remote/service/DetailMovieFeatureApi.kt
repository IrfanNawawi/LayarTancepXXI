package id.heycoding.detailmovie.data.remote.service

import id.heycoding.detailmovie.data.remote.model.response.DetailMovieResponse
import id.heycoding.detailmovie.data.remote.model.response.VideoMovieResponse
import id.heycoding.detailmovie.data.remote.model.response.VideoResponse
import id.heycoding.shared.data.remote.model.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by Irfan Nawawi on 11/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface DetailMovieFeatureApi {
    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(@Path("movie_id") movieId: String): DetailMovieResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getVideoMovie(@Path("movie_id") movieId: String): VideoMovieResponse
}