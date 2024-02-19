package id.heycoding.detailmovie.data.remote.model.mapper

import id.heycoding.detailmovie.data.remote.model.response.BelongToCollectionResponse
import id.heycoding.detailmovie.data.remote.model.response.DetailMovieResponse
import id.heycoding.detailmovie.data.remote.model.response.GenreResponse
import id.heycoding.detailmovie.data.remote.model.response.ProductionCompanyResponse
import id.heycoding.detailmovie.data.remote.model.response.ProductionCountryResponse
import id.heycoding.detailmovie.data.remote.model.response.SpokenLanguageResponse
import id.heycoding.detailmovie.data.remote.model.viewparam.BelongToCollectionViewParam
import id.heycoding.detailmovie.data.remote.model.viewparam.DetailMovieViewParam
import id.heycoding.detailmovie.data.remote.model.viewparam.GenreViewParam
import id.heycoding.detailmovie.data.remote.model.viewparam.ProductionCompanyViewParam
import id.heycoding.detailmovie.data.remote.model.viewparam.ProductionCountryViewParam
import id.heycoding.detailmovie.data.remote.model.viewparam.SpokenLanguageViewParam
import id.heycoding.shared.utils.ext.orFalse
import id.heycoding.shared.utils.ext.orNol
import id.heycoding.shared.utils.ext.orNolDouble
import id.heycoding.shared.utils.mapper.ListMapper
import id.heycoding.shared.utils.mapper.ViewParamMapper


/**
 * Created by Irfan Nawawi on 12/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
object DetailMovieMapper : ViewParamMapper<DetailMovieResponse, DetailMovieViewParam> {
    override fun toViewParam(dataObject: DetailMovieResponse?): DetailMovieViewParam =
        DetailMovieViewParam(
            adult = dataObject?.adult.orFalse(),
            backdrop_path = dataObject?.backdrop_path.orEmpty(),
            belongs_to_collection = BelongToCollectionMapper.toViewParam(dataObject?.belongs_to_collection),
            budget = dataObject?.budget.orNol(),
            genres = ListMapper(GenreMapper).toViewParams(dataObject?.genres),
            homepage = dataObject?.homepage.orEmpty(),
            id = dataObject?.id.orNol(),
            imdb_id = dataObject?.imdb_id.orEmpty(),
            original_language = dataObject?.original_language.orEmpty(),
            original_title = dataObject?.original_title.orEmpty(),
            overview = dataObject?.overview.orEmpty(),
            popularity = dataObject?.popularity.orNolDouble(),
            poster_path = dataObject?.poster_path.orEmpty(),
            production_companies = ListMapper(ProductionCompanyMapper).toViewParams(dataObject?.production_companies),
            production_countries = ListMapper(ProductionCountryMapper).toViewParams(dataObject?.production_countries),
            release_date = dataObject?.release_date.orEmpty(),
            revenue = dataObject?.revenue.orNol(),
            runtime = dataObject?.runtime.orNol(),
            spoken_languages = ListMapper(SpokenLanguageMapper).toViewParams(dataObject?.spoken_languages),
            status = dataObject?.status.orEmpty(),
            tagline = dataObject?.tagline.orEmpty(),
            title = dataObject?.title.orEmpty(),
            video = dataObject?.video.orFalse(),
            vote_average = dataObject?.vote_average.orNolDouble(),
            vote_count = dataObject?.vote_count.orNol()
        )
}

object BelongToCollectionMapper : ViewParamMapper<BelongToCollectionResponse, BelongToCollectionViewParam> {
    override fun toViewParam(dataObject: BelongToCollectionResponse?): BelongToCollectionViewParam = BelongToCollectionViewParam(
        id = dataObject?.id.orNol(),
        name = dataObject?.name.orEmpty(),
        poster_path = dataObject?.poster_path.orEmpty(),
        backdrop_path = dataObject?.backdrop_path.orEmpty(),
    )
}

object GenreMapper : ViewParamMapper<GenreResponse, GenreViewParam> {
    override fun toViewParam(dataObject: GenreResponse?): GenreViewParam = GenreViewParam(
        id = dataObject?.id.orNol(),
        name = dataObject?.name.orEmpty()
    )
}

object ProductionCompanyMapper :
    ViewParamMapper<ProductionCompanyResponse, ProductionCompanyViewParam> {
    override fun toViewParam(dataObject: ProductionCompanyResponse?): ProductionCompanyViewParam =
        ProductionCompanyViewParam(
            id = dataObject?.id.orNol(),
            logo_path = dataObject?.logo_path.orEmpty(),
            name = dataObject?.name.orEmpty(),
            origin_country = dataObject?.origin_country.orEmpty()
        )
}

object ProductionCountryMapper :
    ViewParamMapper<ProductionCountryResponse, ProductionCountryViewParam> {
    override fun toViewParam(dataObject: ProductionCountryResponse?): ProductionCountryViewParam =
        ProductionCountryViewParam(
            iso_3166_1 = dataObject?.iso_3166_1.orEmpty(),
            name = dataObject?.name.orEmpty()
        )
}

object SpokenLanguageMapper : ViewParamMapper<SpokenLanguageResponse, SpokenLanguageViewParam> {
    override fun toViewParam(dataObject: SpokenLanguageResponse?): SpokenLanguageViewParam =
        SpokenLanguageViewParam(
            english_name = dataObject?.english_name.orEmpty(),
            iso_639_1 = dataObject?.iso_639_1.orEmpty(),
            name = dataObject?.name.orEmpty()
        )
}