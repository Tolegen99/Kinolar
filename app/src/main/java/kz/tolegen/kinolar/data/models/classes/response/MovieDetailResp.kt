package kz.tolegen.kinolar.data.models.classes.response

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class MovieDetailResp(
    @Json(name = "adult")val adult: Boolean,
    @Json(name = "backdrop_path")val backdrop_path: String,
    @Json(name = "budget")val budget: Int,
    @Json(name = "genres")val genres: List<GenreResp>,
    @Json(name = "homepage")val homepage: String,
    @Json(name = "id")val id: Long,
    @Json(name = "imdb_id")val imdb_id: String,
    @Json(name = "original_language")val original_language: String,
    @Json(name = "original_title")val original_title: String,
    @Json(name = "overview")val overview: String,
    @Json(name = "popularity")val popularity: Double,
    @Json(name = "poster_path")val poster_path: String,
    @Json(name = "production_companies")val production_companies: List<ProductionCompanyResp>,
    @Json(name = "release_date")val release_date: String,
    @Json(name = "revenue")val revenue: Int,
    @Json(name = "runtime")val runtime: Int,
    @Json(name = "status")val status: String,
    @Json(name = "tagline")val tagline: String,
    @Json(name = "title")val title: String,
    @Json(name = "video")val video: Boolean,
    @Json(name = "vote_average")val vote_average: Double,
    @Json(name = "vote_count")val vote_count: Int
) : Parcelable