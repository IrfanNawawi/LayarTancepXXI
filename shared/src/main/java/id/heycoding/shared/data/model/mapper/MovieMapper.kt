package id.heycoding.shared.data.model.mapper

import id.heycoding.shared.data.model.response.MovieResponse
import id.heycoding.shared.data.model.viewparam.MovieViewParam
import id.heycoding.shared.utils.ext.orFalse
import id.heycoding.shared.utils.ext.orNol
import id.heycoding.shared.utils.ext.orNolDouble
import id.heycoding.shared.utils.mapper.ViewParamMapper


/**
 * Created by Irfan Nawawi on 05/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
object MovieMapper : ViewParamMapper<MovieResponse, MovieViewParam> {
    override fun toViewParam(dataObject: MovieResponse?): MovieViewParam = MovieViewParam(
        backdropPath = dataObject?.backdropPath.orEmpty(),
        id = dataObject?.id.orNol(),
        originalLanguage = dataObject?.originalLanguage.orEmpty(),
        originalTitle = dataObject?.originalTitle.orEmpty(),
        overview = dataObject?.overview.orEmpty(),
        popularity = dataObject?.popularity.orNolDouble(),
        posterPath = dataObject?.posterPath.orEmpty(),
        releaseDate = dataObject?.releaseDate.orEmpty(),
        title = dataObject?.title.orEmpty(),
        video = dataObject?.video.orFalse(),
        voteAverage = dataObject?.voteAverage.orNolDouble(),
        voteCount = dataObject?.voteCount.orNol()
    )

}