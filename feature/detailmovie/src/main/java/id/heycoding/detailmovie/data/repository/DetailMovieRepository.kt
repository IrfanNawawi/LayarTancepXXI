package id.heycoding.detailmovie.data.repository

import id.heycoding.core.wrapper.DataResource
import id.heycoding.detailmovie.data.remote.datasource.DetailMovieDataSource
import id.heycoding.detailmovie.data.remote.model.response.DetailMovieResponse
import id.heycoding.detailmovie.data.remote.model.response.VideoMovieResponse
import id.heycoding.detailmovie.data.remote.model.response.VideoResponse
import id.heycoding.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by Irfan Nawawi on 12/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */

typealias DetailDataResource = DataResource<DetailMovieResponse>
typealias VideoDataResource = DataResource<VideoMovieResponse>

interface DetailMovieRepository {
    suspend fun fetchDetailMovie(movieId: String): Flow<DetailDataResource>
    suspend fun fetchVideoMovie(movieId: String): Flow<VideoDataResource>
}

class DetailMovieRepositoryImpl(private val dataSource: DetailMovieDataSource) :
    DetailMovieRepository,
    Repository() {
    override suspend fun fetchDetailMovie(movieId: String): Flow<DetailDataResource> {
        return flow {
            emit(safeNetworkCall { dataSource.fetchDetailMovie(movieId) })
        }
    }

    override suspend fun fetchVideoMovie(movieId: String): Flow<VideoDataResource> {
        return flow {
            emit(safeNetworkCall { dataSource.fetchVideoMovie(movieId) })
        }
    }


}