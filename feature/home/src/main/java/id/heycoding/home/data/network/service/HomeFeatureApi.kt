package id.heycoding.home.data.network.service

import id.heycoding.shared.data.model.response.BaseResponse
import id.heycoding.shared.data.model.response.MovieResponse
import retrofit2.http.GET


/**
 * Created by Irfan Nawawi on 17/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface HomeFeatureApi {
    @GET("movie/popular")
    suspend fun getPopularMovie(): BaseResponse<List<MovieResponse>>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(): BaseResponse<List<MovieResponse>>
}