package id.heycoding.shared.data.repository

import id.heycoding.core.wrapper.DataResource
import id.heycoding.shared.data.remote.model.request.DetailMovieRequest
import id.heycoding.shared.data.remote.datasource.SharedFeatureApiDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by Irfan Nawawi on 06/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface SharedApiRepository {

    suspend fun addWatchlist(movieId: Int): Flow<DataResource<Any>>

    suspend fun removeWatchlist(movieId: Int): Flow<DataResource<Any>>
}

class SharedApiRepositoryImpl(
    private val dataSource: SharedFeatureApiDataSource
) : Repository(), SharedApiRepository {
    override suspend fun addWatchlist(movieId: Int): Flow<DataResource<Any>> {
        return flow {
            emit(safeNetworkCall { dataSource.addWatchlist(DetailMovieRequest(movieId)) })
        }
    }

    override suspend fun removeWatchlist(movieId: Int): Flow<DataResource<Any>> {
        return flow {
            emit(safeNetworkCall { dataSource.removeWatchlist(DetailMovieRequest(movieId)) })
        }
    }

}