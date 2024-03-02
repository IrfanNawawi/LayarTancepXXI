package id.heycoding.shared.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.heycoding.shared.data.local.model.entity.MovieEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by Irfan Nawawi on 29/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWatchlist(movieEntity: MovieEntity)

    @Query("SELECT * FROM MovieTable ORDER BY movie_id DESC")
    fun getAllWatchlist(): Flow<MutableList<MovieEntity>>
}