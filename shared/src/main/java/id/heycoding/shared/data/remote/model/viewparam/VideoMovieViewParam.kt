package id.heycoding.shared.data.remote.model.viewparam


/**
 * Created by Irfan Nawawi on 15/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
data class VideoMovieViewParam(
    val id: Int,
    val results: List<VideoViewParam>
)

data class VideoViewParam(
    val id: String,
    val iso_3166_1: String,
    val iso_639_1: String,
    val key: String,
    val name: String,
    val official: Boolean,
    val published_at: String,
    val site: String,
    val size: Int,
    val type: String
)
