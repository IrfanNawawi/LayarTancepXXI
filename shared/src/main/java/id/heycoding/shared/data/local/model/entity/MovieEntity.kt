package id.heycoding.shared.data.local.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Irfan Nawawi on 29/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
@Entity(tableName = "MovieTable")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo("movie_id")
    val movieId: Int?,
    @ColumnInfo("backdrop_path")
    val backdropPath: String?,
    @ColumnInfo("original_language")
    val originalLanguage: String?,
    @ColumnInfo("original_title")
    val originalTitle: String?,
    @ColumnInfo("overview")
    val overview: String?,
    @ColumnInfo("popularity")
    val popularity: Double?,
    @ColumnInfo("poster_path")
    val posterPath: String?,
    @ColumnInfo("release_date")
    val releaseDate: String?,
    @ColumnInfo("title")
    val title: String?,
    @ColumnInfo("video")
    val video: Boolean?,
    @ColumnInfo("vote_average")
    val voteAverage: Double?,
    @ColumnInfo("vote_count")
    val voteCount: Int?
)
