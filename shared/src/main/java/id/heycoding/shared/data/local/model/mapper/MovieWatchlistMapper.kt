package id.heycoding.shared.data.local.model.mapper

import id.heycoding.shared.data.local.model.entity.MovieEntity
import id.heycoding.shared.data.remote.model.response.MovieResponse
import id.heycoding.shared.data.remote.model.viewparam.MovieViewParam
import id.heycoding.shared.utils.ext.orFalse
import id.heycoding.shared.utils.ext.orNol
import id.heycoding.shared.utils.ext.orNolDouble
import id.heycoding.shared.utils.mapper.DataObjectMapper
import id.heycoding.shared.utils.mapper.ViewParamMapper


/**
 * Created by Irfan Nawawi on 01/03/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
object MovieWatchlistMapper: DataObjectMapper<MovieEntity, MovieViewParam> {
    override fun toDataObject(viewParam: MovieViewParam?): MovieEntity = MovieEntity(
        backdropPath = viewParam?.backdropPath.orEmpty(),
        movieId = viewParam?.id.orNol(),
        originalLanguage = viewParam?.originalLanguage.orEmpty(),
        originalTitle = viewParam?.originalTitle.orEmpty(),
        overview = viewParam?.overview.orEmpty(),
        popularity = viewParam?.popularity.orNolDouble(),
        posterPath = viewParam?.posterPath.orEmpty(),
        releaseDate = viewParam?.releaseDate.orEmpty(),
        title = viewParam?.title.orEmpty(),
        video = viewParam?.video.orFalse(),
        voteAverage = viewParam?.voteAverage.orNolDouble(),
        voteCount = viewParam?.voteCount.orNol()
    )

}