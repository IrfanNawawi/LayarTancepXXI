package id.heycoding.shared.data.local.datasource

import id.heycoding.shared.data.local.db.MovieDao
import id.heycoding.shared.data.local.model.entity.MovieEntity


/**
 * Created by Irfan Nawawi on 01/03/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface SharedLocalDataSource {
    suspend fun addWatchlist(movieEntity: MovieEntity)
}

class SharedLocalDataSourceImpl(private val movieDao: MovieDao) : SharedLocalDataSource {
    override suspend fun addWatchlist(movieEntity: MovieEntity) {
        movieDao.addWatchlist(movieEntity)
    }

}