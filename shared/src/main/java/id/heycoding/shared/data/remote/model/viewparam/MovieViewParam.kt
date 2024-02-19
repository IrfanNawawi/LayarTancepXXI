package id.heycoding.shared.data.remote.model.viewparam

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Created by Irfan Nawawi on 05/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
@Parcelize
data class MovieViewParam(
    val backdropPath: String,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
) : Parcelable
