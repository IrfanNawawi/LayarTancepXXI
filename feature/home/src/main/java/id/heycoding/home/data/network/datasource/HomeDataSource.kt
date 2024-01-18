package id.heycoding.home.data.network.datasource

import id.heycoding.home.data.network.model.response.HomeFeedsResponse
import id.heycoding.home.data.network.service.HomeFeatureApi
import id.heycoding.shared.data.model.response.BaseResponse
import id.heycoding.shared.data.model.response.MovieResponse


/**
 * Created by Irfan Nawawi on 17/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface HomeDataSource {
    suspend fun fetchHomeFeeds(): BaseResponse<HomeFeedsResponse>
    suspend fun fetchWatchList(): BaseResponse<List<MovieResponse>>
}

class HomeDataSourceImpl(private val service: HomeFeatureApi) : HomeDataSource {
    override suspend fun fetchHomeFeeds(): BaseResponse<HomeFeedsResponse> {
        return service.fetchHomeFeeds()
    }

    override suspend fun fetchWatchList(): BaseResponse<List<MovieResponse>> {
        return service.fetchWatchlist()
    }

}