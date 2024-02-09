package id.heycoding.home.data.repository

import id.heycoding.core.wrapper.DataResource
import id.heycoding.home.data.network.datasource.HomeDataSource
import id.heycoding.shared.data.model.response.BaseResponse
import id.heycoding.shared.data.model.response.MovieResponse
import id.heycoding.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by Irfan Nawawi on 17/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */

typealias HomeDataResource = DataResource<BaseResponse<List<MovieResponse>>>

interface HomeRepository {
    suspend fun fetchPopularMovie(): Flow<HomeDataResource>
    suspend fun fetchUpcomingMovie(): Flow<HomeDataResource>
}

class HomeRepositoryImpl(private val dataSource: HomeDataSource) : HomeRepository, Repository() {
    override suspend fun fetchPopularMovie(): Flow<HomeDataResource> {
        return flow {
            emit(safeNetworkCall { dataSource.fetchPopularMovie() })
        }
    }

    override suspend fun fetchUpcomingMovie(): Flow<HomeDataResource> {
        return flow {
            emit(safeNetworkCall { dataSource.fetchUpcomingMovie() })
        }
    }


}