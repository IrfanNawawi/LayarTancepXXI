package id.heycoding.home.data.network.datasource

import id.heycoding.home.data.network.service.HomeFeatureApi
import id.heycoding.shared.data.model.response.BaseResponse
import id.heycoding.shared.data.model.response.MovieResponse


/**
 * Created by Irfan Nawawi on 17/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface HomeDataSource {
    suspend fun fetchPopularMovie(): BaseResponse<List<MovieResponse>>
    suspend fun fetchUpcomingMovie(): BaseResponse<List<MovieResponse>>
}

class HomeDataSourceImpl(private val service: HomeFeatureApi) : HomeDataSource {
    override suspend fun fetchPopularMovie(): BaseResponse<List<MovieResponse>> {
        return service.getPopularMovie()
    }

    override suspend fun fetchUpcomingMovie(): BaseResponse<List<MovieResponse>> {
        return service.getUpcomingMovie()
    }

}