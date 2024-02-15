package id.heycoding.detailmovie.data.remote.model.mapper

import id.heycoding.detailmovie.data.remote.model.response.VideoMovieResponse
import id.heycoding.detailmovie.data.remote.model.response.VideoResponse
import id.heycoding.detailmovie.data.remote.model.viewparam.VideoMovieViewParam
import id.heycoding.detailmovie.data.remote.model.viewparam.VideoViewParam
import id.heycoding.shared.utils.ext.orFalse
import id.heycoding.shared.utils.ext.orNol
import id.heycoding.shared.utils.mapper.ListMapper
import id.heycoding.shared.utils.mapper.ViewParamMapper


/**
 * Created by Irfan Nawawi on 15/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
object VideoMovieMapper : ViewParamMapper<VideoMovieResponse, VideoMovieViewParam> {
    override fun toViewParam(dataObject: VideoMovieResponse?): VideoMovieViewParam =
        VideoMovieViewParam(
            id = dataObject?.id.orNol(),
            results = ListMapper(VideoMapper).toViewParams(dataObject?.results)
        )
}

object VideoMapper : ViewParamMapper<VideoResponse, VideoViewParam> {
    override fun toViewParam(dataObject: VideoResponse?): VideoViewParam = VideoViewParam(
        id = dataObject?.id.orEmpty(),
        iso_3166_1 = dataObject?.iso_3166_1.orEmpty(),
        iso_639_1 = dataObject?.iso_639_1.orEmpty(),
        key = dataObject?.key.orEmpty(),
        name = dataObject?.name.orEmpty(),
        official = dataObject?.official.orFalse(),
        published_at = dataObject?.published_at.orEmpty(),
        site = dataObject?.site.orEmpty(),
        size = dataObject?.size.orNol(),
        type = dataObject?.type.orEmpty()
    )

}