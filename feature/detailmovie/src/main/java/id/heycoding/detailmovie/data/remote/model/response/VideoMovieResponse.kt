package id.heycoding.detailmovie.data.remote.model.response

data class VideoMovieResponse(
    val id: Int?,
    val results: List<VideoResponse>?
)

data class VideoResponse(
    val id: String?,
    val iso_3166_1: String?,
    val iso_639_1: String?,
    val key: String?,
    val name: String?,
    val official: Boolean?,
    val published_at: String?,
    val site: String?,
    val size: Int?,
    val type: String?
)