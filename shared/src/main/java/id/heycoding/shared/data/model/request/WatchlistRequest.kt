package id.heycoding.shared.data.model.request

import com.google.gson.annotations.SerializedName


/**
 * Created by Irfan Nawawi on 06/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
data class WatchlistRequest(
    @SerializedName("movie_id")
    val movieId: String,
)
