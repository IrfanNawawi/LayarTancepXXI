package id.heycoding.detailmovie.data.repository

import id.heycoding.core.wrapper.DataResource
import id.heycoding.detailmovie.data.remote.datasource.DetailMovieDataSource
import id.heycoding.detailmovie.data.remote.model.response.DetailMovieResponse
import id.heycoding.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by Irfan Nawawi on 12/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */

typealias DetailDataResource = DataResource<DetailMovieResponse>

interface DetailMovieRepository {
    suspend fun fetchDetailMovie(movieId: String): Flow<DetailDataResource>
}

class DetailMovieRepositoryImpl(private val dataSource: DetailMovieDataSource) :
    DetailMovieRepository,
    Repository() {
    override suspend fun fetchDetailMovie(movieId: String): Flow<DetailDataResource> {
        return flow {
            emit(safeNetworkCall { dataSource.fetchDetailMovie(movieId) })
        }
    }

}