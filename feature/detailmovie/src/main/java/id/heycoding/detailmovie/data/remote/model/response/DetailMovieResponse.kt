package id.heycoding.detailmovie.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdrop_path: String?,
    @SerializedName("belongs_to_collection")
    val belongs_to_collection: BelongToCollectionResponse?,
    @SerializedName("budget")
    val budget: Int?,
    @SerializedName("genres")
    val genres: List<GenreResponse>?,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("imdb_id")
    val imdb_id: String?,
    @SerializedName("original_language")
    val original_language: String?,
    @SerializedName("original_title")
    val original_title: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("production_companies")
    val production_companies: List<ProductionCompanyResponse>?,
    @SerializedName("production_countries")
    val production_countries: List<ProductionCountryResponse>?,
    @SerializedName("release_date")
    val release_date: String?,
    @SerializedName("revenue")
    val revenue: Int?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("spoken_languages")
    val spoken_languages: List<SpokenLanguageResponse>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val vote_average: Double?,
    @SerializedName("vote_count")
    val vote_count: Int?
)

data class BelongToCollectionResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("backdrop_path")
    val backdrop_path: String?,
)

data class GenreResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)

data class ProductionCompanyResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("logo_path")
    val logo_path: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin_country")
    val origin_country: String?
)

data class ProductionCountryResponse(
    @SerializedName("iso_3166_1")
    val iso_3166_1: String?,
    @SerializedName("name")
    val name: String?
)

data class SpokenLanguageResponse(
    @SerializedName("english_name")
    val english_name: String?,
    @SerializedName("iso_639_1")
    val iso_639_1: String?,
    @SerializedName("name")
    val name: String?
)