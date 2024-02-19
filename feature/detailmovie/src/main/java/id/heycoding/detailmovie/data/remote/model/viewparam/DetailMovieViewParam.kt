package id.heycoding.detailmovie.data.remote.model.viewparam

data class DetailMovieViewParam(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongToCollectionViewParam,
    val budget: Int,
    val genres: List<GenreViewParam>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompanyViewParam>,
    val production_countries: List<ProductionCountryViewParam>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguageViewParam>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class BelongToCollectionViewParam(
    val id: Int,
    val name: String,
    val backdrop_path: String,
    val poster_path: String,
)

data class GenreViewParam(
    val id: Int,
    val name: String
)

data class ProductionCompanyViewParam(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

data class ProductionCountryViewParam(
    val iso_3166_1: String,
    val name: String
)

data class SpokenLanguageViewParam(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)