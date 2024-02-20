package id.heycoding.shared.data.remote.services

import id.heycoding.shared.data.remote.model.request.DetailMovieRequest
import id.heycoding.shared.data.remote.model.response.BaseResponse
import id.heycoding.shared.data.remote.model.response.VideoMovieResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * Created by Irfan Nawawi on 06/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface SharedFeatureApi {
    @POST("/api/v1/watchlist")
    suspend fun addWatchlist(@Body request: DetailMovieRequest): BaseResponse<Any>

    @HTTP(method = "DELETE", path = "/api/v1/watchlist", hasBody = true)
    suspend fun removeWatchlist(@Body request: DetailMovieRequest): BaseResponse<Any>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideoMovie(@Path("movie_id") movieId: String): VideoMovieResponse
}