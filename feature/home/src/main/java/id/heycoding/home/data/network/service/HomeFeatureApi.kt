package id.heycoding.home.data.network.service

import id.heycoding.home.data.network.model.response.HomeFeedsResponse
import id.heycoding.shared.data.model.response.BaseResponse
import id.heycoding.shared.data.model.response.MovieResponse
import retrofit2.http.GET


/**
 * Created by Irfan Nawawi on 17/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface HomeFeatureApi {
    @GET("api/v1/homepage")
    suspend fun fetchHomeFeeds(): BaseResponse<HomeFeedsResponse>

    @GET("api/v1/watchlist")
    suspend fun fetchWatchlist(): BaseResponse<List<MovieResponse>>
}