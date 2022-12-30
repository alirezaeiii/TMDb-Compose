package com.android.sample.tmdb.data.response

import com.android.sample.tmdb.domain.model.Cast
import com.android.sample.tmdb.domain.model.Crew
import com.squareup.moshi.Json

class NetworkCast(
    @Json(name = "character")
    val credit: String,
    val name: String,
    @Json(name = PROFILE_PATH)
    val profilePath: String?,
    val id: Int
)

class NetworkCrew(
    @Json(name ="job")
    val credit: String,
    val name: String,
    @Json(name = PROFILE_PATH)
    val profilePath: String?,
    val id: String
)

fun List<NetworkCast>.asCastDomainModel(): List<Cast> = map {
    Cast(it.credit, it.name, it.profilePath, it.id)
}

fun List<NetworkCrew>.asCrewDomainModel(): List<Crew> = map {
    Crew(it.credit, it.name, it.profilePath, it.id)
}

private const val PROFILE_PATH = "profile_path"