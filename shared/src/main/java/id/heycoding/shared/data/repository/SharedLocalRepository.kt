package id.heycoding.shared.data.repository

import id.heycoding.core.wrapper.DataResource
import id.heycoding.shared.data.local.datasource.SharedLocalDataSource
import id.heycoding.shared.data.local.model.entity.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by Irfan Nawawi on 01/03/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface SharedLocalRepository {
    suspend fun addWatchlist(movieEntity: MovieEntity): Flow<DataResource<Unit>>
}

class SharedLocalRepositoryImpl(private val dataSource: SharedLocalDataSource) : Repository(), SharedLocalRepository {
    override suspend fun addWatchlist(movieEntity: MovieEntity): Flow<DataResource<Unit>> = flow{
        emit(proceed { dataSource.addWatchlist(movieEntity) })
    }

}