package com.android.sample.tmdb.data.paged.tvshow

import android.content.Context
import com.android.sample.tmdb.data.network.TVShowService
import com.android.sample.tmdb.data.paged.BasePagingSource
import com.android.sample.tmdb.data.response.asTVShowDomainModel
import com.android.sample.tmdb.domain.model.TVShow

class TopRatedTvSeriesPagingSource(
    context: Context,
    private val tvShowApi: TVShowService
) : BasePagingSource<TVShow>(context) {

    override suspend fun fetchItems(page: Int): List<TVShow> =
        tvShowApi.topRatedTVSeries(page).items.asTVShowDomainModel()
}