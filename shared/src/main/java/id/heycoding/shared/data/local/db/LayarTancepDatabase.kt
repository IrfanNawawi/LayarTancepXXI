package id.heycoding.shared.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.heycoding.shared.data.local.model.entity.MovieEntity


/**
 * Created by Irfan Nawawi on 29/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class LayarTancepDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}

fun provideDataBase(context: Context): LayarTancepDatabase =
    Room.databaseBuilder(
        context,
        LayarTancepDatabase::class.java,
        "MovieDatabase"
    ).
    fallbackToDestructiveMigration().build()

fun provideDao(layarTancepDatabase: LayarTancepDatabase): MovieDao = layarTancepDatabase.movieDao()