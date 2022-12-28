package com.android.sample.tmdb.data.response

import com.android.sample.tmdb.domain.model.Movie
import com.android.sample.tmdb.domain.model.TVShow
import com.squareup.moshi.Json

interface NetworkTMDbItem {
    val id : Int
    val overview: String
    val releaseDate: String?
    val posterPath: String?
    val backdropPath: String?
    val name: String
    val voteAverage: Double
}

data class NetworkMovie(
    override val id: Int,
    override val overview: String,
    @Json(name = "release_date")
    override val releaseDate: String?,
    @Json(name = "poster_path")
    override val posterPath: String?,
    @Json(name = "backdrop_path")
    override val backdropPath: String?,
    @Json(name = "title")
    override val name: String,
    @Json(name = "vote_average")
    override val voteAverage: Double) : NetworkTMDbItem

data class NetworkTVShow(
    override val id: Int,
    override val overview: String,
    @Json(name = "first_air_date")
    override val releaseDate: String?,
    @Json(name = "poster_path")
    override val posterPath: String?,
    @Json(name = "backdrop_path")
    override val backdropPath: String?,
    override val name: String,
    @Json(name = "vote_average")
    override val voteAverage: Double) : NetworkTMDbItem

fun List<NetworkMovie>.asMovieDomainModel(): List<Movie> =
    map {
        Movie(
            it.id,
            it.overview,
            it.releaseDate,
            it.posterPath,
            it.backdropPath,
            it.name,
            it.voteAverage
        )
    }

fun List<NetworkTVShow>.asTVShowDomainModel(): List<TVShow> =
    map {
        TVShow(
            it.id,
            it.overview,
            it.releaseDate,
            it.posterPath,
            it.backdropPath,
            it.name,
            it.voteAverage
        )
    }