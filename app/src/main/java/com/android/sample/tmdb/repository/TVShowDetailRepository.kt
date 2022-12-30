package com.android.sample.tmdb.repository

import android.content.Context
import com.android.sample.tmdb.data.network.TVShowService
import com.android.sample.tmdb.data.response.NetworkCreditWrapper
import com.android.sample.tmdb.di.IoDispatcher
import com.android.sample.tmdb.domain.BaseDetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TVShowDetailRepository @Inject constructor(
    private val tvId: Int,
    private val tvShowApi: TVShowService,
    context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseDetailRepository(context, ioDispatcher) {

    override suspend fun getCredit(): NetworkCreditWrapper = tvShowApi.tvCredit(tvId)
}