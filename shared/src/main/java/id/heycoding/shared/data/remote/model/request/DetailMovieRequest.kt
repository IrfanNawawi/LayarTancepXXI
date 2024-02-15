package id.heycoding.shared.data.remote.model.request

import com.google.gson.annotations.SerializedName


/**
 * Created by Irfan Nawawi on 06/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
data class DetailMovieRequest(
    @SerializedName("movie_id")
    val movieId: Int,
)
